"use strict";
var system = require('system');
var webPage = require('webpage');
var fs = require('fs');
var page = webPage.create();
var num = 200
var i= 1

// page.settings.userAgent = 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.34
// (KHTML, like Gecko)Safari/534.34';
// Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.34 (KHTML, like Gecko)
// PhantomJS/1.9.7 Safari/534.34
// console.log('system.args.length---'+system.args.length);

var receive_response_count_current = 0;
var receive_response_count_last = 0;
var receive_response_fail_count = 0;
// 爬虫开始爬的起始路径

var root_url = 'http://www.landchina.com/default.aspx?tabid=263';


//启动周期检查有无回应
//setInterval(checkNoResponse,10000);
page.onAlert = function (msg) {
    console.log('ALERT: ' + msg);
};
var ar = new Array

page.onLoadFinished = function (status) {
    if(i===2){
        console.log(page.content)
    }

    if (!(num ===1)) {

        page.evaluate(function (num) {

            var queryStr = 'input[onclick^="QueryAction.GoPage"]';
            var arr = document.querySelectorAll(queryStr)
            arr[0].setAttribute('onclick', 'QueryAction.GoPage(\'TAB\',' + num + ',200)')
            var ev = document.createEvent("MouseEvents");
            ev.initEvent("click", true, true);

            if (arr.length == 0) {
                window.alert('arr.length == 0');
            }
            else {

                arr[0].dispatchEvent(ev);
            }
        }, num);

    }else{
        console.log(page.content)
        phantom.exit()
    }
    i++
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
    console.log('Error code: ' + resourceError.errorCode + '. Description: '
        + resourceError.errorString);
};


function openPage(root_url) {
    page.open(root_url, function (status) {
        if (status === "success") {
            //    console.log('page open is success');
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

