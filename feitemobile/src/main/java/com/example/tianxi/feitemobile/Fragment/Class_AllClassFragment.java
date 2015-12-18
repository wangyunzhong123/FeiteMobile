package com.example.tianxi.feitemobile.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianxi.feitemobile.Activity.ClassCategoryActivity;
import com.example.tianxi.feitemobile.Activity.ClassDetailActivity;
import com.example.tianxi.feitemobile.Activity.ClassDetail_InfoActivity;
import com.example.tianxi.feitemobile.Activity.MainActivity;
import com.example.tianxi.feitemobile.Adapter.ImageAndTextListAdapter;
import com.example.tianxi.feitemobile.Application.MyApplication;
import com.example.tianxi.feitemobile.Myview.MyListView;
import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.Tools.AdDomain;
import com.example.tianxi.feitemobile.Tools.LogUtil;
import com.example.tianxi.feitemobile.Tools.TempDB;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Class_AllClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Class_AllClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Class_AllClassFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Class_AllClassFragment.
     */
    // TODO: Rename and change types and number of parameters





    private ViewPager adViewPager;
    private List<ImageView> imageViews;// 滑动的图片集合

    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;

    private TextView tv_date;
    private TextView tv_title;
    private TextView tv_topic_from;
    private TextView tv_topic;
    private int currentItem = 0; // 当前图片的索引号
    // 定义的五个指示点
    private View dot0;
    private View dot1;
    private View dot2;
    private View dot3;
    private View dot4;

    private ScheduledExecutorService scheduledExecutorService;

    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;

    // 轮播banner的数据
    private List<AdDomain> adList;


    //GridView
    private MyListView gridView;
    private List<JSONObject> jsonList;
    //
    View view;

    private LayoutInflater layoutInflater;

    //banner的view
    private View localView;
    private static Class_AllClassFragment class_allClassfragment;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adViewPager.setCurrentItem(currentItem);
        };
    };



    public static Class_AllClassFragment newInstance(String param1, String param2) {
        if(class_allClassfragment == null){
            class_allClassfragment = new Class_AllClassFragment();
            return class_allClassfragment;
        }
        return class_allClassfragment;
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_class__all_class, container, false);
        this.layoutInflater = inflater;

        // 使用ImageLoader之前初始化
        initImageLoader();//不用，，已经在MyApplication中初始化了

        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory().cacheOnDisc()/////////.cacheInMemory(ture).cacheOnDisc(ture)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();

        initAdData();

        startAd();

        initGridView();

        return view;
    }


    private void initImageLoader() {

    }

    private void initAdData() {
        // 广告数据
        adList = getBannerAd();

        imageViews = new ArrayList<ImageView>();

        // 点

        localView = LayoutInflater.from(getActivity()).inflate(R.layout.banner,null);

        dots = new ArrayList<View>();
        dotList = new ArrayList<View>();
        dot0 = localView.findViewById(R.id.v_dot0);
        dot1 = localView.findViewById(R.id.v_dot1);
        dot2 = localView.findViewById(R.id.v_dot2);
        dot3 = localView.findViewById(R.id.v_dot3);
        dot4 = localView.findViewById(R.id.v_dot4);
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);



        tv_date = (TextView) localView.findViewById(R.id.tv_date);
        tv_title = (TextView) localView.findViewById(R.id.tv_title);
        tv_topic_from = (TextView) localView.findViewById(R.id.tv_topic_from);
        tv_topic = (TextView) localView.findViewById(R.id.tv_topic);

        adViewPager = (ViewPager) localView.findViewById(R.id.vp);
//        adViewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
//        // 设置一个监听器，当ViewPager中的页面改变时调用
//        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
//        addDynamicView();
    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(MyApplication.getContext());
            // 异步加载图片
            mImageLoader.displayImage(adList.get(i).getImgUrl(), imageView,
                    options);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 2, 3,
                TimeUnit.SECONDS);
    }

    /*
    设置GridView

    * */
    private void initGridView(){
        gridView = (MyListView)view.findViewById(R.id.gridCategory);
        jsonList = TempDB.getJsonList_Class_AllClass_GridList();
        final ImageAndTextListAdapter adapter = new ImageAndTextListAdapter(getActivity(),jsonList,R.layout.fragment_class_all_class_gridview);

        gridView.addHeaderView(localView);
        gridView.setAdapter(adapter);

        //设置下拉刷新事件
        gridView.setonRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        JSONObject object = new JSONObject();
                        try{
                            object.put("id",1);
                            object.put("imagUrl","http://img1.imgtn.bdimg.com/it/u=1708563771,3440055422&fm=21&gp=0.jpg");
                            object.put("info","");
                            object.put("link_url","");
                            object.put("name","太极拳1");

                            jsonList.add(object);
                        }catch (JSONException e){

                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        gridView.onRefreshComplete();
                    }
                }.execute(null, null, null);
            }
        });

        adViewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
        //无效
