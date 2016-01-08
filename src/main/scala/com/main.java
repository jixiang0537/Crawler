package com;

import com.TaskWork.runPhantom;
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
    runPhantom rp = new runPhantom();
        rp.runJS(2);
     }

    public void jsoupParser(String str) {
        Document dm = Jsoup.parse(str);

    }
}
