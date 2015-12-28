package com.IClrawler.TaskInfo

import com.IClrawler.HttpGetCom

/**
 * Created by dell on 2015/12/9.
 */
class realestate_Info {
  var year1= 2007
  var year2= 2015
  var week1= 1
  var week2= 52
  var month1= 1
  var month2= 12
  var quarter1= 1
  var quarter2= 4
  var day1= 1
  var day2= 31
  //商品房 商品住宅 日 tag
    val listTag1 = List(
      List("供给情况", "可售房屋套数(套)", "null")
      ,
      List("供给情况", "可售房屋面积", "数值(万㎡)"
      ),
      List("供给情况", "可售房屋面积", "环比%"
      ),
      List("成交情况", "成交套数(套)", "null"
      ),
      List("成交情况", "成交面积", "数值(万㎡)"
      ),
      List("成交情况", "成交面积", "环比(%)"
      )
    )
    val listTag2 = List(
      //商品房 商品住宅 月 季 周 tag

      List("供给情况", "批准预售套数(套)", "null")
      ,
      List("供给情况", "批准预售面积(万㎡)", "null"
      ),
      List("供给情况", "可售套数(套)", "null"
      ),
      List("供给情况", "可售面积", "数值(㎡)"
      ),
      List("供给情况", "可售面积", "环比增长(%)"
      ),
      List("供给情况", "可售面积", "同比增长(%)"
      ),
      List("成交情况", "成交套数(套)", "null"
      ),
      List("成交情况", "成交面积", "数值(%)"
      ),
      List("成交情况", "成交面积", "环比增长(%)"
      ),
      List("成交情况", "成交面积", "同比增长(%)"
      ),
      List("成交情况", "成交金额(亿元)", "null"
      ),
      List("成交情况", "平均成交单价", "数值(元㎡)"
      ),
      List("成交情况", "平均成交单价", "环比增长(%)"
      ),
      List("成交情况", "平均成交单价", "同比增长(%)"
      ),
      List("成交情况", "套均价格(万元/套)", "null"
      )

    )


