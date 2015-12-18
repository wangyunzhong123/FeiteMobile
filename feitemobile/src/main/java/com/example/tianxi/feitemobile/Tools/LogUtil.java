package com.example.tianxi.feitemobile.Tools;

import android.util.Log;

/**
 * Created by tianxi on 15-12-8.
 *自定义的日志工具，在上线后将LEVEL更改为NOTHING即可，
 * 便不会打印任何信息，避免机密信息泄露
 *
 *
 */
public class LogUtil {

    public static final int VERBOSE = 1;

    public static final int DEBUG = 2;

    public static final int INFO = 3;

    public static final int WARN = 4;

    public static final int ERROR = 5;

    public static final int NOTHING = 6;

    public static final int LEVEL = VERBOSE;//上线后更改这个值为NOTHING即可

    public static void v(String tag,String msg){
        if(LEVEL <= VERBOSE){
            Log.v(tag, msg);
        }
    }

    public static void d(String tag,String msg){
        if(LEVEL <= DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void i(String tag,String msg){
        if(LEVEL <= INFO){
            Log.i(tag, msg);
        }
    }

    public static void w(String tag,String msg){
        if(LEVEL <= WARN){
            Log.w(tag, msg);
        }
    }

    public static void e(String tag,String msg){
        if(LEVEL <= ERROR){
            Log.e(tag, msg);
        }
    }


}
