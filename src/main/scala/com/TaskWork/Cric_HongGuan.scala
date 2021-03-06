package com.IClrawler.TaskWork

import java.net.URLEncoder
import java.util
import com.IClrawler.Manage.Cric_Task
import com.IClrawler.SQL.ConectionSql
import com.IClrawler.TaskInfo.Cric_DataInfo
import net.sf.json.JSONObject
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair

import scala.io.Source
import scala.util.Random
import scala.util.matching.Regex.Match

/**
 * Created by dell on 2015/10/15.
 */
class Cric_HongGuan(task: Cric_DataInfo, array: Array[String], currentPage: Int = 1) {
  private var pageNum = currentPage
  val IHttpclient = HttpClients.createDefault()
  val requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
  //设置请求和传输超时时间
  val cookie = Cric_Task.cricCookie
  try {
    val referer = "http://test.2015.app.cric.com/Statistic/StatisticCenter/StatisticCenter?CityName=" + URLEncoder.encode(task.num_city, "utf-8") + "&MenuKey=150001"

    val cricType = array(0);
    val taskType = array(1);
    val taslAttribute = array(3);
    val cityName = task.num_city
    val key: String = array(2)
    val dateType = array(3)
    val dateA = array(5)
    val dateZ = array(6)

    val httppost = new HttpPost("http://test.2015.app.cric.com/Statistic/StatisticCenter/GetHongguan")
    httppost.addHeader("Host", "test.2015.app.cric.com")
    httppost.setConfig(requestConfig)
    httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko")
    httppost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
    httppost.addHeader("Accept-Encoding", "gzip, deflate")
    httppost.addHeader("Connection", "Keep-Alive")
    httppost.addHeader("Accept-Language", "zh-CN")
    httppost.addHeader("DNT", "1")
    httppost.addHeader("Cache-Control", "no-cache")
    httppost.addHeader("X-Requested-With", "XMLHttpRequest")
    httppost.addHeader("Referer", referer)
    httppost.addHeader("Content-Type", "application/x-www-form-urlencoded")
    httppost.addHeader("Cookie", cookie)
    val ar = new util.ArrayList[BasicNameValuePair]
    ar.add(new BasicNameValuePair("key", key))
    ar.add(new BasicNameValuePair("dateType", dateType))
    ar.add(new BasicNameValuePair("StatisticCityName", cityName))
    ar.add(new BasicNameValuePair("dateA", dateA))
    ar.add(new BasicNameValuePair("dateZ", dateZ))

    ar.add(new BasicNameValuePair("hascity", "0"))
    ar.add(new BasicNameValuePair("currentPage", currentPage.toString))
    ar.add(new BasicNameValuePair("displaySize", "10"))
    //   if (array.length >= 2) ar.add(new BasicNameValuePair("MdxSource", mdxSource))
    httppost.setEntity(new UrlEncodedFormEntity(ar, "utf-8"))
    Worker.o += 1
    val response = IHttpclient.execute(httppost)
    val content = Source.fromInputStream(response.getEntity.getContent, "utf-8").mkString
    println(content)


    if (content.length > 0) {
      Worker.i += 1
      println(cityName, cricType, taskType, taslAttribute)
      val cnsql = new ConectionSql
      cnsql.insetData(task.num_city, cricType, taskType, taslAttribute, content)
    }
    Cric_HongGuan.hgCricJP(content) match {
      case true =>
        pageNum += 1
        Thread.sleep(new Random(10000).nextInt(25000));
        new Cric_HongGuan(task, array, pageNum)
      case _ =>
    }

  } catch {
    case ex: Exception => print(ex.getMessage + "出现异常");
      Worker.retry += 1;
      if (Worker.retry % 3 != 0) {
        println("重试当前连接" + task.num_city + array(2) + array(3) + array(4));
        Thread.sleep(new Random(4000).nextInt(6000));
        new Cric_HongGuan(task, array, currentPage)
      }
      else {
        println("失败次数过多丢弃当前连接")
      }
  }
  println("")

  Thread.sleep(new Random(6000).nextInt(8000))
  if (Worker.o % 3 == 0) Thread.sleep(new Random(500).nextInt(10000))


}

object Cric_HongGuan {
  def hgCricJP(string: String) = {
    val jo = JSONObject.fromObject(string).get("pageModel")
    val jo1 = JSONObject.fromObject(jo).get("currentPage")
    val jo2 = JSONObject.fromObject(jo).get("dataCount")
    val bl = {
      if (jo1.toString < jo2.toString) {
        true
      } else {
        false
      }
    }
    bl
  }

}

