package com.example.tianxi.feitemobile.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.tianxi.feitemobile.Application.MyApplication;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by tianxi on 15-12-8.
 */
public class HttpUtil extends Thread{

    private final String IP = "http://www.feite.org";
    private String httpUrl;
    private URL url;
    private HttpURLConnection conn;
    private String method;
    private JSONObject params;
    private Handler handler;
    private static String sCookie;
    private static String sessionId;

    /*
    *此处的url为绝对的url，不再使用定义的IP 作为前缀。
     * 提供给某些特殊的请求
      * */
    public HttpUtil(String uri,String method, Handler handler){
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.method = method;
        this.handler = handler;
    }

    /*此处的url需要全局的IP 作为前缀，还有方法名，请求参数*/
    public HttpUtil(String uri, String method, JSONObject params)
    {
        httpUrl = IP+uri;
        this.method = method;
        this.params = params;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 包含有回调handler
    * */
    public HttpUtil(String uri, String method, JSONObject params, Handler handler)
    {
        this(uri, method, params);
        this.handler = handler;
        this.sessionId = "";
    }

    /*
    * 使用sessionID
    * */
    public HttpUtil(String uri, String method, JSONObject params, Handler handler,String sessionId)
    {
        this(uri, method, params);
        this.handler = handler;
        this.sessionId = sessionId;
    }
    public void run()
    {
        //在没有网络的情况下，这些url请求的内容由本地保存的提取
//        if(!NetworkDetector.detect(MyApplication.getContext())){
//            SharedPreferences p = MyApplication.getContext().getSharedPreferences("URL_INFO", Context.MODE_PRIVATE);
//            String result = p.getString(url.toString(), "[]");
//            if(result.equals("[]")){
//                Log.e("从共享sharePreferences没有结果      ", result);
//                Toast.makeText(MyApplication.getContext(), "请检查您的网络状态", Toast.LENGTH_SHORT).show();
//                return ;
//            }
//            Log.e("从共享sharePreferences的结果是      ",result);
//            Message msg = new Message();
//            msg.obj = result;
//            msg.what = 0;
//            handler.sendMessage(msg);
//            return ;
//        }

        //在有网络的情况下
        try {
            LogUtil.d("请求的url 是。。。。",url.toString());

            conn = (HttpURLConnection) url.openConnection();
            if(null != sCookie && sCookie.length() > 0)
            {
                LogUtil.d("sCookie是。。。。",sCookie);
                conn.setRequestProperty("Cookie", sCookie);
            }
            conn.setRequestMethod(this.method);
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("X-CSRF-Token", sessionId);
            conn.setReadTimeout(60000);
//			conn.setRequestProperty("Cookie", );
            if(this.method.equalsIgnoreCase("POST"))
            {
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                OutputStream os = conn.getOutputStream();
                os.write(params.toString().getBytes("UTF-8"));
                os.flush();
                os.close();
                LogUtil.d("返回ResponseCode，，和ResponseMessage分别是。。", conn.getResponseCode() + ",," + conn.getResponseMessage());

            }
            if (conn.getResponseCode() == 200) {

                List<String> cookies = conn.getHeaderFields().get("Set-Cookie");

                if(null != cookies && cookies.size() > 0)
                {
                    String cookietmp = cookies.get(0);
                    System.out.println(cookietmp);
                    sCookie = cookietmp.split(";", 2)[0];
                    System.out.println("sCookiexxxxxxxxxxxxxxxx "+sCookie);
                }
                InputStream is = conn.getInputStream();
                byte[] data = readStream(is);
                is.close();
                Message msg = new Message();
                String result = new String(data);
                LogUtil.e("xxxx result = ",result);
                msg.obj = result;
                msg.what = 0;
                handler.sendMessage(msg);
                //保存信息
                SharedPreferences p = MyApplication.getContext().getSharedPreferences("URL_INFO", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = p.edit();
                if(p.contains(url.toString())){
                    LogUtil.e("共享里有url，删除",url.toString());
                    edit.remove(url.toString());
                }
                edit.putString(url.toString(), result);
                edit.commit();

            } else {
                System.out.println("链接失败！！！");
            }
        }catch (Exception e) {
            Toast.makeText(MyApplication.getContext(), "请检查您的网络状态", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();
        //bout.
        return bout.toByteArray();
    }

}
