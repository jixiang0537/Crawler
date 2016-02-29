import java.io.{File, InputStreamReader, BufferedReader}
import java.lang.Thread
import akka.actor.{Actor, Props, ActorSystem}
import com.IClrawler.{httpCom, Units}
import com.SQL.{landchinaOther_Sql, landchina_Sql}
import com._
import com.typesafe.config.ConfigFactory
import org.apache.http.Header
import org.apache.http.client.methods.{HttpGet}
import org.apache.http.impl.client.{DefaultConnectionKeepAliveStrategy, HttpClients}
import org.apache.log4j.{PropertyConfigurator, Logger}
import scala.concurrent.duration._
import scala.util.Random


/**
 * Created by dell on 2016/1/6.
 */
//case class greet(string: String, int: Int)

object CrawlerAkka extends App {
  PropertyConfigurator.configure("log4j.properties");
  val config = ConfigFactory.load()
  val system = ActorSystem("CrawlerAkka", config.getConfig("akka").withFallback(config))
  val system2 = ActorSystem("CrawlerAkk", config.getConfig("akka").withFallback(config))
  val greet = system2.actorOf(Props[work], "overseer")
  val task = system2.actorOf(Props[work], "task")

  val runJs = system.actorOf(Props[manage])

  val task2 = system.actorOf(Props[manage])

  //  val err = system.actorOf(Props[taskWork])
  //
  //  val task3 = system.actorOf(Props[taskWork])

  val runJs2 = system.actorOf(Props[runJS])

  val task4 = system.actorOf(Props[runJS])


  val us = new Units
  val dataAr = us.dateTask("2007-01-01", "2016-03-02")
  landchina.dateAr ++= dataAr
  val enc = "gb2312"
  system.scheduler.schedule(0 second, 5 second, runJs, ("start", 1))(system.dispatcher, task2)

  system2.scheduler.schedule(0 second, 1 second, greet, ("req", enc, 40))(system.dispatcher, task)


  // system.scheduler.schedule(0 second, 30 second, err, ("errUri", enc))(system.dispatcher, task3)

  system.scheduler.schedule(0 second, 10 second, runJs2, ("runjs"))(system.dispatcher, task4)


  //  system.actorOf(Props[manage]) !("start", "2002-01-01","2016-03-02" )


}

class runJS extends Actor {
  val log = MyLogger(this.getClass)

  override def receive = {
    case ("runjs") => {
      landchina.returnJsUri match {
        case null => println()
        case uri: String => val rp = new runPhantom
          log info (uri)
          rp.runJS(uri) match {
            case "false" => log error ("错误 - - -" + uri)
            case x: String if x.length < 500 => log error (s"脚本 或 页面 出错 ===- - - - -" + x);
            case x: String => val lc = new landchina
              val rp = new runPhantom
              landchina.lcAr ++= (lc jsoupParserLd (x))
            case _ => log error ("错误-" + uri)

          }
      }


    }

  }

}

class work extends Actor {
  override def receive = {
    case ("req", enc: String, x: Int) => {
      for (num <- 1 to x) {
        context.actorOf(Props[taskWork]) !("run", enc)
      }

    }

  }
}

class manage extends Actor {
  val log = MyLogger(this.getClass)

  override def receive = {
    //    case (uri: String, unitAr: Array[Header]) => {
    //      val akkaHttp = new akkaHttp
    //      akkaHttp.httpGet(uri, unitAr,)
    //    }
    case ("start", x: Int) => {
      landchina.dateAr.size match {
        case size: Int if size > 0 => val datePar = landchina.returnDatePar
          context.actorOf(Props[taskLCWork]) !("dataTask", datePar)
        case _ =>
      }
      //获得 日期 19xx xx xx 集合 发送线程开始任务 根据日期调用phantom脚本


    }
    case ("landchina", content: String) => {
      // landchina 根据抓取的页面内容 分析子页面链接 加入任务队列 landchina.lcAr
      val lc = new landchina
      val rp = new runPhantom
      landchina.lcAr ++= (lc jsoupParserLd (content))
    }


    case x: String => {
      println("添加链接 = = =" + x);
      landchina.lcAr += x
    }
  }

}

