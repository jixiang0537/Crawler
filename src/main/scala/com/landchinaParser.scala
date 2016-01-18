package com

import akka.actor.Actor
import com.IClrawler.Units
import com.SQL.{landchinaOther_Sql, landchina_Sql}
import org.jsoup.Jsoup

/**
 * Created by dell on 2016/1/18.
 */
class landchinaParser {
  def jP1(str: String) = {
    val content = Jsoup.parse(str)
    val ar = Array(
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r1_c2_ctrl").text(), //行政区
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r1_c4_ctrl").text, //电子监管号
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r17_c2_ctrl").text(), //项目名称
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r16_c2_ctrl").text(), //项目位置
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r2_c2_ctrl").text(), //面积(公顷)
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r2_c4_ctrl").text(), //土地来源
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r3_c2_ctrl").text(), //土地用途
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r3_c4_ctrl").text(), //供地方式
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r19_c2_ctrl").text(), //土地使用年限
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r19_c4_ctrl").text(), //行业分类
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r20_c2_ctrl").text(), //土地级别
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r20_c4_ctrl").text(), //成交价格
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r9_c2_ctrl").text(), //土地使用权人
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f2_r1_c2_ctrl").text(), //约定容积率 下限
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f2_r1_c4_ctrl").text(), //约定容积率 上限
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r21_c4_ctrl").text(), //约定交地时间
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r22_c2_ctrl").text(), //约定开工时间
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r22_c4_ctrl").text(), //约定竣工时间
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r10_c2_ctrl").text(), //实际开工时间
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r10_c4_ctrl").text(), //实际竣工时间
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r14_c2_ctrl").text(), //批准单位
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r14_c4_ctrl").text() //合同签订日期
    )
    ar
  }

  def jP2(str: String) = {
    val content = Jsoup.parse(str)
    val ar = Array(
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f1_r1_c4_ctrl").text, //电子监管号
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f3_r2_c1_0_ctrl").text(), //分期支付期号
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f3_r2_c2_0_ctrl").text(), //约定支付日期
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f3_r2_c3_0_ctrl").text(), //约定支付金额(万元)
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f3_r2_c4_0_ctrl").text(), //备注
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f3_r2_c1_1_ctrl").text(), //分期支付期号
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f3_r2_c2_1_ctrl").text(), //约定支付日期
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f3_r2_c3_1_ctrl").text(), //约定支付金额(万元)
      content.select("span[id=mainModuleContainer_1855_1856_ctl00_ctl00_p1_f3_r2_c4_1_ctrl").text() //备注
    )
    ar
  }

}

class taskLCWork extends Actor {
  override def receive = {
    case ("jP1", content: String) => {
      val jp = new landchinaParser
      val lcq = new landchina_Sql
      val ar = jp.jP1(content)
      lcq.insetData(ar(0),ar(1),ar(2),ar(3),ar(4),ar(5),ar(6),ar(7),ar(8),ar(9),ar(10),ar(11),ar(12),ar(13),ar(14),ar(15),ar(16),ar(17),ar(18),ar(19),ar(20),ar(21))

    }
    case ("jP2", content: String) => {
      val jp = new landchinaParser
      val ar = jp.jP2(content)
      val lcq = new landchinaOther_Sql
      lcq.insetData(ar(0),ar(1),ar(2),ar(3),ar(4),ar(5),ar(6),ar(7),ar(8))


    }
  }

}
