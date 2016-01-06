import java.io.{InputStreamReader, BufferedReader}
import akka.actor.{Actor, Props, ActorSystem}
import com.IClrawler.{httpCom, Units}
import org.apache.http.Header
import org.apache.http.client.methods.{HttpGet, HttpPost}

/**
 * Created by dell on 2016/1/6.
 */
//case class Greeter(uri: String, unitAr: Array[Header])

object CrawlerAkka extends App {
  val us = new Units
  val ar = us.setheader("www.baidu.com", "")
  val system = ActorSystem("CrawlerAkka")
  val greeter = system.actorOf(Props[Greeter], "greeter")
  greeter !("www.baidu.com", "")
}

class akkaHttp extends httpCom {
  override def httpGet(uri: String, unitAr: Array[Header]) = {
    val hg = new HttpGet(uri)
    hg.setHeaders(unitAr)
    val response = Units.IHttpclient.execute(hg)
    val br = new BufferedReader(new InputStreamReader(response.getEntity.getContent, "gb2312"), 30000)
    val content = new StringBuilder
    while (br.readLine() != null) {
      content.append(br.readLine)
      content.append("\n")

    }
    println(content.toString)
    content.toString

  }
}

class Greeter extends Actor {
  private val akkaHttp = new akkaHttp

  override def receive = {
    case (uri: String, unitAr: Array[Header]) => akkaHttp.httpGet(uri, unitAr)
  }
}