class akkaHttp extends httpCom {
  override def httpGet(uri: String, unitAr: Array[Header], encouding: String) = {

    val hg = new HttpGet(uri)
    hg.setHeaders(unitAr)
    val IHttpclient = HttpClients.custom()

      .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).build()
    val response = IHttpclient.execute(hg)
    val re = response.getEntity
    val br = new BufferedReader(new InputStreamReader(re.getContent, encouding), 30000)
    val content = new StringBuilder
    while (br.readLine() != null) {
      content.append(br.readLine())
      content.append("\n")
    }
    println(content.length)
    content.toString
  }
}

class taskWork extends Actor {

  override def receive = {

    case ("run", enc: String) => {
      //对landchina数据 分为1 2两张表 根据编号
      val us = new Units
      val lcg = new landchinaGet
      val lc = new landchina
      val uri = landchina.returnUri
      if (uri != null) {
        landchina.landChinaNum += 1
        //   val fc = lcg.httpGet(uri, us.setheader("www.landchina.com"), enc)
        try {
          MyLogger(this.getClass).info("连接数为" + landchina.landChinaNum + " - - - " + uri)
          //   val fc = lcg.httpGet(uri, us.setheader("www.landchina.com"), enc)

          val fc = lcg.httpGetProxy(uri, us.setheader("www.landchina.com"), enc, "182.92.1.222", 8123)


          //将获取的子页面数据 分别发送到两个线程中解析  解析为两个表
          val jp = new landchinaParser
          val lcq = new landchina_Sql
          val ar = jp.jP1(fc)
          lcq.insetData(ar(0), ar(1), ar(2), ar(3), ar(4), ar(5), ar(6), ar(7), ar(8), ar(9), ar(10), ar(11), ar(12), ar(13), ar(14), ar(15), ar(16), ar(17), ar(18), ar(19), ar(20), ar(21))

          val lcq2 = new landchinaOther_Sql
          val ar2 = jp.jP2(fc)
          lcq2.insetData(ar2(0), ar2(1), ar2(2), ar2(3), ar2(4), ar2(5), ar2(6), ar2(7), ar2(8))
        } catch {
          case ex: Exception => landchina.lcErrAr += uri
        }
      }
    }

    case ("errUri", enc: String) => {
      //对landchina数据 分为1 2两张表 根据编号
      val us = new Units
      val lcg = new landchinaGet
      val uri = landchina.returnErrUri
      uri match {
        case null => println()
        case _ => {
          //   val fc = lcg.httpGet(uri, us.setheader("www.landchina.com"), enc)
          try {


            //    val fc = lcg.httpGet(uri, us.setheader("www.landchina.com"), enc)

            val fc = lcg.httpGetProxy(uri, us.setheader("www.landchina.com"), enc, "182.92.1.222", 8123)
            MyLogger(this.getClass).info("连接数为" + landchina.landChinaNum + " - - - " + uri)

            //根据唯一ID 分表存储数据
            val jp = new landchinaParser
            val lcq = new landchina_Sql
            val ar = jp.jP1(fc)
            lcq.insetData(ar(0), ar(1), ar(2), ar(3), ar(4), ar(5), ar(6), ar(7), ar(8), ar(9), ar(10), ar(11), ar(12), ar(13), ar(14), ar(15), ar(16), ar(17), ar(18), ar(19), ar(20), ar(21))

            val lcq2 = new landchinaOther_Sql
            val ar2 = jp.jP2(fc)
            lcq2.insetData(ar2(0), ar2(1), ar2(2), ar2(3), ar2(4), ar2(5), ar2(6), ar2(7), ar2(8))

          } catch {
            case ex: Exception => landchina.lcErrAr += uri
          }
        }
      }
    }


  }


}

