package com.example.tianxi.feitemobile.Tools;


import com.example.tianxi.feitemobile.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tianxi on 15-12-8.
 *
 * 测试在没有后台支持的情况下的测试
 */
public class TempDB {

    //gridlist的测试数据
    private static List<JSONObject> jsonList_Class_AllClass_GridList;

    //课程类别请求得到的数据，，模拟
    private  static List<JSONObject> jsonList_Class_Category_List;

    //动作列表请求数据模拟
    private  static List<JSONObject> jsonList_Class_ActionList;

    //热门话题listview的测试数据
    private static List<Map<String,Object>> hot_topic_listItem;

    public static List<JSONObject> getJsonList_Class_AllClass_GridList(){

        jsonList_Class_AllClass_GridList = new ArrayList<JSONObject>();
        JSONObject object = new JSONObject();
        try{
            object.put("id",1);
            object.put("imagUrl","http://pic36.nipic.com/20131207/6357173_200917848000_2.jpg");
            object.put("info","");
            object.put("link_url","");
            object.put("name","太极拳1");

            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);
            jsonList_Class_AllClass_GridList.add(object);


        }catch (JSONException e){

        }

        return jsonList_Class_AllClass_GridList;
    }

    public static List<JSONObject> getJsonList_Class_Category_List(){
        jsonList_Class_Category_List = new ArrayList<JSONObject>();
        JSONObject object = new JSONObject();
        try{
            object.put("id",1);
            object.put("imagUrl","http://pic36.nipic.com/20131207/6357173_200917848000_2.jpg");
            object.put("info","");
            object.put("link_url","");
            object.put("name","太极拳1");

            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);
            jsonList_Class_Category_List.add(object);


        }catch (JSONException e){

        }

        return jsonList_Class_Category_List;
    }

    public static List<JSONObject> getJsonList_Class_ActionList(){
        jsonList_Class_ActionList = new ArrayList<JSONObject>();
        try{
            JSONObject object = new JSONObject();
            object.put("imagUrl","http://a.hiphotos.baidu.com/image/h%3D200/sign=d68bcb59d609b3def4bfe368fcbe6cd3/d1160924ab18972b54c26cfbe1cd7b899e510a02.jpg");
            object.put("id","");
            object.put("name","动作dfgdfdgdf第三方1111111");
            object.put("loadUrl","");

            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);
            jsonList_Class_ActionList.add(object);

        }catch (JSONException e){

        }
        return jsonList_Class_ActionList;
    }

    public static List<Map<String,Object>> getHot_topic_listItem(){
        hot_topic_listItem = new ArrayList<Map<String,Object>>();


        Map<String,Object> listItem = new HashMap<String,Object>();
        listItem.put("header", R.drawable.btn_my_pre);
        listItem.put("personName","郑多燕");
        listItem.put("desc","风一样的女子");

        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);
        hot_topic_listItem.add(listItem);



        return hot_topic_listItem;
    }
}
