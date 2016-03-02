package com.example.tianxi.getphonenumber;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.Log;

public class Hack007_01Activity extends PreferenceActivity implements
        Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener {
    private static String TAG = "HelloPreference";
    private CheckBoxPreference mapply_wifiPreference;       //打开wifi
    private CheckBoxPreference mapply_internetPreference;   //Internet共享
    private ListPreference depart_valuePreference;          //部门设置
    private EditTextPreference number_editPreference;       //输入电话号码
    private Preference mwifi_settingPreference;             //wifi设置
    private String oldDeptId; // 旧部门的名称

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mypreference);
        //根据key值找到控件
        mapply_wifiPreference = (CheckBoxPreference) findPreference("apply_wifi");
        mapply_internetPreference = (CheckBoxPreference) findPreference("apply_internet");
        depart_valuePreference = (ListPreference) findPreference("depart_value");
        number_editPreference = (EditTextPreference) findPreference("number_edit");
        mwifi_settingPreference = (Preference) findPreference("wifi_setting");

        // 设置监听器
        mapply_internetPreference.setOnPreferenceClickListener(this);
        mapply_internetPreference.setOnPreferenceChangeListener(this);
        depart_valuePreference.setOnPreferenceClickListener(this);
        depart_valuePreference.setOnPreferenceChangeListener(this);
        number_editPreference.setOnPreferenceClickListener(this);
        number_editPreference.setOnPreferenceChangeListener(this);
        mwifi_settingPreference.setOnPreferenceClickListener(this);

        // 得到我们的存储Preferences值的对象，然后对其进行相应操作
        SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean apply_wifiChecked = shp.getBoolean("apply_wifi", false);
    }

    // 对控件进行的一些操作
    private void operatePreference(Preference preference) {
        Log.i(TAG, "operatePreference");

        if (preference == mapply_wifiPreference){                  //点击了    "打开wifi"
            Log.i(TAG, " Wifi CB, and isCheckd ="+ mapply_wifiPreference.isChecked());
        }else if (preference.getKey().equals("apply_internet")){   //点击了"Internet共享"
            Log.i(TAG, " internet CB, and isCheckd = "+mapply_internetPreference.isChecked());
        }else if (preference == depart_valuePreference){           //点击了 "部门设置"
            Log.i(TAG, " department CB,and selectValue = "+ depart_valuePreference.getValue() + ", Text="+ depart_valuePreference.getEntry());
        }else if (preference.getKey().equals("wifi_setting")) {    //点击了"wifi设置"
            mwifi_settingPreference.setTitle("its turn me.");
        }else if (preference == number_editPreference)             //点击了"输入电话号码"
            Log.i(TAG, "Old Value="+ number_editPreference.getText() + ", New Value="+ number_editPreference.getEditText().toString());
    }
    // 点击事件触发
    @Override
    public boolean onPreferenceClick(Preference preference) {
        // TODO Auto-generated method stub
        Log.i(TAG, "onPreferenceClick----->"+String.valueOf(preference.getKey()));
        // 对控件进行操作
        operatePreference(preference);
        return false;//表示要继续调用onPreferenceTreeClick方法，返回true则不再调用onPreferenceTreeClick方法。
    }
    //点击事件触发
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                                         Preference preference) {
        Log.i(TAG, "onPreferenceTreeClick----->"+preference.getKey());
        // 对控件进行操作
        operatePreference(preference);
        if (preference.getKey().equals("wifi_setting")) {
            // 创建一个新的Intent，
            // 函数如果返回true， 则跳转至该自定义的新的Intent ;
            // 函数如果返回false，则跳转至xml文件中配置的Intent ;
            Intent i = new Intent(Hack007_01Activity.this, Hack007Activity.class);  //OtherActivity只是一个简单的Activity
            i.putExtra("type", "wifi");
            startActivity(i);
            return true;//代表点击事件已成功捕捉，无须执行默认动作或返回上层调用链。 例如， 不跳转至默认Intent。
        }
        return false;//false 代表执行默认动作并且返回上层调用链。例如，跳转至默认Intent。
    }

    // 当Preference的值发生改变时触发该事件，true则以新值更新控件的状态，false则do noting
    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {
        Log.i(TAG, "onPreferenceChange----->"+String.valueOf(preference.getKey()));
        if (preference == mapply_wifiPreference){
            Log.i(TAG, "Wifi CB, and isCheckd = " + String.valueOf(objValue));
        }else if (preference.getKey().equals("apply_internet")) {
            Log.i(TAG, "internet CB, and isCheckd = "+ String.valueOf(objValue));
            //return false;  //不保存该新值
        }else if (preference == depart_valuePreference){
            Log.i(TAG, "  Old Value"+ depart_valuePreference.getValue()+" NewDeptName"+objValue);
        }else if (preference.getKey().equals("wifi_setting")) {
            Log.i(TAG, "change" + String.valueOf(objValue));
            mwifi_settingPreference.setTitle("its turn me.");  //重新设置title
        } else if (preference == number_editPreference) {
            Log.i(TAG, "Old Value = " + String.valueOf(objValue));
            //return false; // 不保存更新值
        }
        return true;  //保存更新后的值
    }
}
