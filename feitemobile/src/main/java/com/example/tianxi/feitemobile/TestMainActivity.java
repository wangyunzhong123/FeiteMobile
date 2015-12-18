package com.example.tianxi.feitemobile;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class TestMainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private String path;
    //private HashMap<String, String> options;
    private VideoView mVideoView;

    private ListView listviewChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.testactivity_main);

        View titleLayout= this.findViewById(R.id.titleLayout);
        TextView textView =(TextView)titleLayout.findViewById(R.id.title_text);
        textView.setText("直播间");


        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
        //path = "rtmp://www.ikktong.com/live/1";rtmp://live.hkstv.hk.lxdns.com/live/hks
        path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
            /*options = new HashMap<>();
            options.put("rtmp_playpath", "");
            options.put("rtmp_swfurl", "");
            options.put("rtmp_live", "1");
            options.put("rtmp_pageurl", "");*/
        mVideoView.setVideoPath(path);
        //mVideoView.setVideoURI(Uri.parse(path), options);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

        initChat();
    }

    public void initChat(){
        //listviewChat = (ListView)findViewById(R.id.listview_chat);

    }
}