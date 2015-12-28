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
    "cric2015=C225C8BFAE65F64D8A108B5118ADEE5293882D8F69832F9577DC025FFFE3EBE76B17B48F7C79A3D25CF46E01DCCB1AE4D5251A78407550A731815004030F6F6BA5FD53AA188487C6D32B8A9D; cric2015_token=username=oJgegXR+5oTEDGyVvTs2RQ==&verifycode=lXBXvEmwIHaDPdRu49838w==&token=kX0v+GqlwuYPhK06dTY0kLqaUjRoSUeXiEJCW3CPHQARZPvf4214wm/NKyJPMSma&usermobilephone=US0MjG5AgDCZeQFCXzftYQ==&userid=twA47Tj+N+Leoj7tJ6sXCY6GddSuy9pMlljOjP3jz2mF4iJKYN71kc8c8r3h2K7R; Hm_lvt_dca78b8bfff3e4d195a71fcb0524dcf3=1449023029,1449455931,1451030680; Hm_lpvt_dca78b8bfff3e4d195a71fcb0524dcf3=1451030853; StatisticCenter=150002%2C150003%2C152002%2C150004;"
  }
}