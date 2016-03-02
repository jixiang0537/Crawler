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
    } catch {
      case ex: Exception => throw new Exception(ex.getMessage)
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

  def returnUri: (String, String) = {
    landchina.lcMap.size match {
      case x: Int if x != 0 =>
        landchina.lcMap.head

      case _ => return null


    }

  }

  def returnDatePar: String = {
    landchina.dateAr.size match {
      case 0 => throw new NullUriException
      case _ => {
        val datePar = landchina.dateAr(0)
        landchina.dateAr.remove(0)
        datePar
      }
    }

  }

  def returnErrUri: String = {
    landchina.lcErrAr.size match {
      case 0 => return null
      case _ => {
        val datePar = landchina.lcErrAr(0)
        landchina.lcErrAr.remove(0)
        datePar

      }
    }

  }

  def returnJsUri: (String, String) = {
    landchina.jsMap.size match {
      case 0 => return null
      case _ => {
        landchina.jsMap.head

      }
    }

  }

  var dateAr = new ArrayBuffer[String]()

  // var lcMap = new ArrayBuffer[String]()

  var lcErrAr = new ArrayBuffer[String]()

  //  var jsMap = new ArrayBuffer[String]()

  var jsErrAr = new ArrayBuffer[String]()

  var lcMap = scala.collection.mutable.Map[String,String]()

  var jsMap = scala.collection.mutable.Map[String,String]()


}

