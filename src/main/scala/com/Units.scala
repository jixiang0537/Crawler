package com.IClrawler

import java.io._


import com.IClrawler.SQL.NBS_ConnectSql
import com.IClrawler.TaskInfo.NBS_TaskLink
import com.IClrawler.TaskWork.NationalData_Work
import net.sf.json
import net.sf.json.{JSONObject, JSONArray}
import org.apache.http.Header
import org.apache.http.impl.client.{DefaultConnectionKeepAliveStrategy, HttpClients}
import org.apache.http.message.BasicHeader
import scala.io.Source


/**
 * Created by cz on 2015/8/7.
 */
class Units {
  def setheader(host: String,referer:String): Array[Header] = {
    val ret = new Array[Header](9)
    ret(0) = new BasicHeader("Host", host)
    ret(1) = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0")
    ret(2) = new BasicHeader("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*")
    ret(3) = new BasicHeader("Accept-Language", "zh-CN")
    ret(4) = new BasicHeader("Accept-Encoding", "gzip, deflate")
    ret(5) = new BasicHeader("Connection", "keep-Alive")
    ret(6) = new BasicHeader("Catch-Control", "no-cache")
    ret(7) = new BasicHeader("DNT", "1")
    ret(8) = new BasicHeader("Referer", referer)
    ret
  }

  def addIp(path: String) = {
    Source.fromFile(new File(path)).getLines().foreach(println(_))
  }

  def textSave(s: String, name: Int) = {
    val FileName = {
      "E:\\CricData\\" + name + "link.txt"
    }
    val fos = new FileOutputStream(new File(FileName))
    val sToByt: Array[Byte] = s.getBytes()
    fos.write(sToByt, 0, s.length - 1)
  }

  def JParse(array: Array[String], str: String,int: Int) = {
    var num = 0
    var num2 = -1
    val nbsLk = new NBS_TaskLink
    var saveData = "null"
    val js = JSONObject.fromObject(str)
    val js3 = js.getString("returndata")
    val js4 = JSONObject.fromObject(js3)
    // println(js4.get("datanodes"))
    val jar = js4.optJSONArray("datanodes")
    for (i <- 0 to jar.size() - 1) {
      val jss = JSONObject.fromObject(jar.get(i))
      //  println(jss)
      //  println(jss.getString("data"))
      val va = jss.getJSONArray("wds")
      val date = JSONObject.fromObject(va.get(1)).get("valuecode")
      val data = JSONObject.fromObject(jss.getString("data"))
      if (data.getString("hasdata") != "false") saveData = data.getString("strdata")
      num += 1
      if (num%int ==1)num2+=1
      if (num2>nbsLk.tagList(array(5).toInt).length-1) {
        num=0;num2= 0
      }
     // Units.intoSql.insetDataCric(array(1), array(2), array(3), array(4),nbsLk.tagList(array(5).toInt)(num2), date.toString, saveData)
      saveData ="null"
    }
  }

  //  def parser(str: String) = {
  //    val parser = JSONValue.parse(str)
  //    val parserObject = parser.asInstanceOf[JSONObject]
  //    parserObject.get()
  //}
  //  def dataSelect(string: String)={
  //     if (cityList1.contains(string){} else
  //
  //  }
}

object Units {
  def intoSql = {
    val nbs = new NBS_ConnectSql
    nbs
  }

  val IHttpclient = HttpClients.custom().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).build()
}