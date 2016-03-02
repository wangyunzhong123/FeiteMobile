package com.example.tianxi.myonlinetelevision;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class VideoActivity extends Activity {


    private String path;
    //private HashMap<String, String> options;
    private VideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video);

//        View titleLayout= this.findViewById(R.id.titleLayout);
//        TextView textView =(TextView)titleLayout.findViewById(R.id.title_text);
//        textView.setText("直播间");


        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
        //path = "rtmp://www.ikktong.com/live/1";rtmp://live.hkstv.hk.lxdns.com/live/hks
        //path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
        path = getIntent().getStringExtra("url");
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
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
}
