package com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dell on 2015/12/25.
 */
public class main {
    public static void main(String[] args) {
        runJS();

    }
    public static void runJS(){

        StringBuilder conStr = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        try {
            Process data = runtime.exec("E:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe E:\\Cz\\ICrawler\\src\\main\\scala\\Phantomjs\\mainJs.js 200");
            InputStream conInput = data.getInputStream();
            BufferedReader cbr = new BufferedReader(new InputStreamReader(conInput, "utf-8"));
            String clien = null;
            while ((clien = cbr.readLine()) != null) {
                conStr.append(clien);
                conStr.append("\n");

            }
            if (conStr != null) {
                System.out.print(conStr);
            } else {
                System.out.print("conStr = = null");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void jsoupParser(String str) {
        Document dm = Jsoup.parse(str);

    }
}
