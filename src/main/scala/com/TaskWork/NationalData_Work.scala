package com.IClrawler.TaskWork

import java.io.{BufferedReader, InputStreamReader}
import java.util

import com.IClrawler.SQL.NBS_ConnectSql
import com.IClrawler.TaskInfo.NBS_TaskLink
import com.IClrawler.Units
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.message.BasicNameValuePair
import org.jsoup.Jsoup

import scala.io.Source
import scala.util.Random

/**
 * Created by dell on 2015/10/20.
 */
class NationalData_Work {
  var uri = "http://data.stats.gov.cn/easyquery.htm?cn=A01"
  var loUri = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=hgyd&rowcode=zb&colcode=sj&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22200001-201510%22%7D%5D&k1=1446452827124"
  val baseUri = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=hgyd&rowcode=zb&colcode=sj&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A010103%22%7D%5D&k1=1446618073066"

  def httpCookie = {
    val hg = new HttpGet("http://data.stats.gov.cn/easyquery.htm?cn=A01")
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn",uri))
    hg.setHeader("Referer", uri)
    hg.setHeader("DNT", "1")
    val response = Units.IHttpclient.execute(hg)
    NationalData_Work.yzma = response.getFirstHeader("Set-Cookie").toString
  }

  def firstHttpGet(string: String) = {
    val hg = new HttpGet(string)
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn",uri))
    hg.setHeader("Referer", uri)
    hg.setHeader("DNT", "1")
    hg.setHeader("Cookie", NationalData_Work.yzma.split(":")(1))
    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    Thread.sleep(new Random(6000).nextInt(10000))

  }

  def httpGet(array: Array[String], month: Int) = {
    firstHttpGet(array(0))
    val hg = new HttpGet(NBS_TaskLink.baseDateUri)
    val unit = new Units
    hg.setHeaders(unit.setheader("data.stats.gov.cn",uri))
    hg.setHeader("Referer", uri)
    hg.setHeader("DNT", "1")
    hg.setHeader("Cookie", NationalData_Work.yzma.split(":")(1))


    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    array.foreach(print(_))
    print(content)
    val units = new Units
    units.JParse(array, content, month)

  }

}

object NationalData_Work {
  var cookie = "ASP.NET_SessionId=2hapxih5pljqidhlwfdb1wkw; path=/; HttpOnly"
  var yzma: String = ""

}
