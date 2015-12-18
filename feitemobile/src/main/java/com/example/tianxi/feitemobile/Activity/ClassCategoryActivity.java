package com.example.tianxi.feitemobile.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianxi.feitemobile.Adapter.ImageAndTextListAdapter;
import com.example.tianxi.feitemobile.Myview.MyListView;
import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.Tools.HttpSender;
import com.example.tianxi.feitemobile.Tools.TempDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClassCategoryActivity extends Activity {


    private TextView textView;//显示一类类别的
    private List<JSONObject> classlist;
    private MyListView listView;

//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg)
//        {
//            if(0 == msg.what){
//                Log.e("type==1请求得到的内容  ",msg.obj.toString());
//                handler_process(msg.obj.toString());
//            }
//        }
//    };
//
//    public void handler_process(String string){
//        JSONObject object= null;
//        JSONArray objectArray = null;
//        try {
//            object = new JSONObject( string);
//            objectArray = new JSONArray(object.getString("nodes"));
//        } catch (JSONException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        if(null != objectArray){
//            for(int i=0;i<objectArray.length();i++){
//                JSONObject cate = new JSONObject();
//                JSONObject temp = null;
//                try {
//                    temp = new JSONObject( (new JSONObject(objectArray.get(i).toString())).getString("node"));
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                try {
//                    Log.i("title",temp.getString("title"));
//                    Log.i("nothing_1",temp.getString("nothing_1"));
//                    cate.put("cateId", i);
//                    cate.put("title", temp.getString("title"));
//                    cate.put("url", new JSONObject(temp.getString("field_p_picture")).getString("src"));
//                    classlist.add(cate);
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//            }
//            initUI();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_category);
        initData();
        initUI();
    }


    public void initData() {
        // TODO Auto-generated method stub
        Intent intent = getIntent();
        View titleLayout= this.findViewById(R.id.titleLayout);
        textView =(TextView)titleLayout.findViewById(R.id.title_text);
        textView.setText(intent.getStringExtra("name"));

        classlist = new ArrayList<JSONObject>();
        Log.i("传递的url", intent.getStringExtra("link_url"));
//        HttpSender httpSender = new HttpSender(intent.getStringExtra("link_url"),"GET", handler);
//        httpSender.send();

        classlist = TempDB.getJsonList_Class_Category_List();

    }
    public void initUI() {
        // TODO Auto-generated method stub
        listView = (MyListView)findViewById(R.id.ListCategoryClass);
        final ImageAndTextListAdapter adapter = new ImageAndTextListAdapter(ClassCategoryActivity.this,classlist,R.layout.category_main_listview);
        listView.setAdapter(adapter);
        //设置下拉刷新事件
        listView.setonRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                {
                    new AsyncTask<Void, Void, Void>() {
                        protected Void doInBackground(Void... params) {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            JSONObject object = new JSONObject();
                            try{
                                object.put("id",1);
                                object.put("imagUrl","http://img1.imgtn.bdimg.com/it/u=1708563771,3440055422&fm=21&gp=0.jpg");
                                object.put("info","");
                                object.put("link_url","");
                                object.put("name","太极拳1");

                                classlist.add(object);
                            }catch (JSONException e){

                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();
                        }
                    }.execute(null, null, null);
                }

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(ClassCategoryActivity.this, "点击进入具体课程页面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ClassCategoryActivity.this, ClassDetailActivity.class);
//                try {
//                    intent.putExtra("classId", classlist.get(position).getInt("cateId"));
//                    intent.putExtra("title", classlist.get(position).getString("title"));
//                    intent.putExtra("url", classlist.get(position).getString("url"));
//                    //获得下一个对课程的详细请求的url
//                    intent.putExtra("class_url", "");
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_class_category, menu);
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
