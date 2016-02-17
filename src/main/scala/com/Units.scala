package com.IClrawler

import java.io._
import java.text.SimpleDateFormat
import java.util.Calendar


import com.IClrawler.SQL.NBS_ConnectSql
import com.IClrawler.TaskInfo.NBS_TaskLink
import com.IClrawler.TaskWork.NationalData_Work
import net.sf.json
import net.sf.json.{JSONObject, JSONArray}
import org.apache.http.Header
import org.apache.http.impl.client.{DefaultConnectionKeepAliveStrategy, HttpClients}
import org.apache.http.message.BasicHeader
import scala.collection.mutable.ArrayBuffer
import scala.io.Source


/**
 * Created by cz on 2015/8/7.
 */
class Units {

  def deteTask(sDate: Int, eDate: Int): Array[String] = {
    var year = sDate
    val sdf = new SimpleDateFormat("yyyy-MM-dd");
    val ar = new ArrayBuffer[String]();
    //月份计数
    val cal = Calendar.getInstance(); //获得当前日期对象
    cal.clear();
    for (ye <- sDate to eDate) {
      for (month <- 1 to 12) {
        //清除信息
        cal.set(Calendar.YEAR, ye);
        cal.set(Calendar.MONTH, month - 1); //1月从0开始
        cal.set(Calendar.DAY_OF_MONTH, 1); //设置为1号,当前日期既为本月第一天
        ar += sdf.format(cal.getTime())
        val count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        //   System.out.println( count);

        for (i <- 0 to count - 2) {
          cal.add(Calendar.DAY_OF_MONTH, +1);
          ar += sdf.format(cal.getTime()).toString
        }
      }
      //      m = 1
      //      year += 1;
    }

    ar.toArray[String]
  }

  def setheader(host: String, referer: String = ""): Array[Header] = {
    referer match {
      case "" => {
        val ret = new Array[Header](8)
        ret(0) = new BasicHeader("Host", host)
        ret(1) = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0")
        ret(2) = new BasicHeader("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*")
        ret(3) = new BasicHeader("Accept-Language", "zh-CN")
        ret(4) = new BasicHeader("Accept-Encoding", "gzip, deflate")
        ret(5) = new BasicHeader("Connection", "keep-Alive")
        ret(6) = new BasicHeader("Catch-Control", "no-cache")
        ret(7) = new BasicHeader("DNT", "1")
        ret
      }
      case _ => {
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
    }

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

  def writeFile(content: String, name: Int) = {
    try {
      val directory = new File("..");
      val path = {
        directory.getCanonicalPath + "\\" + name + ".txt"
      }
      val file = new File(path)
      if (!file.exists()) {
        file.createNewFile();
      }

      val fw = new FileWriter(file.getAbsoluteFile());
      val bw = new BufferedWriter(fw);
      bw.write(content);
      bw.close();

    } catch {
      case e: Exception => println(e.getMessage)
    }


  }

  def JParse(array: Array[String], str: String, int: Int) = {
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
      if (num % int == 1) num2 += 1
      if (num2 > nbsLk.tagList(array(5).toInt).length - 1) {
        num = 0;
        num2 = 0
      }
      // Units.intoSql.insetDataCric(array(1), array(2), array(3), array(4),nbsLk.tagList(array(5).toInt)(num2), date.toString, saveData)
      saveData = "null"
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
  def setheader(host: String, referer: String = ""): ArrayBuffer[Header] = {
    val ret = new ArrayBuffer[Header]()
    ret += new BasicHeader("Host", host)
    ret += new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0")
    ret += new BasicHeader("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*")
    ret += new BasicHeader("Accept-Language", "zh-CN")
    ret += new BasicHeader("Accept-Encoding", "gzip, deflate")
    ret += new BasicHeader("Connection", "keep-Alive")
    ret += new BasicHeader("Catch-Control", "no-cache")
    ret += new BasicHeader("DNT", "1")

    referer.size match {
      case x if x > 0 => {
        ret += new BasicHeader("referer", referer)
      }
      case _ =>

    }
    ret
  }

  def intoSql = {
    val nbs = new NBS_ConnectSql
    nbs
  }

  val IHttpclient = HttpClients.custom().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).build()

}