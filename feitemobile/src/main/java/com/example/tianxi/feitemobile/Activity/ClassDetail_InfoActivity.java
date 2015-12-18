package com.example.tianxi.feitemobile.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tianxi.feitemobile.Application.MyApplication;
import com.example.tianxi.feitemobile.R;

public class ClassDetail_InfoActivity extends Activity {

    //头像
    private ImageView class_detail_info_whoImag;
    //姓名介绍
    private  TextView class_detail_info_whoName;
    //个人信息介绍
    private TextView class_detail_info_whoinfo;
    //图片介绍
    private ImageView class_detail_imag1,class_detail_imag2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail__info);

        View titleLayout= this.findViewById(R.id.titleLayout);
        TextView textView =(TextView)titleLayout.findViewById(R.id.title_text);
        textView.setText("课程详情");

        initUI();

        initData();

    }

    private void initUI(){
        class_detail_info_whoImag = (ImageView)findViewById(R.id.class_detail_info_whoImag);
        class_detail_info_whoName = (TextView)findViewById(R.id.class_detail_info_whoName);
        class_detail_info_whoinfo = (TextView)findViewById(R.id.class_detail_info_whoinfo);
        class_detail_imag1 = (ImageView)findViewById(R.id.class_detail_imag1);
        class_detail_imag2 = (ImageView)findViewById(R.id.class_detail_imag2);
    }

    private void initData(){
        MyApplication.mImageLoader.displayImage("http://h.hiphotos.baidu.com/baike/s%3D220/sign=e13b0c0cad6eddc422e7b3f909d9b6a2/b8014a90f603738d46b82ecbb31bb051f919ec6d.jpg",class_detail_imag1,MyApplication.options);
        MyApplication.mImageLoader.displayImage("http://f.hiphotos.baidu.com/baike/s%3D220/sign=8ae1957cc8fcc3ceb0c0ce31a244d6b7/79f0f736afc37931c7a49f77ebc4b74542a9119b.jpg",class_detail_imag2,MyApplication.options);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_class_detail__info, menu);
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
