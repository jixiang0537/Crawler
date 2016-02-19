package com

import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar

import akka.actor.{Props, ActorSystem, Actor}
import com.IClrawler.Manage.{Cric_Task, NBS_Task}
import com.IClrawler.TaskWork.{NationalData_WkOther, NationalData_Worker2}
import com.IClrawler.{httpTest, Units, httpCom}
import com.TaskWork.NBS_sort
import net.sf.json.{JSONObject, JSON}
import org.apache.http.Header
import org.apache.http.message.BasicHeader
import org.jsoup.Jsoup

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.sys.Prop

/**
 * Created by dell on 2016/1/12.
 */
object scalaMain {
  def main(args: Array[String]): Unit = {
    val nbs = new NBS_sort
    nbs .task

    //    val tp = new textP
    //    val us = Units.setheader("ip.chinaz.com")
    // val context = tp.(, us.toArray)
    //
    //     val context = tp.httpGetProxy("http://ip.chinaz.com/ipbatch",us.toArray,"utf-8","182.92.1.222" ,8123)
    //    printf(context)


    //    val jp = Jsoup.parse(loadFile)
    //    val tableCon = jp.select("td[id=tdContent]").select("tbody tbody")
    //    for (i <- 0 until tableCon.size()) {
    //      println(tableCon.get(0))
    //    }

  }

  def loadFile: String = {
    val path = "E:\\Cz\\1.txt"
    val str = Source.fromFile(new File(path)).mkString //getLines().foreach(println(_)))
    str
  }

}

  class text extends httpCom

  //    val demo = new demo
  //    val us = new us
  //    val dc = demo httpGet("http://www.landchina.com/default.aspx?tabid=386&comname=default&wmguid=75c72564-ffd9-426a-954b-8ac2df0903b7&recorderguid=52364e54-1801-4412-a01e-0e59b4af405b", us.setheader("www.landchina.com", ""))
  //    println(dc)
  //  }
  //
  //
  //}
  //
  //class demo extends httpTest {
  //
  //}
  //
  //class us extends Units {
  //  override def setheader(host: String, referer: String): Array[Header] = {
  //    referer match {
  //      case "" => {
  //        val ret = new Array[Header](9)
  //        ret(0) = new BasicHeader("Host", host)
  //        ret(1) = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0")
  //        ret(2) = new BasicHeader("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*")
  //        ret(3) = new BasicHeader("Accept-Language", "zh-CN")
  //        ret(4) = new BasicHeader("Accept-Encoding", "gzip, deflate")
  //        ret(5) = new BasicHeader("Connection", "keep-Alive")
  //        ret(6) = new BasicHeader("Catch-Control", "no-cache")
  //        ret(7) = new BasicHeader("DNT", "1")
  //        ret
  //      }
  //
  //    }

  class textP extends httpCom