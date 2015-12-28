package com.IClrawler

import java.io.{InputStreamReader, BufferedReader}
import java.util


import org.apache.http.Header
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.message.BasicNameValuePair
import org.jsoup.Jsoup

import scala.collection.mutable.ArrayBuffer


/**
 * Created by dell on 2015/12/1.
 */
class httpTest extends httpCom {
  override def httpPost(postUri: String, unitAr: Array[Header], ar: util.ArrayList[BasicNameValuePair]) = {
    val hg = new HttpPost(postUri)
    hg.setHeaders(unitAr)
    hg.setEntity(new UrlEncodedFormEntity(ar, "utf-8"))
    val response = Units.IHttpclient.execute(hg)
    val br = new BufferedReader(new InputStreamReader(response.getEntity.getContent, "gb2312"), 30000)
    val content = new StringBuilder
    while (br.readLine() != null) {
      content.append(br.readLine)
    }
    content.toString
  }

  override def httpGet(uri: String, unitAr: Array[Header]) = {
    val hg = new HttpGet(uri)
    hg.setHeaders(unitAr)
    val response = Units.IHttpclient.execute(hg)
    val br = new BufferedReader(new InputStreamReader(response.getEntity.getContent, "gb2312"), 30000)
    val content = new StringBuilder
    while (br.readLine() != null) {
      content.append(br.readLine)
    }
    content.toString

  }

  def josupParse(content: String) = {
    val doc = Jsoup.parse(content)
    //  val yzma = doc.getElementById("yzm").text()
    //  val viewr = doc.getElementsByAttributeValue("name", "__VIEWSTATEGENERATOR").`val`().trim
    val vie = doc.getElementsByAttributeValue("name", "__VIEWSTATE").`val`().trim
    val eve = doc.getElementsByAttributeValue("name", "__EVENTVALIDATION").`val`().trim
    val ar = new ArrayBuffer
    ar+(vie)
    ar+(eve)
    ar.toArray
  }
}






