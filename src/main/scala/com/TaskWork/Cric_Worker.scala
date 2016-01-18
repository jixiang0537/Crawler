package com.IClrawler.TaskWork

import java.net.URLEncoder
import java.util
import com.IClrawler.Manage.Cric_Task
import com.IClrawler.SQL.ConectionSql
import com.IClrawler.TaskInfo.Cric_DataInfo
import org.apache.http.Header
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.{BasicHeader, BasicNameValuePair}

import scala.io.Source
import scala.util.Random

/**
 * Created by cz on 2015/8/7.
 */
class Worker(task: Cric_DataInfo, array: Array[String]) {
  //  var number: Int = 0
  //  var key: String =""
  //
  //  def httpGet: CloseableHttpResponse = {
  //
  //    val uri = "http://test.2015.app.cric.com/Statistic/StatisticCenter/StatisticCenter_House_GetResults"
  //    val wholeNumber: String = if (number != 0) "s=" + number else ""
  //    val wholeKey: String = "q=" + key
  //    val sb = new StringBuffer()
  //    val wholeUri = {
  //      sb.append(uri)
  //        .append(wholeNumber)
  //        .append(wholeKey)
  //        .toString
  //    }
  //    val httpget = new HttpPost(uri)
  //    httpget.setHeaders(Worker.setheader)
  //    httpget.addHeader("Cookie", "Hm_lvt_dca78b8bfff3e4d195a71fcb0524dcf3=1444098341; Hm_lpvt_dca78b8bfff3e4d195a71fcb0524dcf3=1444098463; cric2015=F0CE2A98E4C629F6E6CB74C79522D23A174BFBDF1D08DD700B261647A00832587365B0BC6057D65E4B701970AA7B4A047CA75844E204C96D44C6174DFC380F5F681DC2CA286368B4A125A3B1; cric2015_token=username=StQgAR15rv7+OrBYjBtTsg==&verifycode=s7/9LpCX0iCS45Nfl9RzmQ==&token=2g76gHjd8FqxEvNLvENZMJdcVDJHN4IFUT+qKHJU5tiLBBSaqwEOX0I8WAD45asu&usermobilephone=K5YeTHtdn4V26raC8MqOFA==&userid=jENN5gpXpomAU7sN+lHPi5HRFui2QzKnfGvouzckmDwOoCLDhtkVokjHOXFwscv+; BIGipServerpool_10.0.7=34013194.20480.0000; StatisticCenter=150002")
  //    val response = Worker.IHttpclient.execute(httpget)
  //    response
  //  }
  //
  //  def getContent(response: CloseableHttpResponse) = {
  //    try {
  //      val content = response.getEntity.getContent
  //      val isr = new InputStreamReader(content, "utf-8")
  //      val br = new BufferedReader(isr)
  //      val sb = new StringBuilder
  //      while (br.readLine() != null) {
  //        sb.append(br.readLine)
  //        sb.append("\n")
  //      }
  //      sb.toString
  //    } finally {
  //      response.close();
  //    }
  //  }


