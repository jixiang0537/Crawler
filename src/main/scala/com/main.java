package com;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class main {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        int year = 1999;
        int m = 1;//月份计数
        while (year < 2016) {
            Calendar cal = Calendar.getInstance();//获得当前日期对象
            cal.clear();
            while (m < 13) {
                int month = m;
              //清除信息
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);//1月从0开始
                cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天

                System.out.println(sdf.format(cal.getTime()));

                int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

                //   System.out.println( count);

                for (int j = 0; j <= (count - 2); ) {
                    cal.add(Calendar.DAY_OF_MONTH, +1);
                    j++;
                    System.out.println(sdf.format(cal.getTime()));
                }
                m++;
            }
            year++;
        }
    }
}