package com.IClrawler.TaskWork

import java.util

import com.IClrawler.SQL.NBS_ConnectSql
import com.IClrawler.Units
import net.sf.json.{JSONArray, JSONObject}
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.message.BasicNameValuePair

import scala.io.Source
import scala.util.Random

/**
 * Created by dell on 2015/11/11.
 */
class NationalData_WkOther {
  var uri = "http://data.stats.gov.cn/easyquery.htm?cn=E0101"
  val postUri = "http://data.stats.gov.cn/easyquery.htm"
  var dbcode = "fsyd"
  var wdcode = "zb"
  var rowcode = "zb"
  val unit = new Units

  def makeTaks = {
    Jparse(HttpPost())
    Thread.sleep(new Random(6000).nextInt(10000))


  }

  def httpCookie = {
    val hg = new HttpGet(uri)
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn", uri))

    val response = Units.IHttpclient.execute(hg)
    NationalData_Work.yzma = response.getFirstHeader("Set-Cookie").toString
  }

  def firstHttpGet(string: String) = {
    val hg = new HttpGet(string)
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn", uri))
    hg.setHeader("Cookie", NationalData_Work.yzma.split(":")(1))
    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    Thread.sleep(new Random(6000).nextInt(10000))
  }

  def HttpPost(id: String = "zb"): String = {
    val hg = new HttpPost(postUri)
    hg.setHeaders(unit.setheader("data.stats.gov.cn", uri))
    hg.setHeader("Referer", uri)
    hg.setHeader("DNT", "1")
    hg.setHeader("Cookie", NationalData_Work.yzma.split(":")(1))

    val ar = new util.ArrayList[BasicNameValuePair]
    ar.add(new BasicNameValuePair("id", id))
    ar.add(new BasicNameValuePair("dbcode", dbcode))
    ar.add(new BasicNameValuePair("wdcode", wdcode))
    ar.add(new BasicNameValuePair("m", "getTree"))
    hg.setEntity(new UrlEncodedFormEntity(ar, "utf-8"))

    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    println(content)
    content
  }

  def Jparse2(string: String) = {
    val jar = JSONArray.fromObject(string)
    for (i <- 0 to jar.size - 1) {
      val job = JSONObject.fromObject(jar.get(i))
      if (job.get("isParent").toString == "true") {
        NationalData_WkOther.map.+=((job.get("id").toString, job.get("name").toString))
      } else {
        NationalData_WkOther.array(2) = NationalData_WkOther.map.get(job.get("pid").toString).get
        NationalData_WkOther.map.+=((job.get("id").toString, job.get("name").toString));
        NationalData_WkOther.array(0) = job.get("name").toString;
      }
    }

  }

  def Jparse(string: String) = {
    val jar = JSONArray.fromObject(string)
    for (i <- 0 to jar.size - 1) {
      val job = JSONObject.fromObject(jar.get(i))
      if (job.get("isParent").toString == "true") {
        NationalData_WkOther.map.+=((job.get("id").toString, job.get("name").toString))
        HttpPost(job.get("id").toString)
      } else {
        NationalData_WkOther.map.+=((job.get("id").toString, job.get("name").toString));
        NationalData_WkOther.array(2) = NationalData_WkOther.map.get(job.get("pid").toString).get
        NationalData_WkOther.array(0) = job.get("name").toString;
        NationalData_WkOther.classifyMap.foreach(e => HttpGet(e._1, job.get("id").toString))
      }
    }

  }

  def addClassifyId(string: String) = {
    val jo = JSONObject.fromObject(string).get("returndata")
    val jc = JSONArray.fromObject(jo).get(0)

    val jd = JSONObject.fromObject(jc).get("nodes")
    val je = JSONArray.fromObject(jd)
    for (i <- 0 to je.size - 1) {
      val job = JSONObject.fromObject(je.get(i))
      val code= (job.get("code") toString, job.get("name") toString)
      NationalData_WkOther.classifyMap += (code)
      NationalData_WkOther.classifyMap.foreach(e => println(e._1 + ":" + e._2))
    }
  }

  def addClassifyId2(string: String) = {
    val jo = JSONObject.fromObject(string).get("returndata")
    val jc = JSONArray.fromObject(jo).get(0)
    val jd = JSONObject.fromObject(jc).get("nodes")
    val je = JSONArray.fromObject(jd)
    for (i <- 0 to je.size - 1) {
      val job = JSONObject.fromObject(je.get(i))
      NationalData_WkOther.cityMap += ((job.get("code") toString, job.get("name") toString))
      NationalData_WkOther.cityMap.foreach(e => println(e._1 + ":" + e._2))
    }
  }


  def HttpGet(str: String, tag: String): Unit = {
    NationalData_WkOther.array(3) = NationalData_WkOther.classifyMap.get(str).get
    val url = makeUri(str, tag)
    println(url)
    val hg = new HttpGet(url)
    hg.setHeaders(unit.setheader("data.stats.gov.cn", uri))

    hg.setHeader("Cookie", NationalData_Work.yzma.split(":")(1))

    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    println(content)
    IJsonParse(content)
    Thread.sleep(new Random(6000).nextInt(10000))
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
      NationalData_WkOther.map.+=((JSONObject.fromObject(zbsi).get("code").toString, makeTag(JSONObject.fromObject(zbsi).get("name") toString, JSONObject.fromObject(zbsi).get("unit").toString)
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
        NationalData_WkOther.array(1) = NationalData_WkOther.cityMap.get(valStr).get
        NationalData_WkOther.array(4) = JSONObject.fromObject(ar.get(2)).get("valuecode").toString
        NationalData_WkOther.array(5) = jobj.get("strdata").toString
        addSql()
      }
    }
  }

  def makeTag(tag: String, unit: String) = {
    if (unit.length > 0) {
      val mTag = tag + "(" + unit + ")"
      mTag
    } else {
      tag
    }
  }

  def addSql() = {
    NBS_ConnectSql(NationalData_WkOther.dataType, NationalData_WkOther.array)
  }


  def makeUri(cid: String, id: String) = {
      val uri = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode="+dbcode+"&rowcode="+rowcode+"&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22reg%22%2C%22valuecode%22%3A%22" + cid + "%22%7D%5D&dfwds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22" + id + "%22%7D%5D"
    //val uri = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=" + dbcode + "&rowcode=" + rowcode + "&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22" + cid + "%22%7D%5D&dfwds=%5B%5d"
    // "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=fsyd&rowcode=zb&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22reg%22%2C%22valuecode%22%3A%22130000%22%7D%5D&dfwds=%5B%5D"
    uri
  }

}

object NationalData_WkOther {
  var dataType = ""

  var array = new Array[String](6)
  val map = new scala.collection.mutable.HashMap[String, String]
  val classifyMap = new scala.collection.mutable.HashMap[String, String]
  val cityMap = new scala.collection.mutable.HashMap[String, String]


}