package com

import Exception.{WebPageGetException, NullUriException}

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
    try {
      val linkList = jp.body().select("a[href^=default.aspx]")
      val list = {
        for (i <- 0 to linkList.size() - 1) yield {
          makeUriLC(linkList.get(i).attr("href"))
        }
      }

      list.toArray
    }catch {
      case ex:Exception=>throw new WebPageGetException(ex.getMessage)
    }
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
  var landChinaNum = 0
  def returnUri: String = {
    landchina.lcAr.size match {
      case 0 => throw new NullUriException
      case _ => {
        val uri = landchina.lcAr(0)
        landchina.lcAr.remove(0)
        uri
      }
    }

  }

  def returnErrUri: String = {
    landchina.lcErrAr.size match {
      case 0 => throw new NullUriException
      case _ => {
        val uri = landchina.lcErrAr(0)
        landchina.lcErrAr.remove(0)
        uri
      }
    }

  }

  var lcAr = new ArrayBuffer[String]()

  var lcErrAr = new ArrayBuffer[String]()


}

