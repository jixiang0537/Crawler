package com.IClrawler


import java.io.{Reader, BufferedReader, InputStreamReader}
import java.util

import com.IClrawler.SQL.realestate_ConnectSql
import com.IClrawler.TaskWork.Worker
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpPost, HttpGet}
import org.apache.http.message.BasicNameValuePair
import org.jsoup.Jsoup
import scala.io.Source
import scala.util.Random


/**
 * Created by dell on 2015/10/20.
 */
class HttpGetCom {

  var num = "1"



  def jsoupParser(string: String, list: List[List[String]],date:String) = {
    val jp = Jsoup.parse(string)
    val rc= new realestate_ConnectSql
    val tList = jp.getElementsByTag("tbody").select("tr").select("tbody").select("tr")
    for (i <- 4 until tList.size()) {
      val cList = tList.get(i).select("td")
      for (i <- 0 until cList.size()) {
        val c = cList.get(i).text()
        if (i > 0){
          rc.insetData(cList.get(0).text(),list(i - 1)(0),list(i - 1)(1),list(i - 1)(2),date,c)
        }
      }
    }
  }
  var uri = "http://www.realestate.cei.gov.cn/admin/abc/login.aspx"
  var loUri = "http://www.realestate.cei.gov.cn/admin/abc/login.aspx?id=%24%24%24%24%24%24w1%24mw%24mw%24201509%24201509%24%24&page=%24traden%24br1"
  var yzma = ""
  var viewr = ""
  var vie = ""
  var eve = ""

  //设置cookie
  def getNetKey(uri1: String = uri) = {
    val hg = new HttpGet(uri1)
    val unit = new Units
    hg.setHeaders(unit.setheader("www.realestate.cei.gov.cn",uri1))
    hg.setHeader("Cookie", HttpGetCom.cookie)
    val response = Units.IHttpclient.execute(hg)
    val content = Source.fromInputStream(response.getEntity.getContent, "gb2312").mkString
    val doc = Jsoup.parse(content)
    yzma = doc.getElementById("yzm").text()
    viewr = doc.getElementsByAttributeValue("name", "__VIEWSTATEGENERATOR").`val`().trim
    vie = doc.getElementsByAttributeValue("name", "__VIEWSTATE").`val`().trim
    eve = doc.getElementsByAttributeValue("name", "__EVENTVALIDATION").`val`().trim


  }

  //设置请求页面
  def httpGet2(uri1: String = uri,date:String, list: List[List[String]]) = {
    val hg = new HttpGet(uri1)
    val unit = new Units
    hg.setHeaders(unit.setheader("www.realestate.cei.gov.cn",uri1))
    println(HttpGetCom.cookie)
    hg.setHeader("Cookie", HttpGetCom.cookie)

    val response = Units.IHttpclient.execute(hg)
    //    val content = Source.fromInputStream(response.getEntity.getContent,"gb2312")
    val bf = new BufferedReader(new InputStreamReader(response.getEntity.getContent, "gb2312"), 30000)
    val content = bf.lines().iterator()
    var str= ""
    while (content.hasNext)str+=content.next()
   jsoupParser(str,list,date)
    str =""
    Thread.sleep(new Random(6000).nextInt(10000))
  }

  //模拟登陆
  def httpPost = {

    val httppost = new HttpPost(loUri)
    val unit = new Units
    httppost.setHeaders(unit.setheader("www.realestate.cei.gov.cn","http://www.realestate.cei.gov.cn/admin/abc/login.aspx"))

    val ar = new util.ArrayList[BasicNameValuePair]

    ar.add(new BasicNameValuePair("password", "anfle2014"))
    ar.add(new BasicNameValuePair("username", "anflesh"))
    ar.add(new BasicNameValuePair("yzma", yzma))
    ar.add(new BasicNameValuePair("__EVENTVALIDATION", eve))
    ar.add(new BasicNameValuePair("__VIEWSTATE", vie))
    ar.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", viewr))
    ar.add(new BasicNameValuePair("ImageButton1.x", "0"))
    ar.add(new BasicNameValuePair("ImageButton1.y", "0"))

    httppost.setEntity(new UrlEncodedFormEntity(ar,"gb2312"))
    val response = Units.IHttpclient.execute(httppost)
    val content = Source.fromInputStream(response.getEntity.getContent, "gb2312").mkString
    HttpGetCom.cookie = response.getFirstHeader("Set-Cookie").toString.split(":")(1)
    //    println(response.getAllHeaders.foreach(println(_)))

  }
}

object HttpGetCom {
  var realestateType= ""
  var cookie = "ASP.NET_SessionId=2hapxih5pljqidhlwfdb1wkw; path=/; HttpOnly"
}
