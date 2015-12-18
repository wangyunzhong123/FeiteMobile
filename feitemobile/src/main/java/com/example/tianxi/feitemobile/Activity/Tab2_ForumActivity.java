package com.example.tianxi.feitemobile.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.tianxi.feitemobile.Myview.MyListView;
import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.Tools.TempDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Tab2_ForumActivity extends Activity {


    private MyListView myListView;
    private List<Map<String,Object>> listData;

    //界面最上方的View，作为listView的header
    private View localView;

    private View tab2_forum_createTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2__forum);

        initUI();

        initData();
    }

    private void initUI(){
        myListView = (MyListView)findViewById(R.id.tab2_forum_listVIew);
        localView = LayoutInflater.from(this).inflate(R.layout.tab2_forum_header,null);
        tab2_forum_createTopic= (TextView)findViewById(R.id.tab2_forum_createTopic);

    }

    private void initData(){

        listData = TempDB.getHot_topic_listItem();
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listData,R.layout.tab2_hot_topic_listview_item,
                new String[]{"personName","header","desc"},
                new int[]{R.id.name,R.id.header,R.id.desc});

        myListView.addHeaderView(localView);
        myListView.setAdapter(simpleAdapter);
        //设置下拉刷新事件
        myListView.setonRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                    }
                }.execute(null, null, null);
            }
        });


        tab2_forum_createTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tab2_ForumActivity.this,Tab2CreateTopActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab2__forum, menu);
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
