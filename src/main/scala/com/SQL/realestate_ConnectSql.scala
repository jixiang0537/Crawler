package com.IClrawler.SQL

import com.IClrawler.HttpGetCom
import com.IClrawler.TaskWork.NationalData_WkOther

/**
 * Created by dell on 2015/12/9.
 */
class realestate_ConnectSql {
    def insetData (city:String,tag: String,tag1: String,tag2:String,date: String,data: String){
      try {
        val str = "insert into realestate_data (dataType,city,tag,tag1,tag2,date,data)values('"+HttpGetCom.realestateType+"','"+city+"','"+tag+"','"+tag1+"','"+tag2+"','"+date+"','"+data+"')"
        val ps = ConectionSql.ct.createStatement();
        val rs = ps.executeUpdate(str);

        println(city,tag,tag1,tag2,date,data+"插入成功")
        NationalData_WkOther.array.foreach(x=> x==null)


      } catch {
        case ex: Exception => print(ex.getMessage + "出现异常")
      }
    }



}
