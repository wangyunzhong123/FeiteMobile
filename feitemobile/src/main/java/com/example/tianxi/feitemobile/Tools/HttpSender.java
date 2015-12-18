package com.example.tianxi.feitemobile.Tools;

import android.os.Handler;

import org.json.JSONObject;

/**
 * Created by tianxi on 15-12-8.
 */
public class HttpSender {

    HttpUtil http;

    public HttpSender(String uri, String method, Handler handler) {
        http = new HttpUtil(uri, method,handler);
    }
    public HttpSender(String uri, String method, JSONObject params) {
        http = new HttpUtil(uri, method, params);
    }
    public HttpSender(String uri, String method, JSONObject params, Handler handler) {
        http = new HttpUtil(uri, method, params, handler);
    }
    public HttpSender(String uri, String method, JSONObject params, Handler handler,String sessionId) {
        http = new HttpUtil(uri, method, params, handler,sessionId);
    }

    public void send()
    {
        http.start();
    }


}
