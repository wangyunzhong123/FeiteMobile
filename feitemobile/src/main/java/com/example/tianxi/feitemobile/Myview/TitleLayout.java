package com.example.tianxi.feitemobile.Myview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.tianxi.feitemobile.R;

/**
 * Created by tianxi on 15-12-9.
 */
public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        LayoutInflater.from(context).inflate(R.layout.title, this);
        Button back = (Button)findViewById(R.id.title_back);
        back.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ((Activity)getContext()).finish();
            }

        });

    }
    //public void setTitle

}
