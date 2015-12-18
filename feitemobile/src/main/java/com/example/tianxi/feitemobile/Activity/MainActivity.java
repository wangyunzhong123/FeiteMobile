package com.example.tianxi.feitemobile.Activity;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tianxi.feitemobile.Fragment.ClassFragment;
import com.example.tianxi.feitemobile.Fragment.CommuneFragment;
import com.example.tianxi.feitemobile.Fragment.MeFragment;
import com.example.tianxi.feitemobile.R;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    //顶部TextView提示
    private TextView textLogo;

    //底部三个tab布局文件的定义
    private RelativeLayout classLayout, communeLayout,meLayout;

    //底部标签切换的Fragment
    private Fragment classFragment,communeFragment,meFragment;

    //记录当前Fragment
    private Fragment currentFragment;

    //底部标签图片
    private ImageView classImag,communeImag,meImag;

    //底部标签的文字
    private TextView classText,communeText,meText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        initTab();


    }

    /*
    * 初始化UI组件
    * */
    private void initUI(){
        classLayout = (RelativeLayout) findViewById(R.id.class_tab);
        communeLayout = (RelativeLayout) findViewById(R.id.commune_tab);
        meLayout = (RelativeLayout) findViewById(R.id.me_tab);
        classLayout.setOnClickListener(this);
        communeLayout.setOnClickListener(this);
        meLayout.setOnClickListener(this);

        classImag = (ImageView) findViewById(R.id.class_tab_imag);
        communeImag = (ImageView) findViewById(R.id.commune_tab_imag);
        meImag = (ImageView) findViewById(R.id.me_tab_imag);
        classText = (TextView) findViewById(R.id.class_tab_text);
        communeText = (TextView) findViewById(R.id.commune_tab_text);
        meText = (TextView) findViewById(R.id.me_tab_text);

        textLogo = (TextView) findViewById(R.id.iv_logo);
    }

    /*
    * 初始化底部图标
    * */
    private void initTab(){
        if(classFragment == null){//classFragment作为起始默认显示的Fragment
            classFragment = ClassFragment.newInstance("","");
        }

        if(! classFragment.isAdded()){
            //提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout,classFragment).commit();

            // 记录当前Fragment
            currentFragment = classFragment;
            // 设置图片文本的变化
            classImag.setImageResource(R.drawable.btn_know_pre);
            classText.setTextColor(getResources()
                    .getColor(R.color.bottomtab_press));
            communeImag.setImageResource(R.drawable.btn_wantknow_nor);
            communeText.setTextColor(getResources().getColor(
                    R.color.bottomtab_normal));
            meImag.setImageResource(R.drawable.btn_my_nor);
            meText.setTextColor(getResources().getColor(R.color.bottomtab_normal));

            textLogo.setText(R.string.main_tab_1_text);

        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.class_tab: // 课程
                clickTab1Layout();
                break;
            case R.id.commune_tab: // 公社
                clickTab2Layout();
                break;
            case R.id.me_tab: // 我
                clickTab3Layout();
                break;
            default:
                break;
        }
    }

    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        if (classFragment == null) {
            classFragment = ClassFragment.newInstance("","");
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), classFragment);

        // 设置底部tab变化
        classImag.setImageResource(R.drawable.btn_know_pre);
        classText.setTextColor(getResources().getColor(R.color.bottomtab_press));
        communeImag.setImageResource(R.drawable.btn_wantknow_nor);
        communeText.setTextColor(getResources().getColor(
                R.color.bottomtab_normal));
        meImag.setImageResource(R.drawable.btn_my_nor);
        meText.setTextColor(getResources().getColor(R.color.bottomtab_normal));

        textLogo.setText(R.string.main_tab_1_text);
    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        if (communeFragment == null) {
            communeFragment = CommuneFragment.newInstance("","");
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), communeFragment);

        classImag.setImageResource(R.drawable.btn_know_nor);
        classText.setTextColor(getResources().getColor(R.color.bottomtab_normal));
        communeImag.setImageResource(R.drawable.btn_wantknow_pre);
        communeText.setTextColor(getResources().getColor(
                R.color.bottomtab_press));
        meImag.setImageResource(R.drawable.btn_my_nor);
        meText.setTextColor(getResources().getColor(R.color.bottomtab_normal));

        textLogo.setText(R.string.main_tab_2_text);

    }

    /**
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        if (meFragment == null) {
            meFragment = MeFragment.newInstance("","");
        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFragment);
        classImag.setImageResource(R.drawable.btn_know_nor);
        classText.setTextColor(getResources().getColor(R.color.bottomtab_normal));
        communeImag.setImageResource(R.drawable.btn_wantknow_nor);
        communeText.setTextColor(getResources().getColor(
                R.color.bottomtab_normal));
        meImag.setImageResource(R.drawable.btn_my_pre);
        meText.setTextColor(getResources().getColor(R.color.bottomtab_press));

        textLogo.setText(R.string.main_tab_3_text);

    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

//    @Override
//    public void onFragmentInteraction(Uri uri){
//
//    }
}
