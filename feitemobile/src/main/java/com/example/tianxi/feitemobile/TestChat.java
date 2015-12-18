package com.example.tianxi.feitemobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.red5.server.net.rtmp.RTMPClient;

import com.example.tianxi.feitemobile.testrtmp.publisher.PublishClient;
import com.smaxe.uv.client.INetConnection;
import com.smaxe.uv.client.INetStream;
import com.smaxe.uv.client.License;
import com.smaxe.uv.client.NetConnection;
import com.smaxe.uv.client.NetStream;

import java.io.IOException;
import java.util.Map;


public class TestChat extends ActionBarActivity {
    private static final String TAG = "TestChat";

    private EditText chatMessage;
    private Button sendbtn;
    private Button nextbtn;
    private Button connectbtn;

    private String roomId;
    private String roomName;
    private String userId;
    private String userName;
    private String role;

    private Button toWebView;

    private PublishClient client;

    //自己实现
    NetConnection netconn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chat);
        client = new PublishClient();
        initUI();
    }

    public void initUI(){
        chatMessage = (EditText)findViewById(R.id.chatText);
        sendbtn = (Button)findViewById(R.id.chatBtn);
        nextbtn = (Button)findViewById(R.id.nextbtn);
        connectbtn = (Button)findViewById(R.id.connectbtn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("点击发送按钮：  ","");
                String msg = ((EditText) findViewById(R.id.chatText)).getText().toString().trim();
                Object[] str = new Object[2];
                str[0] = roomId;
                str[1] = msg;

               // RTMPConnectionUtil.connection.call("sendPubMessage", null, str);
                client.getRtmpClient().getConnection().notify("sendPubMessage",str);

                ((EditText) findViewById(R.id.chatText)).setText("");

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestChat.this,TestMainActivity.class);
                startActivity(intent);

            }
        });

        connectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("点击连接按钮：  ","");
                initData();
                juv_client_my();
//
//                String red5url = "rtmp://120.20.105.159/FeiteServer/"+roomId;
//                Log.e("red5Url:  ",red5url);
//                RTMPConnectionUtil.connection.connect(red5url, roomId, roomName, userId, userName, role);

//                String publishName = "FeiteServer/";
//                String localFile = "red5.flv";
//                String host = "120.20.105.159";
//                int port = 1935;
//                String app = "FeiteServer";
//
//                PublishClient client = new PublishClient();
//
//                client.setHost(host);
//                client.setPort(port);
//                client.setApp(app);
//
//                Object[] object = new Object[2];
//                object[0] = new Integer(1);
//                object[1] = new String("wang");
//                client.start(publishName, "live", object);
//
//                while(client.getState() != PublishClient.PUBLISHED){
//                   // Thread.sleep(500);
//                }




            }
        });

        toWebView = (Button)findViewById(R.id.toWebViewbtn);
        toWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestChat.this,WebViewActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initData(){
        Log.e("初始化房间信息：  ","");
        roomId = ((EditText)findViewById(R.id.roomId)).getText().toString().trim();
        roomName = ((EditText)findViewById(R.id.roomName)).getText().toString().trim();
        userId = ((EditText)findViewById(R.id.userId)).getText().toString().trim();
        userName = ((EditText)findViewById(R.id.userName)).getText().toString().trim();
        role = ((EditText)findViewById(R.id.role)).getText().toString().trim();
        Log.e("房间信息：  ",roomId+"  "+roomName+"  "+userId+"  "+userName+"  "+role );
    }

    public void onSendPubMessage(String receivemsg){
        Log.e("接收到的消息：  ",receivemsg);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void juv_client_my(){
        //如果对象不存在，则创建对象
        License.setKey("E2409-023F8-7A300-664D9-CF1D8");
        //License.setKey("E3100-4025F-97200-0A1B8-9CFC7");
        netconn = new NetConnection();

        if(netconn == null){

            netconn = new NetConnection();
        }
        String red5url = "rtmp://120.26.105.159/FeiteServer/154";//rtmp://www.ikktong.com/live/1
        //String red5url = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
        //String red5url = "rtmp://www.ikktong.com/live/1";
        //如果未与red5连接，则执行远程连接
        if(!netconn.connected()){
            netconn.connect(red5url,154,"publicRoom",100,"feite","teacher");
            //指定连接client，方便red5调用当前客户端的方法
            // netconn.client(); = this;
            netconn.addEventListener(new INetConnection.IListener() {
                @Override
                public void onAsyncError(INetConnection iNetConnection, String s, Exception e) {

                }

                @Override
                public void onIOError(INetConnection iNetConnection, String s) {

                }

                @Override
                public void onSecurityError(INetConnection iNetConnection, String s) {

                }

                @Override
                public void onNetStatus(INetConnection iNetConnection, Map<String, Object> map) {
                    System.out.println(map.get("code").toString());
                    Log.e("连接结果：  ",map.get("code").toString());
                    switch (map.get("code").toString()) {
                        //

                        //连接成功
                        case "NetConnection.Connect.Success":
                            Log.e("连接成功，，", "");
                            // l_connStatus.text = "连接成功";
                            //调用服务器方法，获取在线用户列表
                            //getOnlineListInfo();
                            break;
                        //服务器断开
                        case "NetConnection.Connect.Closed":
                            // l_connStatus.text = "连接断开";
                            Log.e("服务器断开，，", "");
                            break;
                        //能连上，但被拒绝访问
                        case "NetConnection.Connect.Failed":
                            Log.e("连接失败，，", "");
                            break;
                        default:
                            break;
                    }


                }
            });
        }
    }

//    public void juv_client_he(){
//        RTMPConnectionUtil.ConnectRed5(TestChat.this);
//
//       RTMPConnectionUtil.netStream = new UltraNetStream(
//                RTMPConnectionUtil.connection);
//        RTMPConnectionUtil.netStream
//                .addEventListener(new NetStream.ListenerAdapter() {
//
//                    @Override
//                    public void onNetStatus(final INetStream source,
//                                            final Map<String, Object> info) {
//                        Log.d("DEBUG", "Publisher#NetStream#onNetStatus: "
//                                + info);
//
//                        final Object code = info.get("code");
//
//                    }
//                });
//
//        RTMPConnectionUtil.netStream.publish("1", NetStream.LIVE);
//        RTMPConnectionUtil.netStream.receiveVideo(true);
//    }
}
