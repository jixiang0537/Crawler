package com.IClrawler.SQL

import com.IClrawler.TaskWork.NationalData_WkOther

/**
 * Created by dell on 2015/11/3.
 */
class NBS_ConnectSql {
  def insetDataCric (name:String,tag: String,tag1: String,tag2:String,date: String,data: String){
    try {
      val str = "insert into nbs_areaData (dataType,name,tag,tag1,tag2,date,data)values('"+NationalData_WkOther.dataType+"','"+name+"','"+tag+"','"+tag1+"','"+tag2+"','"+date+"','"+data+"')"
      val ps = ConectionSql.ct.createStatement();
      val rs = ps.executeUpdate(str);

      println(name,tag,tag1,tag2,date,data+"插入成功")
      NationalData_WkOther.array.foreach(x=> x==null)


    } catch {
      case ex: Exception => print(ex.getMessage + "出现异常")
    }
  }

}
