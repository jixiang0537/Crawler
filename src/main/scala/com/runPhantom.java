package com;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * Created by dell on 2016/1/8.
 */
public class runPhantom {
    public String runJS(Integer num) {

        Runtime runtime = Runtime.getRuntime();

        String com = "E:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe --load-images=false E:\\Cz\\ICrawler\\src\\main\\scala\\Phantomjs\\mainJs.js " + num + "";
        try {
            Process data = runtime.exec(com);
            InputStream errInput = data.getErrorStream();
            InputStream conInput = data.getInputStream();
            String errStr = returnCon(errInput);
            String conStr = returnCon(conInput);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String returnCon(InputStream conInput) {
        try {
            StringBuilder conStr = new StringBuilder();

            BufferedReader cbr = new BufferedReader(new InputStreamReader(conInput, "utf-8"));
            String clien = null;
            while ((clien = cbr.readLine()) != null) {
                conStr.append(clien);
                conStr.append("\n");
            }
            if (conStr != null) {
                return conStr.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";

    }

}
