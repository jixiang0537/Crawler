package akkaDemo

import akka.actor.{Props, ActorSystem, Actor}
import com.IClrawler.{Units, httpCom}
import org.apache.http.Header

/**
 * Created by dell on 2016/1/5.
 */
case object Greet
case class Greeter(uri: String, unitAr: Array[Header])
class akkaHttp extends httpCom {

}

class Greet extends Actor {
  private val akkaHttp = new akkaHttp
  override def receive = {
    case Greeter(uri: String, unitAr: Array[Header]) => akkaHttp.httpGet(uri, unitAr)
  }
}

object CrawlerAkka extends App {
  val us = new Units
  val ar = us.setheader("www.baidu.com", "")
  val system = ActorSystem("CrawlerAkka")
  val greeter = system.actorOf(Props[Greeter], "greeter")
  greeter ! Greeter("www.baidu.com", ar)
}