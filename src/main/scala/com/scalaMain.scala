package com

import java.io.File
import java.text.SimpleDateFormat
import java.util.{Date, TimeZone}
import akka.actor.{Props, ActorSystem, Actor}
import com.IClrawler.Manage.{Cric_Task, NBS_Task}
import com.IClrawler.TaskWork.{NationalData_WkOther, NationalData_Worker2}
import com.IClrawler.{httpTest, Units, httpCom}
import net.sf.json.{JSONObject, JSON}
import org.apache.log4j.{Logger, PropertyConfigurator}
import scala.concurrent.{Await, Future}
import scala.io.Source
import akka.pattern.{ ask, pipe }
import slick.driver.H2Driver.api._

/**
 * Created by dell on 2016/1/12.
 */
object scalaMain {
  def main(args: Array[String]): Unit = {

    var lcMap = scala.collection.mutable.Map[String,String]()

    lcMap +=(("1","2"),("3","4"),("5","6"),("7","8"))

    lcMap.foreach(e=>println(e._1,e._2))

    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    format.setTimeZone(TimeZone.getTimeZone("GMT+8"))
    val date = format.format(new Date())
    val str = "MYH-AUTH-MD5 app_key=161600037&sign=56DF7A2FA851EE87764E5AB02E6F560A&timestamp="+date
println(str)    // val db = Database.forURL("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Idatabase", "sa", "197313", driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver")

//    val suppliers = TableQuery[Suppliers]
//
//    try {
//
//
//    } finally db.close


    //        val tp = new textP
    //        val us = Units.setheader("ip.chinaz.com")
    //
    //         val context = tp.httpGetProxy("http://ip.chinaz.com/ipbatch",us.toArray,"utf-8","182.92.1.222" ,8123)
    //        printf(context)


    //    val jp = Jsoup.parse(loadFile)
    //    val tableCon = jp.select("td[id=tdContent]").select("tbody tbody")
    //    for (i <- 0 until tableCon.size()) {
    //      println(tableCon.get(0))
    //    }


    def loadFile: String = {
      val path = "E:\\Cz\\1.txt"
      val str = Source.fromFile(new File(path)).mkString //getLines().foreach(println(_)))
      str
    }

  }

}
class Suppliers(tag: Tag) extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {
  def id = column[Int]("SUP_ID", O.PrimaryKey) // This is the primary key column
  def name = column[String]("SUP_NAME")
  def street = column[String]("STREET")
  def city = column[String]("CITY")
  def state = column[String]("STATE")
  def zip = column[String]("ZIP")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name, street, city, state, zip);
}
