package com.example.tianxi.feitemobile.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.Tools.ImageAndText;
import com.example.tianxi.feitemobile.Tools.LogUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianxi on 15-12-8.
 */
public class ImageAndTextListAdapter extends BaseAdapter {

    private LayoutInflater inflater=null;
    private int whichLayout;
    private List<JSONObject> list;
    private List<Long> downfileidlist;//记录正在下载的文件的id

    private ListView listView;
    public void setListView(ListView view)
    {
        this.listView = view;
    }


    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;


    public ImageAndTextListAdapter(Context context,String[] urls){

    }
    public ImageAndTextListAdapter(Context context,List<JSONObject> list,int layout){
        this.list = list;
        this.whichLayout = layout;
        inflater = LayoutInflater.from(context);

        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory().cacheOnDisc()/////////.cacheInMemory(ture).cacheOnDisc(ture)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();

        LogUtil.i("ImageAndTextListAdapter收到的list是，，，", list.toString());
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
        // TODO Auto-generated method stub

        /*
        *首页所有课程分类的处理
        **whichLayout == R.layout.fragment_class_all_class_gridview
        */
        //to_handler1(arg0,convertView);


        /*
        *分类下所有课程的处理
        * whichLayout == R.layout.category_main_listview
        * */
        //to_handler1(arg0,convertView);


        /*
        **每个课程下的动作list的处理
        * whichLayout == R.layout.activity_class_detail_actionlist*/
       // to_handler2(arg0,convertView);


        if(whichLayout == R.layout.fragment_class_all_class_gridview || whichLayout == R.layout.category_main_listview){
            return to_handler1(arg0,convertView);
        }else if(whichLayout == R.layout.activity_class_detail_actionlist){
            return to_handler2(arg0, convertView);
        }else
        ;

        return convertView;
    }


    private View to_handler1(int arg0,View convertView){
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(whichLayout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            holder.textView = null;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        String url = null;
        try{
            url = list.get(getCount()-1-arg0).getString("imagUrl");//每次把最新的加载在最上面
        }catch (JSONException e){
            LogUtil.d("JSONException错误。",e.toString());
        }

        mImageLoader.displayImage(url, holder.imageView,
                options);
        return convertView;
    }

    private View to_handler2(int arg0,View convertView){
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(whichLayout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.action_list_image);
            holder.textView = (TextView)convertView.findViewById(R.id.action_list_text1);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        String url = null;
        String text = "1243245235";
        try{
            url = list.get(arg0).getString("imagUrl");//
            text = list.get(arg0).getString("name");
            LogUtil.d("imagUrl+name,,,",url+"  "+text);

        }catch (JSONException e){
            LogUtil.d("JSONException错误。",e.toString());
        }

        mImageLoader.displayImage(url, holder.imageView,
                options);
        holder.textView.setText(text);

        return convertView;
    }

    private class ViewHolder{

        public ImageView imageView;
        public TextView textView;

    }































}
