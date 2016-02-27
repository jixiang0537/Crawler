package com

import Exception.{NullResponseException, NullUriException}
import akka.actor.{Props, Actor}
import com.SQL.{landchinaOther_Sql, landchina_Sql}
import org.jsoup.Jsoup

import scala.util.Random

/**
 * Created by dell on 2016/1/18.
 */
class landchinaParser {
  def mkLCPLink(date: String, num: Int = 1) = {
    //返回调用phantomjs 命令行参数
    val str = "E:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe --load-images=false E:\\Cz\\ICrawler\\src\\main\\scala\\Phantomjs\\demo.js " + date + " " + num + ""
    str
  }

  def analysisPageNum(num: Int) = {
    val ar = {
      for (i <- 1 to num) yield {
        i
      }
    }
    ar
  }

  def getLCPageNum(con: String): String = {
    val jp = Jsoup.parse(con)
    val r = """(?<=共)\w+(?=页)""".r
    try {
      val content = jp.select("td[align=right]").select("td[class=pager]").text()
      val num = r.findFirstIn(content).get
      num
    } catch {
      case ex: NumberFormatException => return "1"
      case ex: Throwable => return "1"
    }

  }

  def jP1(str: String) = {
    //第一张表数据
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
    //第二张表数据
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
  val log = MyLogger(this.getClass)

  override def receive = {
    case ("landchina", content: String) => {
      // landchina 根据抓取的页面内容 分析子页面链接 加入任务队列 landchina.lcAr
      val lc = new landchina
      val rp = new runPhantom
      landchina.lcAr ++= (lc jsoupParserLd (content))
    }
    //landchina 传入时间参数 执行Phantom脚本 根据当前日期页面参数进行分析
    case ("dataTask", date: String) => {
      val lp = new landchinaParser
      val rp = new runPhantom

      val cmdLink = lp.mkLCPLink(date)
      val content = rp.runJS(cmdLink)
      log info (cmdLink)
      content match {
        case "false" => log error ("错误 - - -" + cmdLink)
        case x: String if x.length < 500 => log error (s"脚本 或 页面 出错 ===$date- - - - -" + cmdLink)

        case con: String => {
          //获得当前日期页面页数 如果页面数量 为1
          lp.getLCPageNum(con) match {
            case x: String if x.toInt == 1 => {
              sender() !("landchina", content)

            }
            //如果页面数量 为多
            case x: String if x.toInt > 1 => {
              sender() !("landchina", content)

              for (i <- 2 to x.toInt) {
                //根据当前页面参数重复请求每个页面 获取每个页面

                val link = lp.mkLCPLink(date, i)
                landchina.jsAr += link
                // context.actorOf(Props[subWork]) !("subTask", link)

              }
            }

          }
        }

      }
    }
    case ("jP1", content: String) => {
      //根据唯一ID 分表存储数据
      val jp = new landchinaParser
      val lcq = new landchina_Sql
      val ar = jp.jP1(content)
      lcq.insetData(ar(0), ar(1), ar(2), ar(3), ar(4), ar(5), ar(6), ar(7), ar(8), ar(9), ar(10), ar(11), ar(12), ar(13), ar(14), ar(15), ar(16), ar(17), ar(18), ar(19), ar(20), ar(21))

    }
    case ("jP2", content: String) => {
      //根据唯一ID 分表存储数据
      val jp = new landchinaParser
      val ar = jp.jP2(content)
      val lcq = new landchinaOther_Sql
      lcq.insetData(ar(0), ar(1), ar(2), ar(3), ar(4), ar(5), ar(6), ar(7), ar(8))

    }


  }
}

class subWork extends Actor {
  val log = MyLogger(this.getClass)

  override def receive = {
    case ("subTask", link: String) => {

      val rp = new runPhantom
      rp.runJS(link) match {
        case "false" => log error ("错误 - - -" + link)
        case x: String if x.length < 500 => log error (s"脚本 或 页面 出错 ===- - - - -" + link); landchina.jsErrAr += link
        case x: String => sender() !("landchina", x)
        case _ => log error ("错误-" + link)

      }


    }

  }
}