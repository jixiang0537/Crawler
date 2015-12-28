package com.IClrawler.Manage

import com.IClrawler.HttpGetCom
import com.IClrawler.TaskInfo.realestate_Info

/**
 * Created by dell on 2015/12/11.
 */
class realestate_Task {
  def task2: Unit = {

    //    val hg = new HttpGetCom
    //    hg.httpGet()
    //    hg.httpPost
    val ri = new realestate_Info
    ri.num = 2

    //    HttpGetCom.realestateType = "商品住宅日数据"
    //    ri.makeDayUri(hg, ri.listTag1)
    // HttpGetCom.realestateType = "商品住宅周数据"
    // ri.makeWeekUri(hg, ri.listTag2)
    // HttpGetCom.realestateType = "商品住宅月数据"
    //  ri.makeMonthUri(hg, ri.listTag2)
    //    HttpGetCom.realestateType = "商品住宅季数据"
    //    ri.makeQuarterUri(hg, ri.listTag2)
    //    HttpGetCom.realestateType = "商品住宅年数据"
    //    ri.makeYearUri(hg, ri.listTag3)
  }
  def task3: Unit = {
    val ri = new realestate_Info
    val hg2 = new HttpGetCom
    hg2.getNetKey()
    hg2.httpPost
    ri.num = 3
    HttpGetCom.realestateType = "办公楼周数据"
    ri.makeWeekUri(hg2, ri.listTag4)
    HttpGetCom.realestateType = "办公楼月数据"
    ri.makeMonthUri(hg2, ri.listTag4)
    HttpGetCom.realestateType = "办公楼季数据"
    ri.makeQuarterUri(hg2, ri.listTag4)
    HttpGetCom.realestateType = "办公楼年数据"
    ri.makeYearUri(hg2, ri.listTag5)
  }

  def task4: Unit = {
    val ri = new realestate_Info
    val hg3 = new HttpGetCom
    hg3.getNetKey()
    hg3.httpPost
    ri.num = 4
    ri.year1=2010
    HttpGetCom.realestateType = "商业营业用房周数据"
    ri.makeWeekUri(hg3, ri.listTag4)
    ri.year1=2007
    HttpGetCom.realestateType = "商业营业用房月数据"
    ri.makeMonthUri(hg3, ri.listTag4)
    HttpGetCom.realestateType = "商业营业用房季数据"
    ri.makeQuarterUri(hg3, ri.listTag4)
    HttpGetCom.realestateType = "商业营业用房年数据"
    ri.makeYearUri(hg3, ri.listTag5)
  }

  def task5: Unit = {
    val ri = new realestate_Info

    val hg4 = new HttpGetCom
    hg4.getNetKey()
    hg4.httpPost
    ri.num = 5
    ri.year1=2014
    HttpGetCom.realestateType = "二手房日数据"
    ri.makeDayUri(hg4, ri.listTag4)
    ri.year1=2007
    HttpGetCom.realestateType = "二手房周数据"
    ri.makeWeekUri(hg4, ri.listTag4)
    HttpGetCom.realestateType = "二手房月数据"
    ri.makeMonthUri(hg4, ri.listTag4)
    HttpGetCom.realestateType = "二手房季数据"
    ri.makeQuarterUri(hg4, ri.listTag4)
    HttpGetCom.realestateType = "二手房年数据"
    ri.makeYearUri(hg4, ri.listTag5)
  }

  def task6: Unit = {
    val ri = new realestate_Info
    val hg5 = new HttpGetCom
    hg5.getNetKey()
    hg5.httpPost
    ri.num = 6
    ri.year1=2015
    ri.day1=10
    ri.month1=10
    HttpGetCom.realestateType = "二手住宅日数据"
    ri.makeDayUri(hg5, ri.listTag4)
    ri.year1=2007
    ri.day1=1
    ri.month1=1
    HttpGetCom.realestateType = "二手住宅周数据"
    ri.makeWeekUri(hg5, ri.listTag4)
    HttpGetCom.realestateType = "二手住宅月数据"
    ri.makeMonthUri(hg5, ri.listTag4)
    HttpGetCom.realestateType = "二手住宅季数据"
    ri.makeQuarterUri(hg5, ri.listTag4)
    HttpGetCom.realestateType = "二手住宅年数据"
    ri.makeYearUri(hg5, ri.listTag5)
  }
}