    val listTag3 = List(
      //  //商品房 商品住宅 年 tag
      List("供给情况", "批准预售套数(套)", "null")
      ,
      List("供给情况", "批准预售面积(万m*2)", "null"
      ),
      List("供给情况", "可售套数(套)", "null"
      ),
      List("供给情况", "可售面积", "数值(㎡)"
      ),
      List("供给情况", "可售面积", "同比增长(%)"
      ),
      List("成交情况", "成交套数(套)", "null"
      ),
      List("成交情况", "成交面积", "数值(%)"
      ),
      List("成交情况", "成交面积", "同比增长(%)"
      ),
      List("成交情况", "成交金额(亿元)", "null"
      ),
      List("成交情况", "平均成交单价", "数值(元㎡)"
      ),
      List("成交情况", "平均成交单价", "同比增长(%)"
      ),
      List("成交情况", "套均价格(万元/套)", "null"
      )

    )
    val listTag4 = List(
      // 办公楼　商业营业用房 周 月 季 tag
      List("供给情况", "可售套数(套)", "null"
      ),
      List("供给情况", "可售面积", "数值(万㎡)"
      ),
      List("供给情况", "可售面积", "环比增长(%)"
      ),
      List("供给情况", "可售面积", "同比增长(%)"
      ),
      List("供给情况", "成交套数(套)", "null"
      ),
      List("成交情况", "成交面积", "数值(万㎡)"
      ),
      List("成交情况", "成交面积", "环比增长(%)"

      ),
      List("成交情况", "成交面积", "同比增长(%)"
      ),
      List("成交情况", "成交金额(亿元)", "null"
      ),
      List("成交情况", "平均成交单价", "数值(元㎡)"
      ),
      List("成交情况", "平均成交单价", "环比增长(%)"
      ),
      List("成交情况", "平均成交单价", "同比增长(%)"
      ),
      List("成交情况", "套均价格(万元/套)", "null"
      )

    )
    val listTag5 = List(
      // 办公楼　商业营业用房 年 tag
      List("供给情况", "可售套数(套)", "null"
      ),
      List("供给情况", "可售面积", "数值(万㎡)"
      ),
      List("供给情况", "可售面积", "同比增长(%)"
      ),
      List("供给情况", "成交套数(套)", "null"
      ),
      List("成交情况", "成交面积", "数值(万㎡)"
      ),
      List("成交情况", "成交面积", "同比增长(%)"
      ),
      List("成交情况", "成交金额(亿元)", "null"
      ),
      List("成交情况", "平均成交单价", "数值(元㎡)"
      ),
      List("成交情况", "平均成交单价", "环比增长(%)"
      ),
      List("成交情况", "平均成交单价", "同比增长(%)"
      ),
      List("成交情况", "套均价格(万元/套)", "null"
      )
    )
    val listTag6 = List(
      // 二手房　二手住宅 日 tag
      List("null", "成交套数", "null")
      ,
      List("成交面积", "成交面积(套)", "null"
      ),
      List("成交面积", "环比(万㎡ )", "null"
      )

    )
    val listTag7 = List(
      //二手房　二手住宅 月 季 周 tag

      List("成交情况", "成交套数(套)", "null")
      ,
      List("成交情况", "成交面积", "数值(㎡)"
      ),
      List("成交情况", "成交面积", "环比增长(%)"
      ),
      List("成交情况", "成交面积", "同比增长(%)"
      ),
      List("成交情况", "成交金额(亿元)", "null"
      ),
      List("成交情况", "平均成交单价", "数值(元/㎡)"
      ),
      List("成交情况", "平均成交单价", "环比增长(%)"
      ),
      List("成交情况", "平均成交单价", "同比增长(%)"
      ),
      List("成交情况", "套均价格(万元/套)", "null"
      )
    )
    val listTag8 = List(
      //二手房　二手住宅 年 tag

      List("成交情况", "成交套数(套)", "null")
      ,
      List("成交情况", "成交面积", "数值(㎡)"
      ),
      List("成交情况", "成交面积", "同比增长(%)"
      ),
      List("成交情况", "成交金额(亿元)", "null"
      ),
      List("成交情况", "平均成交单价", "数值(元/㎡)"
      ),
      List("成交情况", "平均成交单价", "同比增长(%)"
      ),
      List("成交情况", "套均价格(万元/套)", "null"
      )
    )
  var num=1
    def makeYearUri(hg:HttpGetCom, list: List[List[String]]) = {
      for (year <- year1 to year2) {
        val uri = s"http://www.realestate.cei.gov.cn/traden/br1.aspx?id=$year&x=w$num&result=nw&r1=2014&r2=2007"
        println(uri)

        hg.httpGet2(uri, year.toString,list)
      }
    }

    def makeDayUri(hg:HttpGetCom, list: List[List[String]]) = {
      for (year <- year1 to year2; month <- month1 to month2; day <- day1 to day2) {
        val date = year + makeInt(month) + makeInt(day)
        val uri = s"http://www.realestate.cei.gov.cn/traden/br2.aspx?rq=$date&lx=w$num&r1=20151130"
        println(uri)

        hg.httpGet2(uri, date,list)

      }
    }

    def makeMonthUri ( hg:HttpGetCom,list: List[List[String]])= {
      for (year <- year1 to year2; month <- month1 to month2) {
        val date = year + makeInt(month)
        val uri = s"http://www.realestate.cei.gov.cn/traden/br1.aspx?id=$date&x=w$num&result=mw&r1=201511&r2=200701"
        println(uri)

        hg.httpGet2(uri, date,list)

      }
    }

    def makeQuarterUri  (hg:HttpGetCom, list: List[List[String]])= {
      for (year <- year1 to year2; quarter <- quarter1 to quarter2) {
        val date = year + makeInt(quarter)

        val uri = s"http://www.realestate.cei.gov.cn/traden/br1.aspx?id=$date&x=w$num&result=jw&r1=201503&r2=200701"
        println(uri)

        hg.httpGet2(uri, date,list)
      }
    }

    def makeWeekUri (hg:HttpGetCom, list: List[List[String]]) = {
      for (year <- year1 to year2; week <- week1 to week2) {
        val date = year + makeInt(week)
        val uri = s"http://www.realestate.cei.gov.cn/traden/br1.aspx?id=$date&x=w$num&result=tw&r1=201549&r2=200701"
        println(uri)
        hg.httpGet2(uri, date,list)
      }
    }

    def makeInt(int: Int): String = {
      if (int < 10) "0" + int else int.toString
    }
  }
