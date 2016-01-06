package com.IClrawler

import akka.actor.{ActorSystem, Props}
import com.IClrawler.Manage.Cric_Task

/**
 * Created by cz on 2015/8/7.
 */

object Main {
  def main(args: Array[String]) {
    val ct = new Cric_Task
    ct.monthTask

  }
}



