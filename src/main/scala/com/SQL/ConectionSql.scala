package com.IClrawler.SQL

/**
 * Created by dell on 2015/10/13.
 */

import java.sql._;
class ConectionSql {
  def insetData(ciyi: String, attribute: String, datetype: String, particular: String, data: String) {
    try {
      val str = "insert into cric_upData20160128 (city,attribute,datetype,particular,data)values('" + ciyi + "','" + attribute + "','" + datetype + "','" + particular + "','" + data + "')"
      val ps = ConectionSql.ct.createStatement();
      val rs = ps.executeUpdate(str);
      println(ciyi, attribute, datetype, particular + "插入成功")
    } catch {
      case ex: Exception => print(ex.getMessage + "出现异常")
    }
  }
}

object ConectionSql {
  //  var ct: Connection = null
  //  try {
  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
  val ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Idatabase", "sa", "197313");
  //  } catch {
  //    case ex: Exception => print(ex.getMessage + "出现异常")
  //  }
}



