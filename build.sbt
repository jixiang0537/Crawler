name := "ICrawler"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.apache.poi" % "poi" % "3.13",
  "org.apache.httpcomponents" % "httpclient" % "4.5.1",
  "org.jsoup" % "jsoup" % "1.8.2",
  "org.apache.httpcomponents" % "httpmime" % "4.3.6",
  "com.typesafe.akka" %% "akka-actor" % "2.3.10",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "com.typesafe.akka" %% "akka-remote" % "2.3.10",
  "com.hynnet" % "json-lib" % "2.4",
  "org.apache.poi" % "poi" % "3.13",
  "org.apache.poi" % "poi-ooxml" % "3.13",
  "org.apache.poi" % "poi-ooxml-schemas" % "3.13",
  "org.apache.xmlbeans" % "xmlbeans" % "2.6.0",
  "com.hynnet" % "sqljdbc4-chs" % "4.0.2206.100",
  "log4j" % "log4j" % "1.2.17"

)