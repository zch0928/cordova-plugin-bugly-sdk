package cn.com.gtmc.serviceapad.bugly;

import org.apache.cordova.BuildConfig;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import android.os.Build;
import android.util.Log;

import org.apache.cordova.device.Device;
import org.json.JSONException;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;

public class CDVBugly extends CordovaPlugin {
    public static final String TAG = "BuglyPlugin";
    private String APP_ID;
    private static final String BUGLY_APP_ID = "ANDROID_APPID";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        APP_ID = webView.getPreferences().getString(BUGLY_APP_ID,"");
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        switch (action) {
            case "initSDK":
                return this.initSDK(args, callbackContext);
            case "testJavaCrash":
                return this.testJavaCrash(args, callbackContext);
            case "testSIGTRAP":
                return this.testSIGTRAP(args, callbackContext);
            default:
                return false;
        }
    }

    private boolean initSDK(CordovaArgs args, CallbackContext callbackContext) {
        try {
            UserStrategy strategy = new UserStrategy(this.cordova.getActivity().getApplicationContext());
            strategy.setAppChannel("APAD");
            strategy.setAppVersion(Build.VERSION.RELEASE);
            strategy.setAppPackageName(this.cordova.getActivity().getApplicationContext().getPackageName());
            strategy.setDeviceID(Device.uuid);
            strategy.setDeviceModel(Build.BRAND + " " + Build.MODEL);
            CrashReport.setIsDevelopmentDevice(this.cordova.getActivity().getApplicationContext(), BuildConfig.DEBUG);

            boolean debugModel = true;
            CrashReport.initCrashReport(this.cordova.getActivity().getApplicationContext(), APP_ID, debugModel, strategy);
            CrashReport.setUserId(this.cordova.getActivity().getApplicationContext(), Device.uuid);
            Log.i(TAG, "Bugly sdk init success, version: " + Build.VERSION.RELEASE);
            callbackContext.success();
            return true;
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
            Log.e(TAG, "Bugly sdk init fail: " + e.getMessage(), e);
            return false;
        }
    }

    private boolean testJavaCrash(CordovaArgs args, CallbackContext callbackContext) {
        this.cordova.getActivity().runOnUiThread(CrashReport::testJavaCrash);
        callbackContext.success();
        return true;
    }

    private boolean testSIGTRAP(CordovaArgs args, CallbackContext callbackContext) {
        this.cordova.getActivity().runOnUiThread(CrashReport::testJavaCrash);
        callbackContext.success();
        return true;
    }

}
