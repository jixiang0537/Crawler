package com

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration._
import com.IClrawler.httpCom
import org.jsoup.Jsoup

import scala.io.Source

/**
 * Created by dell on 2016/1/12.
 */
class landchina {
  def returnUri: String = {
    landchina.lcAr.size match {
      case 0 => ""
      case _ => {
        val uri = landchina.lcAr(0)
        landchina.lcAr.remove(0)
        uri
      }
    }


  }

  def jsoupParserLd(file: String) = {
    val jp = Jsoup.parse(file)
    val linkList = jp.body().select("a[href^=default.aspx]")
    val list = {
      for (i <- 0 to linkList.size() - 1) yield {
        makeUriLC(linkList.get(i).attr("href"))
      }
    }
    list.toArray
  }

  def makeUriLC(link: String) = {
    val str = "http://www.landchina.com/"
    val uri = {
      str + link
    }
    uri
  }


}

class landchinaGet extends httpCom {

}

object landchina {
  var lcAr = new ArrayBuffer[String]()


}
