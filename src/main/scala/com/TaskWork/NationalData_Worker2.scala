package com.IClrawler.TaskWork

import java.util

import com.IClrawler.SQL.NBS_ConnectSql
import com.IClrawler.TaskInfo.NBS_TaskLink
import com.IClrawler.Units
import net.sf.json.{JSONArray, JSONObject}
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpPost, HttpGet}
import org.apache.http.message.BasicNameValuePair

import scala.io.Source
import scala.util.Random

/**
 * Created by dell on 2015/11/11.
 */
class NationalData_Worker2 {
  var uri = "http://data.stats.gov.cn/easyquery.htm?cn=C01"
  val postUri = "http://data.stats.gov.cn/easyquery.htm"
  var dbcode = "hgnd"
  var wdcode = "zb"
  var rowcode = "zb"
  var dfwds = "[{\"wdcode\":\"reg\",\"valuecode\":\"800001\"}]"
  val unit = new Units

  def httpCookie = {
    val hg = new HttpGet("http://data.stats.gov.cn/easyquery.htm?cn=A01")
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn",uri))

    val response = Units.IHttpclient.execute(hg)
    NationalData_Work.yzma = response.getFirstHeader("Set-Cookie").toString
  }

  def firstHttpGet(string: String) = {
    val hg = new HttpGet(string)
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn",uri))

    hg.setHeader("Cookie", NationalData_Work.yzma.split(":")(1))
    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    Thread.sleep(new Random(6000).nextInt(10000))

  }

  def HttpPost(id: String): Unit = {
    val hg = new HttpPost(postUri)
    hg.setHeaders(unit.setheader("data.stats.gov.cn",uri))
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
    Jparse(content)
    Thread.sleep(new Random(6000).nextInt(10000))
  }

  def Jparse(string: String) = {
    val jar = JSONArray.fromObject(string)
    for (i <- 0 to jar.size - 1) {
      val job = JSONObject.fromObject(jar.get(i))
      if (job.get("isParent").toString == "true") {
        NationalData_WkOther.map.+=((job.get("id").toString, job.get("name").toString))
        HttpPost(job.get("id").toString)
      } else {
        NationalData_WkOther.array(2) = NationalData_WkOther.map.get(job.get("pid").toString).get
        NationalData_WkOther.map.+=((job.get("id").toString, job.get("name").toString));
        NationalData_WkOther.array(0) = job.get("name").toString;
        HttpGet(job.get("id").toString)
      }
      //      println(job.get("id"))
      //      println(job.get("name"))
    }

  }

  def HttpGet(str: String): Unit = {
    val hg = new HttpGet(makeUri(str))
    hg.setHeaders(unit.setheader("data.stats.gov.cn",uri))
    hg.setHeader("Referer", uri)
    hg.setHeader("DNT", "1")
    hg.setHeader("Cookie", NationalData_Work.yzma.split(":")(1))

    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
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
        NationalData_WkOther.array(1) = NationalData_WkOther.map.get(JSONObject.fromObject(ar.get(0)).get("valuecode").toString).get
        NationalData_WkOther.array(3) = JSONObject.fromObject(ar.get(1)).get("valuecode").toString
        NationalData_WkOther.array(4) = jobj.get("strdata").toString
        addSql()
        //       print("ID: " + JSONObject.fromObject(ar.get(0)).get("valuecode"))
        //        print("时间: " + JSONObject.fromObject(ar.get(1)).get("valuecode"))
        //        println("数据:" + jobj.get("strdata"))

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
    val csql = new NBS_ConnectSql
    csql.insetDataCric(NationalData_WkOther.array(0), NationalData_WkOther.array(1), NationalData_WkOther.array(2), NationalData_WkOther.array(3), NationalData_WkOther.array(4),NationalData_WkOther.array(5))

  }

  def makeUri(id: String) = {

    val uri = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=" + dbcode + "&rowcode=" + rowcode + "&colcode=sj&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22" + id + "%22%7D%5D"
    uri
  }

}

object NationalData_Worker2 {
  var dataType = ""

  var array = new Array[String](5)
  val map = new scala.collection.mutable.HashMap[String, String]
}