//        adViewPager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"点击了adViewPager",Toast.LENGTH_LONG).show();
//            }
//        });
        addDynamicView();

        mImageLoader = ImageLoader.getInstance();
        gridView.setOnScrollListener(new PauseOnScrollListener(mImageLoader,true,true));

        //点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Toast.makeText(getActivity(), jsonList.get(position).getString("name"), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(),ClassCategoryActivity.class);
                    intent.putExtra("name", jsonList.get(position).getString("name"));
                    intent.putExtra("link_url", jsonList.get(position).getString("imagUrl"));
                    startActivity(intent);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            AdDomain adDomain = adList.get(position);
            tv_title.setText(adDomain.getTitle()); // 设置标题
            tv_date.setText(adDomain.getDate());
            tv_topic_from.setText(adDomain.getTopicFrom());
            tv_topic.setText(adDomain.getTopic());
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return adList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv = imageViews.get(position);
            ((ViewPager) container).addView(iv);
            final AdDomain adDomain = adList.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
                    Toast.makeText(getActivity(),"点击了第 "+position+"图片 id= "+adDomain.getId()+" 时间是 "+adDomain.getDate(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), ClassDetailActivity.class);
                    startActivity(intent);


                }
            });
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    /**
     * 轮播广播模拟数据
     *
     * @return
     */
    public static List<AdDomain> getBannerAd() {
        List<AdDomain> adList = new ArrayList<AdDomain>();

        AdDomain adDomain = new AdDomain();
        adDomain.setId("108078");
        adDomain.setDate("3月4日");
        adDomain.setTitle("我和令计划只是同姓");
        adDomain.setTopicFrom("阿宅");
        adDomain.setTopic("我想知道令狐安和令计划有什么关系？");
        adDomain.setImgUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
        adDomain.setAd(false);
        adList.add(adDomain);

        AdDomain adDomain2 = new AdDomain();
        adDomain2.setId("108078");
        adDomain2.setDate("3月5日");
        adDomain2.setTitle("我和令计划只是同姓");
        adDomain2.setTopicFrom("小巫");
        adDomain2.setTopic("“我想知道令狐安和令计划有什么关系？”");
        adDomain2
                .setImgUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=7cbcd7da78f40ad115e4c1e2672e1151/eaf81a4c510fd9f9a1edb58b262dd42a2934a45e.jpg");
        adDomain2.setAd(false);
        adList.add(adDomain2);

        AdDomain adDomain3 = new AdDomain();
        adDomain3.setId("108078");
        adDomain3.setDate("3月6日");
        adDomain3.setTitle("我和令计划只是同姓");
        adDomain3.setTopicFrom("旭东");
        adDomain3.setTopic("“我想知道令狐安和令计划有什么关系？”");
        adDomain3
                .setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg");
        adDomain3.setAd(false);
        adList.add(adDomain3);

        AdDomain adDomain4 = new AdDomain();
        adDomain4.setId("108078");
        adDomain4.setDate("3月7日");
        adDomain4.setTitle("我和令计划只是同姓");
        adDomain4.setTopicFrom("小软");
        adDomain4.setTopic("“我想知道令狐安和令计划有什么关系？”");
        adDomain4
                .setImgUrl("http://d.hiphotos.baidu.com/image/w%3D310/sign=54884c82b78f8c54e3d3c32e0a282dee/a686c9177f3e670932e4cf9338c79f3df9dc55f2.jpg");
        adDomain4.setAd(false);
        adList.add(adDomain4);

        AdDomain adDomain5 = new AdDomain();
        adDomain5.setId("108078");
        adDomain5.setDate("3月8日");
        adDomain5.setTitle("我和令计划只是同姓");
        adDomain5.setTopicFrom("大熊");
        adDomain5.setTopic("“我想知道令狐安和令计划有什么关系？”");
        adDomain5
                .setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=66270b4fe8c4b7453494b117fffd1e78/0bd162d9f2d3572c7dad11ba8913632762d0c30d.jpg");
        adDomain5.setAd(true); // 代表是广告
        adList.add(adDomain5);

        return adList;
    }


    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
