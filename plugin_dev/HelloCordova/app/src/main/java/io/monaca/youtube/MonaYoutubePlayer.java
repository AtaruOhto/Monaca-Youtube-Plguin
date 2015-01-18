package io.monaca.youtube;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ataru on 2015/01/17.
 */
public class MonaYoutubePlayer extends CordovaPlugin {

    private Context context;
    private final static String TAG = "MonaYoutubePlayer";
    private final static int VIDEO_ID_INDEX = 0;
    private final static int DEV_KEY_INDEX = 1;
    private final static int OPTION_INDEX = 2;
    private final static String ACTION_PLAY_VIDEO_WITH_STANDALONE = "playVideoWithStandAlonePlayer";
    private final static String ACTION_PLAY_VIDEO_WITH_YOUTUBE_APP = "playVideoWithInstalledYoutubeApp";
    private final static String STARTTIME_OPTION_KEY = "startTime";
    private final static String AUTOPLAY_OPTION_KEY = "autoPlay";
    private final static String LIGHT_BOX_OPTION_KEY = "lightBoxMode";
    private final static String FULLSCREEN_KEY = "fullscreen";
    private final static String FINISH_ON_END = "finishOnEnd";

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) {

        context = cordova.getActivity();
        String videoId = null;
        String youtubeDevKey = null;
        JSONObject playerOptions = null;

        try {
            videoId = args.getString(VIDEO_ID_INDEX);
            youtubeDevKey = args.getString(DEV_KEY_INDEX);
            playerOptions = args.getJSONObject(OPTION_INDEX);
        } catch (JSONException e) {
            showToastLongly("Argument you specified contain errors. Please fix and try again");
            Log.e(TAG, "JSON Exception");
        }


        if(action.equalsIgnoreCase(ACTION_PLAY_VIDEO_WITH_STANDALONE)) {

            try {
                int startTimeMillis = playerOptions.getInt(STARTTIME_OPTION_KEY);
                boolean autoPlayFlag = playerOptions.getBoolean(AUTOPLAY_OPTION_KEY);
                boolean lightBoxModeFlag = playerOptions.getBoolean(LIGHT_BOX_OPTION_KEY);
                playVideoWithStandAlonePlayer(videoId, youtubeDevKey, startTimeMillis, autoPlayFlag, lightBoxModeFlag);
            }catch (JSONException e) {
                showToastLongly("Argument you specified contain errors. Please fix and try again");
                Log.e(TAG, "JSON Exception in Options");
            }

        } else if (action.equalsIgnoreCase(ACTION_PLAY_VIDEO_WITH_YOUTUBE_APP)) {

            if (checkIsYoutubeAppInstalled()) {

                try {
                    boolean isFullscreen = playerOptions.getBoolean(FULLSCREEN_KEY);
                    boolean isFinishOnVideoEnd = playerOptions.getBoolean(FINISH_ON_END);
                    playVideoWithInstalledYoutubeApp(videoId, isFullscreen, isFinishOnVideoEnd);
                }catch (JSONException e) {
                    showToastLongly("Argument you specified contain errors. Please fix and try again");
                    Log.e(TAG, "JSON Exception in Options");
                }

            }

        }
        return true;
    }

    private void playVideoWithStandAlonePlayer(String videoId, String youtubeDevKey, int startTimeMillis, boolean autoplay, boolean lightboxMode) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, youtubeDevKey, videoId, startTimeMillis, autoplay, lightboxMode);
        context.startActivity(intent);
    }

    private boolean checkIsYoutubeAppInstalled() {

        if (YouTubeIntents.isYouTubeInstalled(context)) {

            if (YouTubeIntents.canResolvePlayVideoIntent(context)) {
                Log.i(TAG, "The device has appropriate youtube app.");
                return true;
            }

            showToastShortly("Youtube App found. But the Youtube App can't resolve intent passed from this Application.");
            return false;
        }

        showToastShortly("No Youtube App found on this device. Please confirm the Youtube App is installed on the device.");
        return false;

    }

    private void playVideoWithInstalledYoutubeApp(String videoId, boolean isFullscreen, boolean isFinishOnEnd) {
        Intent youtubeAppIntent = YouTubeIntents.createPlayVideoIntentWithOptions(context, videoId, isFullscreen, isFinishOnEnd);
        youtubeAppIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(youtubeAppIntent);
    }

    private void showToastShortly(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void showToastLongly(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
//
//    private Object checkIsAPIArgumentValid(JSONArray args, int keyIndex) {
//
//        Object argument = null;
//
//        try {
//            argument = args.get(keyIndex);
//        } catch (JSONException e) {
//            showToastLongly("Argument" + keyIndex + "is invalid. Please make sure you specified correct parameters in the API.");
//        }
//        return argument;
//
//    }
//
//    private Object checkIsOptionValueValid(JSONObject args, String keyName) {
//
//        Object argument = null;
//
//        try {
//
//            argument = args.get(keyName);
//
//        } catch (JSONException  e) {
//            showToastLongly("Argument" + keyName + "is invalid. Please make sure you specified correct parameters in the API.");
//        }
//
//        return argument;
//
//    }

}