package com.IClrawler.Manage


import com.IClrawler.TaskWork.{NationalData_Worker2, NationalData_WkOther, NationalData_Work}
import com.IClrawler.{httpCom, Units, httpTest}

/**
 * Created by dell on 2015/11/3.
 */
class NBS_Task {
  val nw2 = new NationalData_WkOther
  val us = new Units
  val uAr = us.setheader("data.stats.gov.cn", "http://www.stats.gov.cn/")

  def yearTask = {
    val nw2 = new NationalData_WkOther
    NationalData_Worker2.map.clear()
    NationalData_Worker2.map +=(("A01", "综合"), ("A02", "国民经济核算"), ("A03", "人口"), ("A04", "就业人员和工资"), ("A05", "固定资产和房地产"), ("A06", "对外经济贸易"), ("A07", "能源"), ("A08", "财政"), ("A09", "价格指数"), ("A0A", "人民生活"), ("A0B", "城市概况"), ("A0C", "资源和环境"))
    NationalData_Worker2.map += (("A0D", "农业"))
    NationalData_Worker2.map += (("A0E", "工业"))
    NationalData_Worker2.map += (("A0F", "建筑业"))
    NationalData_Worker2.map += (("A0G", "运输和邮电"))
    NationalData_Worker2.map += (("A0H", "社会消费品零售总额"))
    NationalData_Worker2.map += (("A0I", "批发和零售业"))
    NationalData_Worker2.map += (("A0G", "住宿和餐饮业"))
    NationalData_Worker2.map += (("A0K", "旅游业"))
    NationalData_Worker2.map += (("A0L", "金融业"))
    NationalData_Worker2.map += (("A0M", "教育"))
    NationalData_Worker2.map += (("A0N", "科技"))
    NationalData_Worker2.map += (("A0O", "卫生"))
    NationalData_Worker2.map += (("A0P", "社会服务"))
    NationalData_Worker2.map += (("A0Q", "文化"))
    NationalData_Worker2.map += (("A0R", "体育"))
    NationalData_Worker2.map += (("A0S", "公共管理,社会保障和其他"))
    //  NationalData_Worker2.map += (("A0S", "公共管理,社会保障和其他"))

    val list = List("A01", "A02", "A03",
      "A04", "A05", "A06", "A07", "A08", "A09", "A0A", "A0B", "A0C", "A0D", "A0E", "A0F", "A0G", "A0H", "A0I", "A0G", "A0K", "A0L", "A0M", "A0N", "A0O", "A0P", "A0Q", "A0R", "A0S"
    )
    NationalData_WkOther.dataType = "月度数据"
    nw2.httpCookie
    nw2.firstHttpGet("http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=hgnd&rowcode=zb&colcode=sj&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22LAST20%22%7D%5D")
  }

  def monthTask(sDate: String, eDate: String) = {
    val nW2 = new NationalData_Worker2
    val dateType = "月度数据"
    NationalData_Worker2.dataType = dateType
    nw2.uri = "http://data.stats.gov.cn/easyquery.htm?cn=A01"
    nw2.dbcode = "hgyd"
    nw2.httpCookie
    NationalData_Worker2.map.clear()
    NationalData_Worker2.map +=(("A01", "价格指数"), ("A02", "工业"), ("A03", "固定资产投资额"), ("A04", "房地产"), ("A05", "国内贸易"), ("A06", "对外经济"), ("A07", "交通运输"), ("A08", "邮电通信"), ("A09", "采购经理指数"), ("A0A", "财政"), ("A0B", "金融"))
    val list = List("A01", "A02", "A03",
      "A04", "A05", "A06", "A07", "A08", "A09", "A0A", "A0B")
    nw2.firstHttpGet(s"http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=hgyd&rowcode=zb&colcode=sj&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22$sDate-$eDate%22%7D%5D")
    list.foreach(x => {
      nW2.Jparse(nw2.HttpPost(x))
    })
  }

