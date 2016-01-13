import java.io.{InputStreamReader, BufferedReader}
import akka.actor.{Actor, Props, ActorSystem}
import com.IClrawler.{httpCom, Units}
import com.{landchinaGet, landchina, runPhantom, main}
import org.apache.http.Header
import org.apache.http.client.methods.{HttpGet}
import org.apache.http.impl.client.{DefaultConnectionKeepAliveStrategy, HttpClients}
import scala.concurrent.duration._


/**
 * Created by dell on 2016/1/6.
 */
//case class greet(string: String, int: Int)

object CrawlerAkka extends App {
  val us = new Units
  val system = ActorSystem("CrawlerAkka")
  val greeter = system.actorOf(Props[manage], "1")
  val greet = system.actorOf(Props[manage], "overseer")
  val task = system.actorOf(Props[manage], "task")
 system.scheduler.schedule(0 second, 1 milliseconds, greet, ("start", 10))(system.dispatcher, task)

  greeter ! 2

}

class manage extends Actor {
  override def receive = {
    case (uri: String, unitAr: Array[Header]) => {
      val akkaHttp = new akkaHttp
      akkaHttp.httpGet(uri, unitAr)
    }
    case x: Int => {
      val lc = new landchina
      val rp = new runPhantom
     landchina.lcAr= landchina.lcAr ++ (lc jsoupParserLd (rp.runJS(x)))
    }
    case ("start", x: Int) => {
      if (landchina.lcAr.size != 0) {
        for (num <- 1 to x) {
          context.actorOf(Props[taskWork], num.toString) ! "run"
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
  override def httpGet(uri: String, unitAr: Array[Header]) = {

    val hg = new HttpGet(uri)
    hg.setHeaders(unitAr)
    val IHttpclient = HttpClients.custom().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).build()
    val response = IHttpclient.execute(hg)
    val re = response.getEntity
    val br = new BufferedReader(new InputStreamReader(re.getContent, "utf-8"), 30000)
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
    case "run" => println("ok")
  }
}






