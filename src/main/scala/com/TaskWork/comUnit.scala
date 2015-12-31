package com.TaskWork

import java.io._
import java.net.URL

import com.IClrawler.{httpTest, Units}
import org.apache.poi.hssf.extractor.ExcelExtractor
import org.apache.poi.hssf.usermodel.HSSFWorkbook

/**
 * Created by dell on 2015/12/30.
 */
class comUnit {



    def downlond(uriString: String, num: Int) = {
      try {
        val url = new URL(uriString);
        val dataInputStream = new DataInputStream(url.openStream());
        val imageName = "D:\\图片\\" + num + ".jpg";
        val fileOutputStream = new FileOutputStream(new File(imageName));

        val buffer = new Array[Byte](10240);
        var length = 1
        while (length > 0) {
          length = dataInputStream.read(buffer)
          if (length > 0) {
            fileOutputStream.write(buffer, 0, length);
          }
        }
        println(imageName + "下载结束")
        dataInputStream.close();
        fileOutputStream.close();
      } catch {
        case e: Exception => println(e.getMessage + num)
      }
    }


    def downLoadStart(): Unit = {
      val fs = new FileInputStream("D:\\小区总平图列表.xls");
      val xw = new HSSFWorkbook(fs)
      val xe = new ExcelExtractor(xw);
      var num = 0
      xe.getText.split("\\n").foreach(n(_))
      def n(string: String) {
        println(string + "nun = " + num)
        if (num >= 1) {
          downlond(string, num)
        }
        num += 1
      }
    }



    def comGet(uri:String="http://www.landchina.com/default.aspx?tabid=386&comname=default&wmguid=75c72564-ffd9-426a-954b-8ac2df0903b7&recorderguid=9595ee03-2952-430a-8466-ab0179ad1687"): String = {
      val unit = new Units
      val ht = new httpTest
      val content = ht.httpGet(uri, unit.setheader("www.landchina.com", "http://www.landchina.com/default.aspx?tabid=263"))
      content

    }

    def phantomRun(): Unit = {
      val conStr: StringBuilder = new StringBuilder
      val runtime: Runtime = Runtime.getRuntime
      try {
        val data = runtime.exec("E:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe E:\\Cz\\ICrawler\\src\\Phantomjs\\demo.js");
        val conInput = data.getInputStream();
        val cbr = new BufferedReader(new InputStreamReader(conInput));
        var clien: String = "";
        while ((clien = cbr.readLine()) != null) {
          conStr.append(clien);
        }
        if (conStr != null) {
          System.out.print(conStr);
        } else {
          System.out.print("conStr = = null");
        }

      }

    }

  }



