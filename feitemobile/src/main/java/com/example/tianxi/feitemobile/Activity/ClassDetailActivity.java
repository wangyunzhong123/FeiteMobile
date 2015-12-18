package com.example.tianxi.feitemobile.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tianxi.feitemobile.Adapter.ImageAndTextListAdapter;
import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.Tools.TempDB;

import org.json.JSONObject;

import java.util.List;

public class ClassDetailActivity extends Activity {

    private ListView listView;

    //课程头
    private LinearLayout class_detail_head_out;
    //下载按钮
    private TextView class_detail_loadbtn;
    //动作列表
    private List<JSONObject> jsonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        View titleLayout= this.findViewById(R.id.titleLayout);
        TextView textView =(TextView)titleLayout.findViewById(R.id.title_text);
        textView.setText("课程详情");

        initUI();

        initData();

    }

    private void initUI(){
        listView = (ListView)findViewById(R.id.class_detail_listview);
        class_detail_head_out = (LinearLayout)findViewById(R.id.class_detail_head_out);
        class_detail_loadbtn = (TextView)findViewById(R.id.class_detail_loadbtn);

        //点击课程介绍
        class_detail_head_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend = new Intent(ClassDetailActivity.this,ClassDetail_InfoActivity.class);
                startActivity(intend);
            }
        });
    }

    private void initData(){
        jsonList = TempDB.getJsonList_Class_ActionList();
        ImageAndTextListAdapter adapter = new ImageAndTextListAdapter(ClassDetailActivity.this,jsonList,R.layout.activity_class_detail_actionlist);
        listView.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_class_detail, menu);
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