  //    def postajax() = {
  //
  //      val thisMean = RequestBuilder.post()
  //        .setHeader("Host", "test.2015.app.cric.com")
  //        .setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko")
  //        .setHeader("Accept", "application/json, text/javascript, */*; q=0.01")
  //        .setHeader("Accept-Encoding", "gzip, deflate")
  //        .setHeader("Connection", "Keep-Alive")
  //        .setHeader("Accept-Language", "zh-CN")
  //        .setHeader("DNT", "1")
  //        .setHeader("Cache-Control", "no-cache")
  // //       .setHeader("Origin", "http://test.2015.app.cric.com")
  //        .setHeader("X-Requested-With", "XMLHttpRequest")
  //        .setHeader("Referer", "http://test.2015.app.cric.com/Statistic/StatisticCenter/StatisticCenter?CityName=%E4%B8%8A%E6%B5%B7&MenuKey=150001&SubMenuKey=150002")
  //        .setHeader("Content-Type", "application/x-www-form-urlencoded")
  //        .setUri("http://test.2015.app.cric.com/Statistic/StatisticCenter/StatisticCenter_House_GetResults")
  //        .setHeader("Cookie","Hm_lvt_dca78b8bfff3e4d195a71fcb0524dcf3=1444198514; Hm_lpvt_dca78b8bfff3e4d195a71fcb0524dcf3=1444198564; cric2015=75DD25C2C2E4BEE9C3B5E070311C6790266D54B022616978EA5D1D15B86D97A003B9D36E7A2021175FB308CF3788DB29BEAF3F5FB8C5684F6E914C1B255343F5065F896DE4E8BEC108321108; cric2015_token=username=StQgAR15rv7+OrBYjBtTsg==&verifycode=89R6Kc620XZnyxE+joEnOw==&token=Ycflz9ch2zUTGWxcujQTOQH9winccRZaHnwW1gGR0CgbObdUpx5FTLtaVjZfZZUN&usermobilephone=K5YeTHtdn4V26raC8MqOFA==&userid=jENN5gpXpomAU7sN+lHPi5HRFui2QzKnfGvouzckmDwOoCLDhtkVokjHOXFwscv+; BIGipServerpool_10.0.7=17235978.20480.0000; StatisticCenter=150002")
  //       // .addParameter("Params","{\"Dims\":[{\"Key\":\"StatisticCenter.Dim.DailyData.RoomUsage\",\"Value\":\"%e5%95%86%e5%93%81%e6%88%bf\"},{\"Key\":\"StatisticCenter.Dim.Time.Day\",\"Value\":\"2015-09-15:2015-09-29\"},{\"Key\":\"StatisticCenter.Dim.DailyData.City\",\"Value\":\"%E4%B8%8A%E6%B5%B7\"}],\"Rows\":[\"StatisticCenter.Dim.Time.Day\"],\"Columns\":[],\"Outputs\":\"%e5%8f%af%e5%94%ae%e9%9d%a2%e7%a7%af,%e5%8f%af%e5%94%ae%e5%a5%97%e6%95%b0,%e4%be%9b%e5%ba%94%e9%9d%a2%e7%a7%af,%e4%be%9b%e5%ba%94%e5%a5%97%e6%95%b0,%e6%88%90%e4%ba%a4%e9%9d%a2%e7%a7%af,%e6%88%90%e4%ba%a4%e5%a5%97%e6%95%b0,%e6%88%90%e4%ba%a4%e5%9d%87%e4%bb%b7,%e6%88%90%e4%ba%a4%e9%87%91%e9%a2%9d\",\"RowTotal\":true,\"RowNonEmpty\":false,\"ColumnNonEmpty\":false,\"DataSource\":\"DailyData\"}")
  //       // .addParameter("Params","{\"Dims\":[{\"Key\":\"StatisticCenter.Dim.DailyData.RoomUsage\",\"Value\":\"%e5%95%86%e5%93%81%e6%88%bf\"},{\"Key\":\"StatisticCenter.Dim.Time.Day\",\"Value\":\"2015-09-15:2015-09-29\"},{\"Key\":\"StatisticCenter.Dim.DailyData.City\",\"Value\":\"%E4%B8%8A%E6%B5%B7\"}],\"Rows\":[\"StatisticCenter.Dim.Time.Day\"],\"Columns\":[],\"Outputs\":\"%e6%88%90%e4%ba%a4%e9%87%91%e9%a2%9d\",\"RowTotal\":true,\"RowNonEmpty\":false,\"ColumnNonEmpty\":false,\"DataSource\":\"DailyData\"}")
  //
  //        .addParameter("CityName", Worker.encoding("上海"))
  //        .addParameter("MenuKey", "150002")
  //        .addParameter("currentPage","1")
  //        .addParameter("displaySize","10")
  ////        val se=   new StringEntity("Params","{\"Dims\":[{\"Key\":\"StatisticCenter.Dim.DailyData.RoomUsage\",\"Value\":\"%e5%95%86%e5%93%81%e6%88%bf\"},{\"Key\":\"StatisticCenter.Dim.Time.Day\",\"Value\":\"2015-09-15:2015-09-29\"},{\"Key\":\"StatisticCenter.Dim.DailyData.City\",\"Value\":\"%E4%B8%8A%E6%B5%B7\"}],\"Rows\":[\"StatisticCenter.Dim.Time.Day\"],\"Columns\":[],\"Outputs\":\"%e6%88%90%e4%ba%a4%e9%87%91%e9%a2%9d\",\"RowTotal\":true,\"RowNonEmpty\":false,\"ColumnNonEmpty\":false,\"DataSource\":\"DailyData\"}")
  ////         se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"))
  //         .build()
  //         val response2 = Worker.IHttpclient.execute(thisMean);
  //      //   Thread.sleep(1000)
  //        response2
  //    }
  val IHttpclient = HttpClients.createDefault()
  val requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
  //设置请求和传输超时时间
  val cookie = Cric_Task.cricCookie
  try {
    val cs: String = array(0)
    val menuKey = array(1)
    val referer = "http://test.2015.app.cric.com/Statistic/StatisticCenter/StatisticCenter?CityName=" + URLEncoder.encode(task.num_city, "utf-8") + "&MenuKey=150001"
    val cityName = task.num_city
    val cricType = array(2)
    val taskType = array(3)
    val taslAttribute = array(4)
    var mdxSource = "2"
    // if (array.length>=3)  mdxSource= "MdxSource:2"
    // println("menuKey="+menuKey+ "referer="+referer +"cityName="+cityName + "参数=" +cs )
    val httppost = new HttpPost("http://test.2015.app.cric.com/Statistic/StatisticCenter/StatisticCenter_House_GetResults")
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
    ar.add(new BasicNameValuePair("Params", cs))
    ar.add(new BasicNameValuePair("CityName", cityName))
    ar.add(new BasicNameValuePair("MenuKey", menuKey))
    ar.add(new BasicNameValuePair("currentPage", "1"))
    ar.add(new BasicNameValuePair("displaySize", "10"))
    if (array.length >= 2) ar.add(new BasicNameValuePair("MdxSource", mdxSource))
    httppost.setEntity(new UrlEncodedFormEntity(ar, "utf-8"))
    Worker.o += 1
    val response = IHttpclient.execute(httppost)
    val content = Source.fromInputStream(response.getEntity.getContent,"utf-8").mkString
    println(content)
    if (content.length > 0) {
      Worker.i += 1
      println(cityName, cricType, taskType, taslAttribute)
      val cnsql = new ConectionSql
      cnsql.insetData(task.num_city, cricType, taskType, taslAttribute, content)
    }
    //    val json = JSON.parseFull(content)
    //    json match {
    //      case Some(m:Map) => m.get("") match {
    //        case Some(m:Map) => m.get("data").getOrElse().
    //      }; Worker.i += 1; print("成功" + Worker.i + "   开始" + Worker.o + "当前抓取城市为" + task.num_city)
    //      case None => println(task.num_city + "数据错误")
    //      case other => println("Unknown data structure: " + other)
    //    }
    // Source.fromInputStream(response.getEntity.getContent).bufferedReader().readLine().foreach(print(_))
  } catch {
    case ex: Exception => print(ex.getMessage + "出现异常"); Worker.retry += 1; if (Worker.retry % 3 != 0) {
      println("重试当前连接" + task.num_city + array(2) + array(3) + array(4));Thread.sleep(new Random(4000).nextInt(6000)); new Worker(task, array);
    } else println("失败次数过多丢弃当前连接");
  }
  println("")
  Thread.sleep(new Random(4000).nextInt(6000))
  if (Worker.o % 3 == 0) Thread.sleep(new Random(500).nextInt(5000))

}

object Worker {
  var retry: Int = 3
  var i = 0
  var o = 0

  def setheader: Array[Header] = {
    val ret = new Array[Header](7)
    ret(0) = new BasicHeader("Host", "test.2015.app.cric.com")
    ret(1) = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0")
    ret(2) = new BasicHeader("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*")
    ret(3) = new BasicHeader("Accept-Language", "zh-CN")
    ret(4) = new BasicHeader("Accept-Encoding", "gzip, deflate")
    ret(5) = new BasicHeader("Connection", "keep-Alive")
    ret(6) = new BasicHeader("Catch-Control", "no-cache")
    ret
  }

  def encoding(str: String) = {
    val ul = URLEncoder.encode(str, "utf-8");
    println(ul);
    ul
  }


}
