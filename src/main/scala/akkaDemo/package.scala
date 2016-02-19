import java.io.{File, InputStreamReader, BufferedReader}
import akka.actor.{Actor, Props, ActorSystem}
import com.IClrawler.{httpCom, Units}
import com._
import org.apache.http.Header
import org.apache.http.client.methods.{HttpGet}
import org.apache.http.impl.client.{DefaultConnectionKeepAliveStrategy, HttpClients}
import org.apache.log4j.Logger
import scala.concurrent.duration._


/**
 * Created by dell on 2016/1/6.
 */
//case class greet(string: String, int: Int)

object CrawlerAkka extends App {

  val us = new Units
  val system = ActorSystem("CrawlerAkka")
  //val greeter = system.actorOf(Props[manage], "init")
  val greet = system.actorOf(Props[manage], "overseer")
  val task = system.actorOf(Props[manage], "task")

  val enc = "gb2312"
  system.scheduler.schedule(0 second, 1 second, greet, (enc, 10))(system.dispatcher, task)
  system.actorOf(Props[manage]) !("start", 2000, 2016)


}

class manage extends Actor {
  override def receive = {
    //    case (uri: String, unitAr: Array[Header]) => {
    //      val akkaHttp = new akkaHttp
    //      akkaHttp.httpGet(uri, unitAr,)
    //    }
    case ("start", x: Int, y: Int) => {
      val us = new Units
      val dataAr = us.deteTask(x, y)
      //获得 日期 19xx xx xx 集合 发送线程开始任务 根据日期调用phantom脚本
      for (i <- 0 until (dataAr.length)) context.actorOf(Props[taskLCWork]) !("dataTask", dataAr(i))
    }
    case ("landchina",content:String) => {
      // landchina 根据抓取的页面内容 分析子页面链接 加入任务队列 landchina.lcAr
      val lc = new landchina
      val rp = new runPhantom
        landchina.lcAr ++= (lc jsoupParserLd (content))
    }
    case (enc: String, x: Int) => {
      println(landchina.lcAr.size)
      if (landchina.lcAr.size != 0) {
        for (num <- 1 to x) {
          context.actorOf(Props[taskWork]) !("run", enc)
        }
      }
    }

    case x: String => {
      println("添加链接 = = =" + x);
      landchina.lcAr ++ x
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
      //对landchina数据进行模块化 分为1 2两张表 根据编号
      val us = new Units
      val lcg = new landchinaGet
      val lc = new landchina
      val fc = lcg.httpGetProxy(landchina.returnUri, us.setheader("www.landchina.com"), enc,"182.92.1.222",8123)
      //将获取的子页面数据 分别发送到两个线程中解析  解析为两个表
      context.actorOf(Props[taskLCWork]) !("jP1", fc: String)
      context.actorOf(Props[taskLCWork]) !("jP2", fc: String)

    }
  }


}




