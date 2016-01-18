package com.SQL

import com.IClrawler.SQL.ConectionSql

/**
 * Created by dell on 2016/1/18.
 */
class landchina_Sql extends ConectionSql {
  def insetData(CITY_NAME: String, MONITORING_NUM: String, PROJECT_NAME: String, PROJECT_LOCATING: String, AREA_HA: String, LANDSOURCE: String, LANDUSAGE: String, LANDOFWAY: String, USE_AGE_DESC: String, CATEGORY: String, LAND_LEVEL: String, TRADE_PRICE: String, LAND_USER: String, LOWER_LIMIT_PLOTRATIO: String, UPPER_LIMIT_PLOTRATIO: String, APPOINT_DELIVERY_LAND_DATE: String, APPOINT_START_DATE: String, APPOINT_DELIVER_DATE: String, ACTUAL_START_DATE: String, ACTUAL_DELIVER_DATE: String, APPROVE_UNIT: String, CONTRACT_SIGN_DATE: String): Unit = {
    try {
      val str = s"insert into LandChina_Result (CITY_NAME,MONITORING_NUM,PROJECT_NAME,PROJECT_LOCATING,AREA_HA,LANDSOURCE,LANDUSAGE,LANDOFWAY,USE_AGE_DESC,CATEGORY,LAND_LEVEL,TRADE_PRICE,LAND_USER,LOWER_LIMIT_PLOTRATIO,UPPER_LIMIT_PLOTRATIO,APPOINT_DELIVERY_LAND_DATE,APPOINT_START_DATE,APPOINT_DELIVER_DATE,ACTUAL_START_DATE,ACTUAL_DELIVER_DATE,APPROVE_UNIT,CONTRACT_SIGN_DATE)values('$CITY_NAME','$MONITORING_NUM','$PROJECT_NAME','$PROJECT_LOCATING','$AREA_HA','$LANDSOURCE','$LANDSOURCE','$LANDOFWAY','$USE_AGE_DESC','$CATEGORY','$LAND_LEVEL','$TRADE_PRICE','$LAND_USER','$LOWER_LIMIT_PLOTRATIO','$UPPER_LIMIT_PLOTRATIO','$APPOINT_DELIVERY_LAND_DATE','$APPOINT_START_DATE','$APPOINT_DELIVER_DATE','$ACTUAL_START_DATE','$ACTUAL_DELIVER_DATE','$APPROVE_UNIT','$CONTRACT_SIGN_DATE')"
      val ps = ConectionSql.ct.createStatement();
      val rs = ps.executeUpdate(str);
      println(CITY_NAME, MONITORING_NUM + "数据库 LandChina_Result 插入成功")
    } catch {
      case ex: Exception => print(ex.getMessage + "出现异常")
    }
  }
}
class landchinaOther_Sql extends ConectionSql {
  def insetData(MONITORING_NUM:String,PAY_ID1:String,PAY_DATE1:String,PAY_AMOUNT1:String,ETC1:String,PAY_ID2:String,PAY_DATE2:String,PAY_AMOUNT2:String,ETC2:String): Unit = {
    try {
      val str = s"insert into LCOther_Result (MONITORING_NUM,PAY_ID1,PAY_DATE1,PAY_AMOUNT1,ETC1,PAY_ID2,PAY_DATE2,PAY_AMOUNT2,ETC2)values('$MONITORING_NUM','$PAY_ID1','$PAY_DATE1','$PAY_AMOUNT1','$ETC1','$PAY_ID2','$PAY_DATE2','$PAY_AMOUNT2','$ETC2')"
      val ps = ConectionSql.ct.createStatement();
      val rs = ps.executeUpdate(str);
      println( MONITORING_NUM + "数据库 LCOther_Result 插入成功")
    } catch {
      case ex: Exception => print(ex.getMessage + "出现异常")
    }
  }
}
