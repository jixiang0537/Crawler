import java.io.{InputStreamReader, BufferedReader}
import akka.actor.{Actor, Props, ActorSystem}
import com.IClrawler.{httpCom, Units}
import com.{runPhantom, main}
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
  val greeter = system.actorOf(Props[Demo], "1")
  var i = 1
  while (i < 200) {

  }


}

class maneg extends Actor {

  override def receive = {
    case (num: String) =>
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

class Demo extends Actor {
  override def receive = {
    case (uri: String, unitAr: Array[Header]) => {
      val akkaHttp = new akkaHttp
      akkaHttp.httpGet(uri, unitAr)
    }
    case x: Int => {
      val rp = new runPhantom
      rp.runJS(x)
    }
  }
}