  def NBS_Task1 = {
    val us = new Units
    val uArOther = us.setheader("data.stats.gov.cn")
    val nw = new NationalData_WkOther
    NationalData_WkOther.map += (("", "null"))
    nw.dbcode = "fsyd"
      //nw.rowcode= "reg"
    nw.httpCookie
    val str =      "http://data.stats.gov.cn/easyquery.htm?m=getOtherWds&dbcode=fsyd&rowcode=reg&colcode=sj&wds=%5B%5D"
    val cityStr = "http://data.stats.gov.cn/easyquery.htm?m=getOtherWds&dbcode=fsyd&rowcode=zb&colcode=sj&wds=%5B%5D"
    val ht = new NBS_HttpReq
    val cityTag = ht.httpGet(cityStr, uArOther)
    println("分类标签已获取")
    val tagStr = ht.httpGet(str, uArOther)
    NationalData_WkOther.dataType = "分省月度数据"
    nw.firstHttpGet("http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=fsyd&rowcode=zb&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A020101%22%7D%5D&dfwds=%5B%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22201511-201512%22%7D%5D")
    nw.addClassifyId(cityTag)
    nw.addClassifyId2(tagStr)
    nw.Jparse2(nw.HttpPost())

    nw.Jparse(nw.HttpPost("A01"))
    nw.Jparse(nw.HttpPost("A02"))
    nw.Jparse(nw.HttpPost("A03"))
    nw.Jparse(nw.HttpPost("A04"))
    nw.Jparse(nw.HttpPost("A05"))
    nw.Jparse(nw.HttpPost("A06"))
    nw.Jparse(nw.HttpPost("A07"))
    nw.Jparse(nw.HttpPost("A08"))
    nw.Jparse(nw.HttpPost("A09"))
    nw.Jparse(nw.HttpPost("A0A"))
    nw.Jparse(nw.HttpPost("A0B"))
    nw.Jparse(nw.HttpPost("A0C"))
    nw.Jparse(nw.HttpPost("A0D"))
    nw.Jparse(nw.HttpPost("A0E"))
    nw.Jparse(nw.HttpPost("A0F"))
    nw.Jparse(nw.HttpPost("A0G"))
    nw.Jparse(nw.HttpPost("A0H"))
    nw.Jparse(nw.HttpPost("A0I"))
    nw.Jparse(nw.HttpPost("A0G"))
    nw.Jparse(nw.HttpPost("A0K"))
    nw.Jparse(nw.HttpPost("A0L"))
    nw.Jparse(nw.HttpPost("A0M"))
    nw.Jparse(nw.HttpPost("A0N"))
    nw.Jparse(nw.HttpPost("A0O"))
    nw.Jparse(nw.HttpPost("A0P"))
    nw.Jparse(nw.HttpPost("A0Q"))
    nw.Jparse(nw.HttpPost("A0R"))
    nw.Jparse(nw.HttpPost("A0S"))
  }

  def NBS_Task = {
    val taskType = "fsyd"
    var str3 = "http://data.stats.gov.cn/easyquery.htm?m=getOtherWds&dbcode=csyd&rowcode=zb&colcode=sj&wds=%5B%5D"
    val nw = new NationalData_WkOther
    NationalData_WkOther.map += (("", "null"))
    nw.dbcode = taskType
    nw.rowcode = "reg"
    nw.wdcode = "reg"
    nw.uri = "http://data.stats.gov.cn/easyquery.htm?cn=E0101"
    nw.httpCookie
    val str = s"http://data.stats.gov.cn/easyquery.htm?m=getOtherWds&dbcode=$taskType&rowcode=reg&colcode=sj&wds=%5B%5D"
    // nw.nw.HttpPost("A01")



    println("分类标签已获取")
    NationalData_WkOther.dataType = "主要城市月度价格"
    nw.firstHttpGet("http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=csyd&rowcode=reg&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A01010102%22%7D%5D&dfwds=%5B%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22201512-201512%22%7D%5D")
    //nw.addClassifyId(ht.httpGet(str, uAr))
    // nw.addClassifyId2(ht.httpGet(str3, uAr))
    nw.Jparse2(nw.HttpPost("reg"))
    nw.Jparse(nw.HttpPost("reg"))

  }
}

class NBS_HttpReq extends httpCom {}