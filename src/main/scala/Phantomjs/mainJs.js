"use strict";
var system = require('system');
var webPage = require('webpage');
var fs = require('fs');
var page = webPage.create();
var number = 0;
var num = 2;
// page.settings.userAgent = 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.34
// (KHTML, like Gecko)Safari/534.34';
// Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.34 (KHTML, like Gecko)
// PhantomJS/1.9.7 Safari/534.34
// console.log('system.args.length---'+system.args.length);

var receive_response_count_current = 0;
var receive_response_count_last = 0;
var receive_response_fail_count = 0;
// ���濪ʼ������ʼ·��

var root_url = 'http://www.landchina.com/default.aspx?tabid=263';


//�������ڼ�����޻�Ӧ
//setInterval(checkNoResponse,10000);
page.onAlert = function (msg) {
    console.log('ALERT: ' + msg);
};


page.onLoadFinished = function (status) {
    if (number>3){
        phantom.exit();
    }
    page.render('E:\\Cz\\' + number + '.png')
  //  fs.write('E:\\Cz\\' + number + '.txt', page.content, 'a');
    console.log(page.content)
    number++
    console.log('load finish--------');
    console.log('page.title---' + page.title);

    console.log('page.url----' + page.url);
    page.evaluate(function (num) {

        var queryStr ='a[onclick="QueryAction.GoPage(\'TAB\','+num+')"]';
        var arr = document.querySelectorAll(queryStr);
        var ev = document.createEvent("MouseEvents");
        ev.initEvent("click", true, true);

        if (arr.length == 0) {
            window.alert('arr.length == 0');
        }
        else  {
            window.alert(arr[arr.length-1].innerHTML);
            arr[1].dispatchEvent(ev);
        }
    },num);
    num++
};

page.onUrlChanged = function (targetUrl) {
    // console.log('New URL: ' + targetUrl);
    /*
     * if((targetUrl.indexOf("http://www.baidu.com/s?"))>-1){
     * page.open(targetUrl); }
     */
};

page.onResourceReceived = function (response) {

};


page.onResourceError = function (resourceError) {
    //console.log('Unable to load resource (#' + resourceError.id + 'URL:'
    //    + resourceError.url + ')');
    //console.log('Error code: ' + resourceError.errorCode + '. Description: '
    //    + resourceError.errorString);
};


function openPage(root_url) {
    page.open(root_url, function (status) {
        if (status === "success") {
            console.log('page open is success');
        } else {
            phantom.exit();
        }
        // console.log('page.open is finished');
    });
}

function closePage() {
    page.close();
}

openPage(root_url);

