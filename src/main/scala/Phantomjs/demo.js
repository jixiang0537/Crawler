/**
 * Created by dell on 2015/12/25.
 */

var page = require('webpage').create();
page.open('http://example.com', function (status) {
    console.log("Status: " + status);
    if (status === "success") {
        console.log(page.content)
    }
    phantom.exit();
});
