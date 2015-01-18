# Monaca Youtube Plugin (Cordova Plugin)

> Unofficial Monaca Youtube Plugin for Android


## Installation

### Monaca

1. Download This plugin.
2. From Monaca Cordova Plugin Management Menu, upload this plugin to <a href="https://monaca.io/" target="_blank">Monaca</a>.

### Playing Video With Youtube Standalone Player

In order to play vide with Youtube Stand Alone Player, you need Youtube Developer Key. If you don't have developer key, please get key from <a href="https://developers.google.com/youtube/android/player/register" target="_blank">Google Developer Console</a>.


monaca.youtube.playVideoWithStandAlonePlayer (videoId, developerKey, options);
***

* videoId (String) : Video ID.
* developerKey (String) : Your developer Key.
* options (JSON) : Player options.

<dl>
  <dt>Options</dt>
  	<dd>startTime (Number): Start time of the video (default : 0)</dd>
  	<dd>autoPlay (Boolean): Should start automatically or not (default : true)</dd>
  	<dd>lightBoxMode (Boolean): Should play with lightBoxMode (default : true)</dd>
</dl>


#### Example


           
	// Specify Video ID and your Developer Key.
	monaca.youtube.playVideoWithStandAlonePlayer(
		'zua0_IXcPFY',
		'Your Developer Key',
		{
			"startTime"    :  0,
			"autoPlay"     :  true,
			"lightBoxMode" :  true
		}
	);



### Playing Video With Installed Youtube App


monaca.youtube.playVideoWithStandAlonePlayer (videoId, developerKey, options);
***

* videoId (String) : Video ID.
* options (JSON) : Player options.

<dl>
  <dt>Options</dt>
  	<dd>fullscreen (Boolean): Should with Full Screen (default : true)</dd>
  	<dd>finishOnEnd (Boolean): Should go back to previous Activity when the video finish (default : true)</dd>
</dl>


#### Example

	
	// Specify Video ID.
	monaca.youtube.playVideoWithInstalledYoutubeApp(
		'zua0_IXcPFY',
		{
			"fullscreen"    : false,
			"finishOnEnd"   : true
		}
	);
	
	




