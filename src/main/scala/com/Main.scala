package com.IClrawler

import java.io._


/**
 * Created by cz on 2015/8/7.
 */
object Main {
  def main(args: Array[String]) {
    val conStr: StringBuilder = new StringBuilder
    val runtime: Runtime = Runtime.getRuntime
    try {
      val data = runtime.exec("E:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe E:\\Cz\\ICrawler\\src\\Phantomjs\\demo.js");
      val conInput = data.getInputStream();
      val cbr = new BufferedReader(new InputStreamReader(conInput));
      var  clien :String= "";
      while ((clien = cbr.readLine()) != null) {
        conStr.append(clien);
      }
      if (conStr != null) {
        System.out.print(conStr);
      } else {
        System.out.print("conStr = = null");
      }

    //    val responsUri = "http://www.landchina.com/default.aspx?tabid=263"
    //    val host = "www.landchina.com"
    //    val unit = new Units
    //
    //    val ar = new util.ArrayList[BasicNameValuePair]
    //    //    ar.add(new BasicNameValuePair("__EVENTVALIDATION", "asdasd"))
    //    //    ar.add(new BasicNameValuePair("__VIEWSTATE", "asdas"))
    //    ar.add(new BasicNameValuePair("hidComName", "default"))
    //    ar.add(new BasicNameValuePair("TAB_QuerySubmitConditionData", ""))
    //    ar.add(new BasicNameValuePair("TAB_QuerySubmitOrderData", ""))
    //    ar.add(new BasicNameValuePair("TAB_RowButtonActionControl", ""))
    //    ar.add(new BasicNameValuePair("TAB_QuerySubmitPagerData", "100"))
    //    ar.add(new BasicNameValuePair("TAB_QuerySubmitSortData", ""))
    //
    //    val ht = new httpTest
    //    val con  = ht.httpPost(responsUri, unit.setheader(host, responsUri), ar)
    //    val arKey = ht.josupParse(con )
    //
    //    val ar2 = new util.ArrayList[BasicNameValuePair]
    //        ar2.add(new BasicNameValuePair("__EVENTVALIDATION", arKey(1)))
    //        ar2.add(new BasicNameValuePair("__VIEWSTATE", arKey(0)))
    //    ar2.add(new BasicNameValuePair("hidComName", "default"))
    //    ar2.add(new BasicNameValuePair("TAB_QuerySubmitConditionData", ""))
    //    ar2.add(new BasicNameValuePair("TAB_QuerySubmitOrderData", ""))
    //    ar2.add(new BasicNameValuePair("TAB_RowButtonActionControl", ""))
    //    ar2.add(new BasicNameValuePair("TAB_QuerySubmitPagerData", "100"))
    //    ar2.add(new BasicNameValuePair("TAB_QuerySubmitSortData", ""))
    //
    //    ht.httpPost(responsUri, unit.setheader(host, responsUri), ar2)
    //    def downlond(uriString: String, num: Int) = {
    //      try {
    //        val url = new URL(uriString);
    //        val dataInputStream = new DataInputStream(url.openStream());
    //        val imageName = "D:\\cric图片\\" + num + ".jpg";
    //        val fileOutputStream = new FileOutputStream(new File(imageName));
    //
    //        val buffer = new Array[Byte](10240);
    //        var length = 1
    //        while (length > 0) {
    //          length = dataInputStream.read(buffer)
    //          if (length > 0) {
    //            fileOutputStream.write(buffer, 0, length);
    //          }
    //        }
    //        println(imageName + "下载结束")
    //        dataInputStream.close();
    //        fileOutputStream.close();
    //      }catch {
    //        case  e:Exception => println(e.getMessage+num)
    //      }
    //    }
    //
    //
//    def downLoadStart(): Unit ={
//      val fs = new FileInputStream("E:\\缺总平图小区.xlsx");
//      val xw = new XSSFWorkbook(fs)
//      val xe = new XSSFExcelExtractor(xw);
//      var num = 0
//      xe.getText.split("\\n").foreach(n(_))
//      def n(string: String) {
//        println(string + "nun = " + num)
//        if (num>17057){
//          downlond(string,num)
//        }
//        num += 1
//      }
//    }
//    downLoadStart()
//      }
// val ck = new Cric_Task
//      ck.dayTask
//      ck.weekTask
//      ck.monthTask
//      ck.monthLandTask
//

  }}}




