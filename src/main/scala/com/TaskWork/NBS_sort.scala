package com.TaskWork

import java.util

import com.IClrawler
import com.IClrawler.SQL.NBS_ConnectSql
import com.IClrawler.TaskWork.NationalData_Work
import com.IClrawler.{Units, httpCom}
import net.sf.json.{JSONArray, JSONObject}
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpPost, HttpGet}
import org.apache.http.message.{BasicHeader, BasicNameValuePair}

import scala.io.Source
import scala.util.Random

/**
 * Created by dell on 2016/2/1.
 */
class NBS_sort extends httpCom {
  var dataType = ""
  var uri = "http://data.stats.gov.cn/easyquery.htm?cn=E0101"
  var dbcode = "fsyd"
  var wdcode = "zb"
  var rowcode = "zb"

  def task: Unit = {
    val usA = Units.setheader("data.stats.gov.cn", uri)
    var taskType = "fsyd"
    NBS_sort.map += (("", "null"))
    setCookie
    firstGet("http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=hgyd&rowcode=zb&colcode=sj&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22201511-201512%22%7D%5D")
    //获得城市映射JSON URI
    val cityUri = "http://data.stats.gov.cn/easyquery.htm?m=getOtherWds&dbcode=fsyd&rowcode=zb&colcode=sj&wds=%5B%5D&k1"
    //获得分类标签映射JSON URI
    val tagUri = "http://data.stats.gov.cn/easyquery.htm?m=getOtherWds&dbcode=fsyd&rowcode=reg&colcode=sj&wds=%5B%5D&k1"
    addClassifyId(httpGet(cityUri, usA.toArray))
    addClassifyId(httpGet(tagUri, usA.toArray))
    println("分类标签已获取")
    dataType = "分省月度数据"

    post("zb")

  }

   def post(id: String = "zb", postUri: String = "http://data.stats.gov.cn/easyquery.htm") :String= {
    //请求标签 获得基础标签JSON
    val ar = new util.ArrayList[BasicNameValuePair]
    ar.add(new BasicNameValuePair("id", id))
    ar.add(new BasicNameValuePair("dbcode", dbcode))
    ar.add(new BasicNameValuePair("wdcode", wdcode))
    ar.add(new BasicNameValuePair("m", "getTree"))

    val usA = Units.setheader("data.stats.gov.cn", uri)
    usA += new BasicHeader("Cookie", NBS_sort.Cookie.split(":")(1))
    val str = super.httpPost(postUri, usA.toArray, ar)
     Jparse(str)
     str
  }

  def Jparse2(string: String) = {
    //得到POST返回的JSON进行解析

    val jar = JSONArray.fromObject(string)
    for (i <- 0 to jar.size - 1) {
      val job = JSONObject.fromObject(jar.get(i))
      if (job.get("isParent").toString == "true") {
        //判断是否为父节点 添加MAP
        NBS_sort.map.+=((job.get("id").toString, job.get("name").toString))
      } else {
        NBS_sort.array(2) = NBS_sort.map.get(job.get("pid").toString).get
        NBS_sort.map.+=((job.get("id").toString, job.get("name").toString));
        NBS_sort.array(0) = job.get("name").toString;
      }
    }

  }

  def Jparse(string: String) = {
    //得到POST返回的JSON进行解析
    val jar = JSONArray.fromObject(string)
    for (i <- 0 to jar.size - 1) {
      val job = JSONObject.fromObject(jar.get(i))
      if (job.get("isParent").toString == "true") {
        //判断是否为父节点 添加MAP 父节点重复请求
        NBS_sort.map.+=((job.get("id").toString, job.get("name").toString))
        post(job.get("id").toString)
      } else {
        NBS_sort.map.+=((job.get("id").toString, job.get("name").toString));
        NBS_sort.array(2) = NBS_sort.map.get(job.get("pid").toString).get
        NBS_sort.array(0) = job.get("name").toString;
        NBS_sort.map.foreach(e => httpGet(e._1, job.get("id").toString))
      }
    }
  }

  def setCookie = {
    val hg = new HttpGet(uri)
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn", uri))
    val response = Units.IHttpclient.execute(hg)
    //请求从相应中得到并设置cookie
    NBS_sort.Cookie = response.getFirstHeader("Set-Cookie").toString
  }

  def httpGet(str: String, tag: String): Unit = {
    NBS_sort.array(3) = NBS_sort.map.get(str).get
    val url = makeUri(str, tag)
    println(url)
    val usA = Units.setheader("data.stats.gov.cn", uri)
    usA += new BasicHeader("Cookie", NBS_sort.Cookie.split(":")(1))
    val content = super.httpGet(url, usA.toArray)
    content
    IJsonParse(content)

  }

