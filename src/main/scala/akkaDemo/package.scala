import java.io.{InputStreamReader, BufferedReader}
import akka.actor.{Actor, Props, ActorSystem}
import com.IClrawler.{httpCom, Units}
import org.apache.http.Header
import org.apache.http.client.methods.{HttpGet}
import org.apache.http.impl.client.{DefaultConnectionKeepAliveStrategy, HttpClients}

/**
 * Created by dell on 2016/1/6.
 */
object CrawlerAkka extends App {
  val us = new Units
  val baiduAr = us.setheader("www.baidu.com", "")
  val system = ActorSystem("CrawlerAkka")
  val greeter = system.actorOf(Props[Demo], "greeter")
  val greeter2 = system.actorOf(Props[Demo], "greeter2")
  val greeter3 = system.actorOf(Props[Demo], "greeter3")
  val greeter4 = system.actorOf(Props[Demo], "greeter4")
  val greeter5 = system.actorOf(Props[Demo], "greeter5")
  val greeter6 = system.actorOf(Props[Demo], "greeter6")
  val greeter7 = system.actorOf(Props[Demo], "greeter7")
  val greeter8 = system.actorOf(Props[Demo], "greeter8")
  val greeter9 = system.actorOf(Props[Demo], "greeter9")
  val greeter10 = system.actorOf(Props[Demo], "greeter10")
  val greeter11 = system.actorOf(Props[Demo], "greeter11")
  val greeter12= system.actorOf(Props[Demo], "greeter12")

  greeter !("https://www.baidu.com/", baiduAr);
  greeter2 !("https://www.baidu.com/", baiduAr);
  greeter3 !("https://www.baidu.com/", baiduAr);
  greeter4 !("https://www.baidu.com/", baiduAr);
  greeter5 !("https://www.baidu.com/", baiduAr);
  greeter6 !("https://www.baidu.com/", baiduAr);
  greeter7 !("https://www.baidu.com/", baiduAr);
  greeter8 !("https://www.baidu.com/", baiduAr);
  greeter9 !("https://www.baidu.com/", baiduAr);
  greeter10 !("https://www.baidu.com/", baiduAr);
  greeter11 !("https://www.baidu.com/", baiduAr);
  greeter12 !("https://www.baidu.com/", baiduAr);
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

class Demo extends Actor {
  override def receive = {
        case (uri: String, unitAr: Array[Header]) =>         {
                                                                 val akkaHttp = new akkaHttp
                                                                 akkaHttp.httpGet(uri, unitAr)
                                                             }
  }
}




