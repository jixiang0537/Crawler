package com.IClrawler.Manage

import com.IClrawler.TaskInfo.{Cric_DataInfo, Cric_TaskLink}
import com.IClrawler.TaskWork.{Worker, Cric_HongGuan, Cric_LandWorker}

/**
 * Created by dell on 2015/10/15.
 */
class Cric_Task {
  val cityArray = Array[String](
  /* "北京","上海","广州","深圳","南京","杭州","天津","成都","重庆","武汉","沈阳","合肥",
    "宁波","无锡","苏州","厦门","常州","南昌","徐州","福州","扬州","江阴","泰州","泉州",
    "漳州","九江","南通","海门","启东","昆山","镇江","宜兴","淮安","盐城","晋江","马鞍山",
    "台州","青岛","济南","太原","烟台","唐山","大厂","固安","廊坊,","燕郊","香河","日照",
    "淄博","长春", "大连","哈尔滨","南宁",*/"珠海","东莞","佛山","中山","惠州","桂林","北海",
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
      println("任务 " + name + "抓取结束" + "成功" + Worker.i + "组数据  一共" + Worker.o + "个任务")
    }
  }

  def monthLandTask {
    val task1 = new Cric_DataInfo;
    for (name <- cityArray) {
      println("任务 " + name + " 开始抓取")
      task1.num_city = name
      val ll = new Cric_TaskLink(task1)
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
  val cricCookie: String = {
    "StatisticCenter=150002%2C150003%2C152002%2C150004; BIGipServerpool_10.0.7=34013194.20480.0000; cric2015=D996EC6088C232FA50D669415ADBF2AA2317D262A62AF17E1677CF26C00A5A058CE44164BD832A63582D9B28CBB2E0430EA692F6CF13D8EB2031DA20656234C0D4C2E4565C33CF8C3E40A685; cric2015_token=username=oJgegXR+5oTEDGyVvTs2RQ==&verifycode=RikHXmFt1dia8c7uEslHVA==&token=vlOmcwSuEc5yeLPBPEzmA9YXVP9yj1af8KoidE182i1YDJgAZlKqe1OHJfYg4qLo&usermobilephone=US0MjG5AgDCZeQFCXzftYQ==&userid=twA47Tj+N+Leoj7tJ6sXCY6GddSuy9pMlljOjP3jz2mF4iJKYN71kc8c8r3h2K7R; Hm_lvt_dca78b8bfff3e4d195a71fcb0524dcf3=1449455931,1451030680,1451453015,1451884298; Hm_lpvt_dca78b8bfff3e4d195a71fcb0524dcf3=1451884372"
  }
}