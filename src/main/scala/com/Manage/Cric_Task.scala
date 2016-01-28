package com.IClrawler.Manage

import com.IClrawler.TaskInfo.{Cric_DataInfo, Cric_TaskLink}
import com.IClrawler.TaskWork.{Worker, Cric_HongGuan, Cric_LandWorker}

/**
 * Created by dell on 2015/10/15.
 */
class Cric_Task {
  val cityArray = Array[String](
 "北京","上海","广州","深圳","南京","杭州","天津","成都","重庆","武汉","沈阳","合肥",

    "宁波","无锡","苏州","厦门","常州","南昌","徐州","福州","扬州","江阴","泰州","泉州",
    "漳州","九江","南通","海门","启东","昆山","镇江","宜兴","淮安","盐城","晋江","马鞍山",
    "台州","青岛","济南","太原","烟台","唐山","大厂","固安","廊坊,","燕郊","香河","日照",
    "淄博","长春", "大连","哈尔滨","南宁","珠海","东莞","佛山","中山","惠州","桂林","北海",
    "清远","肇庆","柳州","汕头","西南","昆明","贵阳","遵义","丽江","西安","兰州","西宁",
    "乌鲁木齐","郑州","长沙","洛阳","宜昌","株洲","岳阳","海口","三亚","保亭","澄迈","儋州"  ,
    "万宁","文昌","五指山","定安","陵水","琼海"
  )

  def monthTask {
    val task1 = new Cric_DataInfo;
    for (name <- cityArray) {
      println("任务 " + name + " 开始抓取")
      task1.num_city = name
      val ll = new Cric_TaskLink(task1)
      ll.monthLink.foreach(x => new Worker(task1, x.split(";")))
      ll.monthLandLink.foreach(x => new Cric_LandWorker(task1, x(0).split(";"), x(1)))
      println("任务 " + name + "抓取结束" + "成功" + Worker.i + "组数据  一共" + Worker.o + "个任务")
    }
  }

  def hongGuanTask {
    val task1 = new Cric_DataInfo;
    for (name <- cityArray) {
      println("任务 " + name + " 开始抓取")
      task1.num_city = name
      val ll = new Cric_TaskLink(task1)
      ll.hongGuanLink.foreach(x => new Cric_HongGuan(task1, x.split(";")))
      println("任务 " + name + "抓取结束" + "成功" + Worker.i + "组数据  一共" + Worker.o + "个任务")
    }

  }

  def hongGuanMonthTask {
    val task1 = new Cric_DataInfo;
    for (name <- cityArray) {
      println("任务 " + name + " 开始抓取")
      task1.num_city = name
      val ll = new Cric_TaskLink(task1)
      ll.hongGuanMonthLink.foreach(x => new Cric_HongGuan(task1, x.split(";")))
      println("任务 " + name + "抓取结束" + "成功" + Worker.i + "组数据  一共" + Worker.o + "个任务")
    }

  }


  def weekTask {
    val task1 = new Cric_DataInfo;
    for (name <- cityArray) {
      println("任务 " + name + " 开始抓取")
      task1.num_city = name
      val ll = new Cric_TaskLink(task1)
      ll.weekLink.foreach(x => new Worker(task1, x.split(";")))
      ll.weekSecondLink.foreach(x => new Worker(task1, x.split(";")))
      println("任务 " + name + "抓取结束" + "成功" + Worker.i + "组数据  一共" + Worker.o + "个任务")
    }
  }

  def dayTask {
    val task1 = new Cric_DataInfo;
    for (name <- cityArray) {
      println("任务 " + name + " 开始抓取")
      task1.num_city = name
      val ll = new Cric_TaskLink(task1)
      ll.dayLink.foreach(x => new Worker(task1, x.split(";")))
      ll.daySesondLink.foreach(x => new Worker(task1, x.split(";")))
      println("任务 " + name + "抓取结束" + "成功" + Worker.i + "组数据  一共" + Worker.o + "个任务")
    }
  }

  def quarterTask {
    val task1 = new Cric_DataInfo;
    for (name <- cityArray) {
      println("任务 " + name + " 开始抓取")
      task1.num_city = name
      val ll = new Cric_TaskLink(task1)
      ll.quarterLink.foreach(x => new Worker(task1, x.split(";")))
      ll.quarterLandLink.foreach(x => new Cric_LandWorker(task1, x(0).split(";"), x(1)))
      println("任务 " + name + "抓取结束" + "成功" + Worker.i + "组数据  一共" + Worker.o + "个任务")
    }
  }

}

object Cric_Task {
  var cricCookie: String = {
    "BIGipServerpool_10.0.7=17235978.20480.0000; cric2015=5BC0919F7D6A3578E51C6243302B392CA96E06CF6F33D0C354A6FE808B55B4274F697000155C65612AEDFACCBEC845A46C06DFCD3C488969A95DDBBD73B977310E6F1C47614A0B40291737D8; cric2015_token=username=oJgegXR+5oTEDGyVvTs2RQ==&verifycode=x0GobxKERqkAtkwETPIbjQ==&token=TBxS5Dc9pXVCgg4NI3yXO0c0bqziZxCNW9lX33vsG8EMEf7tfx8PNAKuKRfnDn40&usermobilephone=US0MjG5AgDCZeQFCXzftYQ==&userid=twA47Tj+N+Leoj7tJ6sXCY6GddSuy9pMlljOjP3jz2mF4iJKYN71kc8c8r3h2K7R; Hm_lvt_dca78b8bfff3e4d195a71fcb0524dcf3=1452589588,1452649507,1453790430,1453962589; Hm_lpvt_dca78b8bfff3e4d195a71fcb0524dcf3=1453965204; StatisticCenter=150002%2C150003%2C152002%2C150004%2C152003%2C155002%2C155003%2C155004%2C155005%2C155006%2C155007%2C155008%2C154028%2C154004%2C151004%2C151010%2C151005%2C151002%2C150005%2C150006%2C155009%2C155015%2C155019%2C155010"
  }
}