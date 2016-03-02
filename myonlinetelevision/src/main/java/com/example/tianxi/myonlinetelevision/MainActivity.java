package com.example.tianxi.myonlinetelevision;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private List<JSONObject> objectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        initUI();

        return true;
    }

    private void initUI(){
        listView = (ListView)findViewById(R.id.listView);
        objectList = DBUtil.getObjectList();
        MyAdapter adapter = new MyAdapter(MainActivity.this,objectList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                String name = null;
                String url = null;
                try {
                    name = objectList.get(position).getString("name");
                    url = objectList.get(position).getString("url");
                }catch (JSONException e){

                }
                intent.putExtra("name", name);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

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
