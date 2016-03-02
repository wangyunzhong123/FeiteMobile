package com.example.tianxi.getphonenumber;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;

public class Hack006Activity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getListView().setLayoutAnimation(
//                new LayoutAnimationController(AnimationUtils.loadAnimation(
//                        this, R.anim.list_animation), 0.5f));

        //设置动画
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(50);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f
        ,Animation.RELATIVE_TO_SELF,-1.0f,Animation.RELATIVE_TO_SELF,0.0f);
        animation.setDuration(100);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(set,0.5f);

        getListView().setLayoutAnimation(controller);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Countries.COUNTRIES));
    }
}