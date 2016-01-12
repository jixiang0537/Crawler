package com

import java.io.File

import org.jsoup.Jsoup

import scala.io.Source

/**
 * Created by dell on 2016/1/12.
 */
class landchina {
  def jsoupParserLd(file: String) = {
    val jp = Jsoup.parse(file)
    val linkList = jp.body().select("a[href^=default.aspx]")
    for (i <- 0 to linkList.size() - 1) {
      makeUriLC(linkList.get(i).toString)
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
