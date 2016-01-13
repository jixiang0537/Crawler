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
  def jsoupParserLd(file: String) = {
    val jp = Jsoup.parse(file)
    val linkList = jp.body().select("a[href^=default.aspx]")
    val list = {
      for (i <- 0 to linkList.size() - 1) yield {
        makeUriLC(linkList.get(i).toString)
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
  var  lcAr = scala.collection.mutable.ArrayBuffer()

  def returnUri: String = {
    val uri = lcAr(0)
    lcAr.remove(0)
    uri
  }
}
