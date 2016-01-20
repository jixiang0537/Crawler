"use strict";
var system = require('system');
var webPage = require('webpage');
//var fs = require('fs');
var startDate = system.args[1]
var subNum = system.args[2]
var page = require('webpage').create(),
    server = 'http://www.landchina.com/default.aspx?tabid=263',
    data = 'hidComName=default&TAB_QueryConditionItem=9f2c3acd-0256-4da2-a659-6949c4671a2a&TAB_QuerySortItemList=282%3AFalse&TAB_QuerySubmitConditionData=9f2c3acd-0256-4da2-a659-6949c4671a2a%3A' + startDate + '%7E' + startDate + '&TAB_QuerySubmitOrderData=282%3AFalse&TAB_RowButtonActionControl=&TAB_QuerySubmitPagerData=' + subNum + '&TAB_QuerySubmitSortData=';

page.open(server, 'post', data, function (status) {
    if (status !== 'success') {
        console.log('Unable to post!');
    } else {
        console.log(page.content);
    }
    phantom.exit();
});