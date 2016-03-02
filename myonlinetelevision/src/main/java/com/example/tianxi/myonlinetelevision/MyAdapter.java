package com.example.tianxi.myonlinetelevision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by tianxi on 15-12-18.
 */
public class MyAdapter extends BaseAdapter {

    private List<JSONObject> list;

    private Context context;

    private LayoutInflater inflater=null;

    public MyAdapter(Context context,List<JSONObject> objectList){
        this.context = context;
        this.list = objectList;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub

        return arg0;
    }

    @Override
    public View getView(int arg0, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_main_list_layout,null);
        TextView textView = (TextView)convertView.findViewById(R.id.activity_main_list_text);
        String str = null;
        try {
            str = list.get(arg0).getString("name");
        }catch (JSONException e){

        }
        textView.setText(str);
        return convertView;
    }
}
