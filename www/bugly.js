var exec = require('cordova/exec');

exports.initSDK = function(success, error, arg0) {
    exec(success, error, "Bugly", "initSDK", [arg0]);
};

exports.testJavaCrash = function() {
    exec(null, null, "Bugly", "testJavaCrash", []);
};

exports.testSIGTRAP = function() {
    exec(null, null, "Bugly", "testSIGTRAP", []);
};

exports.testPostContent = function(success, error, arg0) {
    exec(success, error, "Bugly", "postContent", [arg0]);
};
