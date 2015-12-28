package com.IClrawler

import java.io.{InputStreamReader, BufferedReader}
import java.nio.charset.MalformedInputException
import java.util

import org.apache.http.Header
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.message.BasicNameValuePair

import scala.io.Source

/**
 * Created by dell on 2015/12/15.
 */
trait httpCom {
  def httpPost(postUri: String, unitAr: Array[Header], ar: util.ArrayList[BasicNameValuePair]) = {
    val hg = new HttpPost(postUri)
    hg.setHeaders(unitAr)
    hg.setEntity(new UrlEncodedFormEntity(ar, "utf-8"))
    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    content
  }

  def httpGet(uri: String, unitAr: Array[Header]): String = {
    val hg = new HttpGet(uri)
    hg.setHeaders(unitAr)
    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    content

  }
}