  def IJsonParse(string: String) = {
    val jo = JSONObject.fromObject(string).get("returndata")
    val jc = JSONObject.fromObject(jo).get("wdnodes")
    val jcar = JSONArray.fromObject(jc)
    val zbs = JSONArray.fromObject(JSONObject.fromObject(jcar.get(0)).get("nodes"))
    for (i <- 0 to zbs.size() - 1) {
      val zbsi = zbs.get(i)
      println("ID:" + JSONObject.fromObject(zbsi).get("code"))
      println("标签:" + JSONObject.fromObject(zbsi).get("name"))
      NBS_sort.map.+=((JSONObject.fromObject(zbsi).get("code").toString, NBS_sort makeTag(JSONObject.fromObject(zbsi).get("name") toString, JSONObject.fromObject(zbsi).get("unit").toString)
        ))
    }
    val jb = JSONObject.fromObject(jo).get("datanodes")
    val jar = JSONArray.fromObject(jb)
    val size = jar.size()
    for (i <- 0 to size - 1) {
      val lo = jar.get(i)
      //    println(lo)
      val jars = JSONObject.fromObject(lo.toString)
      val jobj = JSONObject.fromObject(jars.get("data"))
      if (jobj.get("hasdata").toString == "true") {
        val ar = JSONArray.fromObject(jars.get("wds"))
        //土地为1 分类年月度为0
        // NationalData_WkOther.array(1) = NationalData_WkOther.cityMap.get(JSONObject.fromObject(ar.get(1)).get("valuecode").toString).get
        val valStr = JSONObject.fromObject(ar.get(0)).get("valuecode").toString
        println(valStr)
        NBS_sort.array(1) = NBS_sort.map.get(valStr).get
        NBS_sort.array(4) = JSONObject.fromObject(ar.get(2)).get("valuecode").toString
        NBS_sort.array(5) = jobj.get("strdata").toString
        addSql()
      }
    }
  }

  def firstGet(string: String) = {
    //根据cookie  SEESION设置时间
    val hg = new HttpGet(string)
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn", uri))
    //得到cookie
    hg.setHeader("Cookie", NBS_sort.Cookie.split(":")(1))
    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    content

  }

  def addsortMap(string: String) = {
    //解析json添加标签键值
    val jo = JSONObject.fromObject(string).get("returndata")
    val jc = JSONArray.fromObject(jo).get(0)

    val jd = JSONObject.fromObject(jc).get("nodes")
    val je = JSONArray.fromObject(jd)
    for (i <- 0 to je.size - 1) {
      val job = JSONObject.fromObject(je.get(i))
      val code = (job.get("code") toString, job.get("name") toString)
      //      NBS_sort.sortMap += (code)
      //      NBS_sort.sortMap.foreach(e => println(e._1 + ":" + e._2))
      NBS_sort.map += (code)
  //    NBS_sort.map.foreach(e => println(e._1 + ":" + e._2))
    }
  }

  def addcityMap(string: String) = {
    //解析json添加城市键值
    val jo = JSONObject.fromObject(string).get("returndata")
    val jc = JSONArray.fromObject(jo).get(0)
    val jd = JSONObject.fromObject(jc).get("nodes")
    val je = JSONArray.fromObject(jd)
    for (i <- 0 to je.size - 1) {
      val job = JSONObject.fromObject(je.get(i))
      //
      //      NBS_sort.cityMap += ((job.get("code") toString, job.get("name") toString))
      //      NBS_sort.cityMap.foreach(e => println(e._1 + ":" + e._2))
      NBS_sort.map += ((job.get("code") toString, job.get("name") toString))
 //     NBS_sort.map.foreach(e => println(e._1 + ":" + e._2))
    }
  }

  def makeUri(cid: String, id: String) = {
    "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=fsyd&rowcode=zb&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22reg%22%2C%22valuecode%22%3A%22540000%22%7D%5D&dfwds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A010105%22%7D%5D&k1=1455681421285"
  //  "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=fsyd&rowcode=reg&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A01010101%22%7D%5D&dfwds=%5B%5D"
    val uri = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=fsyd&rowcode=zb&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22reg%22%2C%22valuecode%22%3A%22" + cid + "%22%7D%5D&dfwds=%5B%5D"
    //val uri = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode="+dbcode+"&rowcode="+rowcode+"&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22reg%22%2C%22valuecode%22%3A%22" + cid + "%22%7D%5D&dfwds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22" + id + "%22%7D%5D"
    //val uri = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=" + dbcode + "&rowcode=" + rowcode + "&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22" + cid + "%22%7D%5D&dfwds=%5B%5d"
    // "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=fsyd&rowcode=zb&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22reg%22%2C%22valuecode%22%3A%22130000%22%7D%5D&dfwds=%5B%5D"
    uri
  }

  def addClassifyId(string: String) = {
    //解析JSON 添加 城市-编号， 查询标签-编号的映射
    val jo = JSONObject.fromObject(string).get("returndata")
    val jc = JSONArray.fromObject(jo).get(0)

    val jd = JSONObject.fromObject(jc).get("nodes")
    val je = JSONArray.fromObject(jd)
    for (i <- 0 to je.size - 1) {
      val job = JSONObject.fromObject(je.get(i))
      val code = (job.get("code") toString, job.get("name") toString)
      NBS_sort.map += (code)
  //    NBS_sort.map.foreach(e => println(e._1 + ":" + e._2))
    }
  }

  def addSql() = {
    NBS_ConnectSql(dataType, NBS_sort.array)
  }


}

object NBS_sort {
  def makeTag(tag: String, unit: String) = {
    if (unit.length > 0) {
      val mTag = tag + "(" + unit + ")"
      mTag
    } else {
      tag
    }
  }

  var Cookie = ""
  var array = new Array[String](6)
  val map = new scala.collection.mutable.HashMap[String, String]
  val sortMap = new scala.collection.mutable.HashMap[String, String]
  val cityMap = new scala.collection.mutable.HashMap[String, String]
}

