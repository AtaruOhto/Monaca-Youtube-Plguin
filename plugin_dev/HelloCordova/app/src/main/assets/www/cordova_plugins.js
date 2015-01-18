cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/io.monaca.youtube/www/youtube.js",
        "id": "io.monaca.youtube.MonaYoutube",
        "clobbers": [
            "monaca.youtube"
        ]
    }
];
module.exports.metadata =
// TOP OF METADATA
{
    "io.monaca.youtube": "1.0.0"
}
// BOTTOM OF METADATA
});