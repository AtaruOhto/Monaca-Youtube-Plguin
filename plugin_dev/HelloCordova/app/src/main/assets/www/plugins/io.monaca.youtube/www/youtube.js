cordova.define("io.monaca.youtube.MonaYoutube", function(require, exports, module) {

var MonaYoutube = function() { };

MonaYoutube.prototype.playVideoWithStandAlonePlayer = function(videoId, devKey, options) {

    checkIsNotNullValDefinedAsArray([videoId, devKey]);

    var options = {
        "startTime"    :  options.startTime || 0,
        "autoPlay"     :  checkIsDefined(options.autoPlay) ? options.autoPlay : true,
        "lightBoxMode" :  checkIsDefined(options.lightBoxMode) ? options.lightBoxMode : true
    }

    cordova.exec(null, null,'MonaYoutubePlayer','playVideoWithStandAlonePlayer', [videoId, devKey, options]);
};

MonaYoutube.prototype.playVideoWithStandAlonePlayer = function(videoId, devKey, options) {

    if(!videoId || !devKey) {
        throw new Error("videoId or Youtube Developer Key is null");
    }

    var options = {
        "startTime"    :  options.startTime || 0,
        "autoPlay"     :  checkIsOptionalValDefined(options.autoPlay) ? options.autoPlay : true,
        "lightBoxMode" :  checkIsOptionalValDefined(options.lightBoxMode) ? options.lightBoxMode : true
    }

    cordova.exec(null, null,'MonaYoutubePlayer','playVideoWithStandAlonePlayer', [videoId, devKey, options]);
};

MonaYoutube.prototype.playVideoWithInstalledYoutubeApp = function(videoId, options) {

    checkIsNotNullValDefinedAsArray([videoId]);

    var options = {
        "fullscreen"   :  checkIsOptionalValDefined(options.fullscreen) ? options.fullscreen : true,
        "finishOnEnd"  :  checkIsOptionalValDefined(options.finishOnEnd) ? options.finishOnEnd : true
    }

    cordova.exec(null, null,'MonaYoutubePlayer','playVideoWithInstalledYoutubeApp', [videoId, '', options]);

}

function checkIsNotNullValDefinedAsArray(valueArray) {

    for (var i = 0; i < valueArray.length; i++) {
        if (!valueArray[i] == null) {
            throw new Error(valueArray[i] + "is null!");
        }
    }

}


function checkIsOptionalValDefined(value) {

    if (value == null) {
        return false;
    }

    return true;

}


module.exports = new MonaYoutube();

});
