package com.IClrawler

import java.io.{IOException, InputStreamReader, BufferedReader}
import java.net.URL
import java.util

import Exception.{NullResponseException, WebPageGetException}
import org.apache.http.{HttpHost, Header}
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.impl.client.{BasicCookieStore, DefaultConnectionKeepAliveStrategy, HttpClients}
import org.apache.http.message.BasicNameValuePair

import scala.io.Source

/**
 * Created by dell on 2015/12/15.
 */
trait httpCom {


  def httpPost(postUri: String, unitAr: Array[Header], ar: util.ArrayList[BasicNameValuePair], encoding: String = "utf-8"): String = {
    val requestConfig = RequestConfig.custom().setConnectTimeout(100000).
      setConnectionRequestTimeout(100000).setSocketTimeout(100000).build()
    val IHttpclient = HttpClients.custom()
      .setDefaultRequestConfig(requestConfig).build()

    val hg = new HttpPost(postUri)
    hg.setHeaders(unitAr)
    hg.setEntity(new UrlEncodedFormEntity(ar, "utf-8"))

    val response = try {
      IHttpclient.execute(hg)
    } catch {
      case ex: IOException =>throw new IOException(ex.getMessage)
      case ex: Throwable =>throw new Throwable(ex.getMessage)
    }
    response.getStatusLine.getStatusCode match {
      case 200 => {
        val content = Source.fromInputStream(response.getEntity.getContent, encoding).mkString
        return content
      }
      case x: Int => throw new WebPageGetException(x.toString,postUri)
    }
    //    val content = Source.fromInputStream(response.getEntity.getContent, encoding).mkString
    //    content
  }

  def httpPostProxy(postUri: String, unitAr: Array[Header], ar: util.ArrayList[BasicNameValuePair], encoding: String = "utf-8", Proxy: String): String = {
    val hProxy = new HttpHost(Proxy, 80, "http");
    val requestConfig = RequestConfig.custom().setConnectTimeout(100000).
      setConnectionRequestTimeout(100000).setSocketTimeout(100000).setProxy(hProxy)
      .build()
    val IHttpclient = HttpClients.custom()
      .setDefaultRequestConfig(requestConfig).build()

    val hg = new HttpPost(postUri)
    hg.setHeaders(unitAr)
    hg.setEntity(new UrlEncodedFormEntity(ar, "utf-8"))

    val response = try {
      IHttpclient.execute(hg)
    } catch {
      case ex: IOException =>throw new IOException(ex.getMessage)
      case ex: Throwable =>throw new Throwable(ex.getMessage)
    }
    response.getStatusLine.getStatusCode match {
      case 200 => {
        val content = Source.fromInputStream(response.getEntity.getContent, encoding).mkString
        return content
      }
      case x: Int => throw new WebPageGetException(x.toString,postUri)
    }

  }

  def httpGet(getUri: String, unitAr: Array[Header], encoding: String = "utf-8"): String = {

    val requestConfig = RequestConfig.custom().setConnectTimeout(100000).
      setConnectionRequestTimeout(100000).setSocketTimeout(100000).build()
    val IHttpclient = HttpClients.custom()
    .setDefaultRequestConfig(requestConfig)
 //   setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
    .build()
    val hg = new HttpGet(getUri)
    hg.setHeaders(unitAr)
    val response = try {
      IHttpclient.execute(hg)
    } catch {
      case ex: IOException => return s"无法从  $getUri  获取响应"
      case ex: Throwable => return "出现异常"
    }
    response.getStatusLine.getStatusCode match {
      case 200 => {
        val content = try {
          Source.fromInputStream(response.getEntity.getContent, encoding).mkString
        } catch {
          case ex: Exception => throw new NullResponseException(getUri)
        }
        content.toString
      }
      case x: Int => throw new WebPageGetException(x.toString,getUri)
    }


  }

  def httpGetProxy(getUri: String, unitAr: Array[Header], encoding: String = "utf-8", Proxy: String, port: Int = 80): String = {

    val hProxy = new HttpHost(Proxy, port, "http");

    val requestConfig = RequestConfig.custom().setConnectTimeout(100000).
      setConnectionRequestTimeout(100000).setSocketTimeout(100000)
      .setProxy(hProxy)
      .build()

    val IHttpclient = HttpClients.custom()
      .setDefaultRequestConfig(requestConfig)
      .build()

    val hg = new HttpGet(getUri)
    hg.setHeaders(unitAr)
    val response = try {
      IHttpclient.execute(hg)
    } catch {
      case ex: IOException =>throw new IOException(ex.getMessage)
      case ex: Throwable =>throw new Throwable(ex.getMessage)
    }
    response.getStatusLine.getStatusCode match {
      case 200 => {
        val content = try {
          Source.fromInputStream(response.getEntity.getContent, encoding).mkString
        } catch {
          case ex: Throwable => throw new Throwable(ex.getMessage)
        }
        return content
      }
      case x: Int =>throw new WebPageGetException(x.toString,getUri)
    }


  }


}

object httpCom {

}