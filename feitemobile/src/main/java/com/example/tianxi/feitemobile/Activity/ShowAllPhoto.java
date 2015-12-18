package com.example.tianxi.feitemobile.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tianxi.feitemobile.Adapter.AlbumGridViewAdapter;
import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.util.Bimp;
import com.example.tianxi.feitemobile.util.ImageItem;
import com.example.tianxi.feitemobile.util.PublicWay;
import com.example.tianxi.feitemobile.util.Res;

import java.util.ArrayList;


/*
* 这个是显示一个文件夹里面的所有图片时的界面
* */
public class ShowAllPhoto extends Activity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_all_photo);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_show_all_photo, menu);
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

    private GridView gridView;
    private ProgressBar progressBar;
    private AlbumGridViewAdapter gridImageAdapter;
    // 完成按钮
    private Button okButton;
    // 预览按钮
    private Button preview;
    // 返回按钮
    private Button back;
    // 取消按钮
    private Button cancel;
    // 标题
    private TextView headTitle;
    private Intent intent;
    private Context mContext;
    public static ArrayList<ImageItem> dataList = new ArrayList<ImageItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Res.getLayoutID("plugin_camera_show_all_photo"));
        PublicWay.activityList.add(this);
        mContext = this;
        back = (Button) findViewById(Res.getWidgetID("showallphoto_back"));
        cancel = (Button) findViewById(Res.getWidgetID("showallphoto_cancel"));
        preview = (Button) findViewById(Res.getWidgetID("showallphoto_preview"));
        okButton = (Button) findViewById(Res.getWidgetID("showallphoto_ok_button"));
        headTitle = (TextView) findViewById(Res.getWidgetID("showallphoto_headtitle"));
        this.intent = getIntent();
        String folderName = intent.getStringExtra("folderName");
        if (folderName.length() > 8) {
            folderName = folderName.substring(0, 9) + "...";
        }
        headTitle.setText(folderName);
        cancel.setOnClickListener(new CancelListener());
        back.setOnClickListener(new BackListener(intent));
        preview.setOnClickListener(new PreviewListener());
        init();
        initListener();
        isShowOkBt();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            gridImageAdapter.notifyDataSetChanged();
        }
    };

    private class PreviewListener implements View.OnClickListener {
        public void onClick(View v) {
            if (Bimp.tempSelectBitmap.size() > 0) {
                intent.putExtra("position", "2");
                intent.setClass(ShowAllPhoto.this, GalleryActivity.class);
                startActivity(intent);
            }
        }

    }

    private class BackListener implements View.OnClickListener {// 返回按钮监听
        Intent intent;

        public BackListener(Intent intent) {
            this.intent = intent;
        }

        public void onClick(View v) {
            intent.setClass(ShowAllPhoto.this, ImageFile.class);
            startActivity(intent);
        }

    }

    private class CancelListener implements View.OnClickListener {// 取消按钮的监听
        public void onClick(View v) {
            //清空选择的图片
            Bimp.tempSelectBitmap.clear();
            intent.setClass(mContext, MainActivity.class);
            startActivity(intent);
        }
    }

    private void init() {
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        registerReceiver(broadcastReceiver, filter);
        progressBar = (ProgressBar) findViewById(Res.getWidgetID("showallphoto_progressbar"));
        progressBar.setVisibility(View.GONE);
        gridView = (GridView) findViewById(Res.getWidgetID("showallphoto_myGrid"));
        gridImageAdapter = new AlbumGridViewAdapter(this,dataList,
                Bimp.tempSelectBitmap);
        gridView.setAdapter(gridImageAdapter);
        okButton = (Button) findViewById(Res.getWidgetID("showallphoto_ok_button"));
    }

    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
                    public void onItemClick(final ToggleButton toggleButton,
                                            int position, boolean isChecked,
                                            Button button) {
                        if (Bimp.tempSelectBitmap.size() >= PublicWay.num&&isChecked) {
                            button.setVisibility(View.GONE);
                            toggleButton.setChecked(false);
                            Toast.makeText(ShowAllPhoto.this, Res.getString("only_choose_num"), Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        if (isChecked) {
                            button.setVisibility(View.VISIBLE);
                            Bimp.tempSelectBitmap.add(dataList.get(position));
                            okButton.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size()
                                    + "/"+PublicWay.num+")");
                        } else {
                            button.setVisibility(View.GONE);
                            Bimp.tempSelectBitmap.remove(dataList.get(position));
                            okButton.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
                        }
                        isShowOkBt();
                    }
                });

        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                okButton.setClickable(false);
//				if (PublicWay.photoService != null) {
//					PublicWay.selectedDataList.addAll(Bimp.tempSelectBitmap);
//					Bimp.tempSelectBitmap.clear();
//					PublicWay.photoService.onActivityResult(0, -2,
//							intent);
//				}
                intent.setClass(mContext, MainActivity.class);
                startActivity(intent);
                // Intent intent = new Intent();
                // Bundle bundle = new Bundle();
                // bundle.putStringArrayList("selectedDataList",
                // selectedDataList);
                // intent.putExtras(bundle);
                // intent.setClass(ShowAllPhoto.this, UploadPhoto.class);
                // startActivity(intent);
                finish();

            }
        });

    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            okButton.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
            preview.setPressed(true);
            okButton.setPressed(true);
            preview.setClickable(true);
            okButton.setClickable(true);
            okButton.setTextColor(Color.WHITE);
            preview.setTextColor(Color.WHITE);
        } else {
            okButton.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+ PublicWay.num+")");
            preview.setPressed(false);
            preview.setClickable(false);
            okButton.setPressed(false);
            okButton.setClickable(false);
            okButton.setTextColor(Color.parseColor("#E1E0DE"));
            preview.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            intent.setClass(ShowAllPhoto.this, ImageFile.class);
            startActivity(intent);
        }

        return false;

    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        isShowOkBt();
        super.onRestart();
    }

}
