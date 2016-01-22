package com.IClrawler.SQL

import com.IClrawler.TaskWork.{NationalData_Worker2, NationalData_WkOther}

/**
 * Created by dell on 2015/11/3.
 */
class NBS_ConnectSql {
  def insetData(SqlStr: String) {

    try {
      val ps = ConectionSql.ct.createStatement();
      val rs = ps.executeUpdate(SqlStr);
      NationalData_WkOther.array.foreach(x => x == null)
    } catch {
      case ex: Exception => print(ex.getMessage + "出现异常")
    }
  }

}

object NBS_ConnectSql {
  def apply(dateType: String, dataAr: Array[String]) = dateType match {
    case "月度数据" => {
      val nc = new NBS_ConnectSql
      val partStr = "'\" + NationalData_WkOther.dataType + \"','\" + dataAr(0) + \"','\" + dataAr(1) + \"','\" + dataAr(2) + \"','\" + dataAr(3) + \"','\" + dataAr(4) + \"'"
      val str = s"insert into nbs_month (dateType,tag1,tag2,tag3,date,data)values($partStr)"
      nc.insetData(str)
      println("月度数据" + dataAr(0) + dataAr(1) + dataAr(2) + dataAr(3) + dataAr(4) + "数据库插入成功")
    }
    case "季度数据" => {

    }
    case "年度数据" => {

    }
    case "分省月度数据" => {

    }
    case _ =>
  }
}
