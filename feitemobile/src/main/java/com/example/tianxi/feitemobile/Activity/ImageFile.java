package com.example.tianxi.feitemobile.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tianxi.feitemobile.Adapter.FolderAdapter;
import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.util.Bimp;
import com.example.tianxi.feitemobile.util.PublicWay;
import com.example.tianxi.feitemobile.util.Res;


/*
* 这个用来进行显示包含图片的文件夹
* */
public class ImageFile extends Activity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_file);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_image_file, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    private FolderAdapter folderAdapter;
    private Button bt_cancel;
    private Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Res.getLayoutID("plugin_camera_image_file"));
        PublicWay.activityList.add(this);
        mContext = this;
        bt_cancel = (Button) findViewById(Res.getWidgetID("cancel"));
        bt_cancel.setOnClickListener(new CancelListener());
        GridView gridView = (GridView) findViewById(Res.getWidgetID("fileGridView"));
        TextView textView = (TextView) findViewById(Res.getWidgetID("headerTitle"));
        textView.setText(Res.getString("photo"));
        folderAdapter = new FolderAdapter(this);
        gridView.setAdapter(folderAdapter);
    }

    private class CancelListener implements View.OnClickListener {// 取消按钮的监听
        public void onClick(View v) {
            //清空选择的图片
            Bimp.tempSelectBitmap.clear();
            Intent intent = new Intent();
            intent.setClass(mContext, MainActivity.class);
            startActivity(intent);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(mContext, MainActivity.class);
            startActivity(intent);
        }

        return true;
    }


}
