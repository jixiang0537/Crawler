import java.io.{File, InputStreamReader, BufferedReader}
import akka.actor.{Actor, Props, ActorSystem}
import com.IClrawler.{httpCom, Units}
import com._
import org.apache.http.Header
import org.apache.http.client.methods.{HttpGet}
import org.apache.http.impl.client.{DefaultConnectionKeepAliveStrategy, HttpClients}
import scala.concurrent.duration._
import scala.util.Random


/**
 * Created by dell on 2016/1/6.
 */
//case class greet(string: String, int: Int)

object CrawlerAkka extends App {
  val us = new Units
  val system = ActorSystem("CrawlerAkka")
  val greeter = system.actorOf(Props[manage], "init")
  val greet = system.actorOf(Props[manage], "overseer")
  val task = system.actorOf(Props[manage], "task")
  val enc = "gb2312"
  system.scheduler.schedule(0 second, 1 second, greet, (enc, 10))(system.dispatcher, task)
  //  for (i <- 2 to 10)
  //    system.actorOf(Props[manage], i + "") ! i
  //}
  system.actorOf(Props[manage]) ! 2
  system.actorOf(Props[manage]) ! 3


}

class manage extends Actor {
  override def receive = {
    //    case (uri: String, unitAr: Array[Header]) => {
    //      val akkaHttp = new akkaHttp
    //      akkaHttp.httpGet(uri, unitAr,)
    //    }
    case x: Int => {
      val lc = new landchina
      val rp = new runPhantom
      landchina.lcAr ++= (lc jsoupParserLd (rp.runJS(x)))
    }
    case (enc: String, x: Int) => {
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
      val us = new Units
      val lcg = new landchinaGet
      val lc = new landchina
      println(lcg.httpGet(lc.returnUri, us.setheader("www.landchina.com", ""), enc).length)

    }
  }
}






