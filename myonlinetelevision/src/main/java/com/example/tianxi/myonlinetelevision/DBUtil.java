package com.example.tianxi.myonlinetelevision;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxi on 15-12-18.
 */
public class DBUtil {

    private static List<JSONObject> objectList;


    //直播电视rtmp
//    private static Map<>
    public static List<JSONObject> getObjectList(){

        objectList = new ArrayList<JSONObject>();

        Map<String,String> map = new HashMap<String,String>();
        map.put("香港卫视","rtmp://live.hkstv.hk.lxdns.com/live/hks");
        map.put("香港卫视精品台","rtmp://221.120.177.59/livestream/ucagm8kk");
        map.put("香港9DTV","mmsh://media.9dtv.com.hk:8088/9DTVBroadCas");
        map.put("酷六V音乐","http://main.gslb.ku6.com/broadcast/sub?channel=910");
        map.put("阳光卫视","http://wzfree.10043.doftp.com/tvtest/182tv.php/live/id/suntv.m3u8");
        map.put("健康生活台","http://wzfree.10043.doftp.com/tvtest/182tv.php/live/id/hlc.m3u8");
        map.put("凤凰卫视中文","rtsp://116.199.127.68/fenghuang");
        map.put("北京卫视","http://zb.v.qq.com:1863/?progid=1039787969");
        map.put("江苏卫视","http://zb.v.qq.com:1863/?progid=2674956498");
        map.put("东方卫视","http://zb.v.qq.com:1863/?progid=3900155972");
        map.put("浙江卫视","http://zb.v.qq.com:1863/?progid=1975434150");
        map.put("广东卫视","http://zb.v.qq.com:1863/?progid=857894899");
        map.put("深圳卫视","http://zb.v.qq.com:1863/?progid=2220552576");
        map.put("湖南卫视","http://zb.v.qq.com:1863/?progid=18091377");
        map.put("深圳卫视","http://zb.v.qq.com:1863/?progid=2220552576");
        map.put("中国之声","mms://mms.cnr.cn/cnr001");
        map.put("音乐之声","mms://mms.cnr.cn/cnr003");
        map.put("经济之声","mms://mms.cnr.cn/cnr002");
        map.put("韩国CR1台","rtmp://lm02.everyon.tv:1935/ptv2/phd501");
        map.put("韩国CR2台","rtmp://lm02.everyon.tv:1935/ptv/phd63");
        map.put("韩国CR3台","rtmp://lm02.everyon.tv:1935/ptv/phd62");
        map.put("欧美CR1","rtmp://live190.la3.origin.filmon.com:1935/live/198.high.stream");
        map.put("欧美CR2","rtmp://live190.la3.origin.filmon.com:1935/live/244.high.stream");
        map.put("欧美CR3","rtmp://live190.la3.origin.filmon.com:1935/live/245.high.stream");
        map.put("奇奇影院","rtmp://lxrtmp.load.cdn.zhanqi.tv/zqlive/24472_k75qU");
        map.put("斯文影院","rtmp://121.17.123.227/dlrtmp.cdn.zhanqi.tv/zqlive/27913_q06c4");
        map.put("老郭影院","rtmp://121.17.123.227/dlrtmp.cdn.zhanqi.tv/zqlive/13393_Raq4m");
        map.put("幂幂影院","rtmp://121.17.123.229/dlrtmp.cdn.zhanqi.tv/zqlive/20910_uUMgC");
        map.put("啪啪影院","rtmp://121.17.123.2/dlrtmp.cdn.zhanqi.tv/zqlive/29645_h6yyx");
        map.put("白腿影院","rtmp://lxrtmp.load.cdn.zhanqi.tv/zqlive/20143_JY9Z3");
        map.put("最牛老师","rtmp://121.17.123.227/dlrtmp.cdn.zhanqi.tv/zqlive/24720_2Kbm1");
        map.put("伟哥影院","rtmp://121.17.123.2/dlrtmp.cdn.zhanqi.tv/zqlive/7032_0s2qn");
        map.put("福利影院","rtmp://121.17.123.2/dlrtmp.cdn.zhanqi.tv/zqlive/30537_wRnG6");

        try{
            Iterator it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry)it.next();
                JSONObject object = new JSONObject();
                object.put("name",entry.getKey().toString());
                object.put("url",entry.getValue().toString());
                objectList.add(object);
            }
        }catch (JSONException e){

        }
        return objectList;
    }
}
