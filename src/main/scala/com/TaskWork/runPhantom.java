package com.TaskWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dell on 2016/1/8.
 */
public class runPhantom {
    public void runJS(Integer num) {

        StringBuilder conStr = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        String com  = "E:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe E:\\Cz\\ICrawler\\src\\main\\scala\\Phantomjs\\mainJs.js "+num+"";
        try {
            Process data = runtime.exec(com);
            InputStream conInput = data.getInputStream();
            BufferedReader cbr = new BufferedReader(new InputStreamReader(conInput,"utf-8"));
            String clien = null;
            while ((clien = cbr.readLine())!= null ) {
                conStr.append(clien);
                conStr.append("\n");
            }
            if (conStr!= null) {
                System.out.print(conStr.toString());
            } else {
                System.out.print("conStr = = null");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
