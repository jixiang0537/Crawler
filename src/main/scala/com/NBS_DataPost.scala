//package com.IClrawler
//
//import com.IClrawler.TaskInfo.NBS_TaskLink
//import org.apache.http.client.methods.{HttpPost}
//
//import scala.io.Source
//import scala.util.Random
//
///**
// * Created by dell on 2015/11/18.
// */
//class NBS_DataPost {
//  def httpGet(array: Array[String], month: Int) = {
//    val hg = new HttpPost(NBS_TaskLink.baseDateUri)
//    val unit = new Units
//    hg.setHeaders(unit.setheader("data.stats.gov.cn"))
//    hg.setHeader("Referer", uri)
//    hg.setHeader("DNT", "1")
//
//    val response = Units.IHttpclient.execute(hg)
//    val content = Source.fromInputStream(response.getEntity.getContent,"utf-8").mkString
//    array.foreach(print(_))
//    print(content)
//    val units = new Units
//    units.JParse(array,content,month)
//    Thread.sleep(new Random(6000).nextInt(10000))
//  }
//
//}
