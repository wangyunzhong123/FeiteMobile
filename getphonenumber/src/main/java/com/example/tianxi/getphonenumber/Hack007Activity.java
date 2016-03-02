package com.example.tianxi.getphonenumber;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

public class Hack007Activity extends Activity {

    private DrawView mDrawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        mDrawView = new DrawView(this);
        mDrawView.height = display.getHeight();
        mDrawView.width = display.getWidth();

        setContentView(mDrawView);
    }
}