package com

import org.apache.log4j.{PropertyConfigurator, Logger}

/**
 * Created by dell on 2016/2/25.
 */
object MyLogger {
  PropertyConfigurator.configure("log4j.properties");

  def apply(cla: Class[_]) = {
    val log = Logger.getLogger(cla)
    log
  }
}
