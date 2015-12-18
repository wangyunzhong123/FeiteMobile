package com.example.tianxi.feitemobile.Fragment;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianxi.feitemobile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
     * @return A new instance of fragment ClassFragment.
     */
    // TODO: Rename and change types and number of parameters

    private static ClassFragment classFragment;
    private View view;
    private LayoutInflater inflater;

    private ViewPager viewPager;//页卡内容
    private ImageView imageView;// 动画图片
    private TextView textView1,textView2,textView3;
    private List<View> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View view1,view2,view3;//各个页卡

    //List Fragment
    private FragmentPagerAdapter mFragmentAdapter;
    private List<Fragment> listFragment;
    private Fragment fragment1,fragment2,fragment3;

    public static ClassFragment newInstance(String param1, String param2) {
        if(classFragment == null){
            classFragment = new ClassFragment();
            return classFragment;
        }
        return classFragment;
        //ClassFragment fragment = new ClassFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
       // return fragment;
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
        view = inflater.inflate(R.layout.main_tab1_classfragment, container, false);
        this.inflater = inflater;

        InitImageView();

        InitTextView();

        viewPager=(ViewPager) view.findViewById(R.id.vPager);

        //InitViewPager();//使用view作为切换

        InitFragmentPaper();//使用Fragment作为切换


        return view;
    }

    private void InitViewPager() {


        //切换的是View
        views=new ArrayList<View>();
        view1=inflater.inflate(R.layout.tab1_view1_layout, null);
        view2=inflater.inflate(R.layout.tab1_view2_layout, null);
        view3=inflater.inflate(R.layout.tab1_view3_layout, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);


        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /*
    * 创建添加Framgent
    * */
    private void InitFragmentPaper(){
        //自定义切换Fragment
        listFragment = new ArrayList<Fragment>();
        fragment1 = Class_AllClassFragment.newInstance("","");
        fragment2 = Class_MyClassFragment.newInstance("","");
        fragment3 = Class_OnliveFragment.newInstance("","");
        listFragment.add(fragment1);
        listFragment.add(fragment2);
        listFragment.add(fragment3);

        mFragmentAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager())
        {

            @Override
            public int getCount()
            {
                return listFragment.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return listFragment.get(arg0);
            }
        };

        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());


//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            private int currentIndex;
//
//            @Override
//            public void onPageSelected(int position) {
////                resetTabBtn();
//                switch (position) {
////                    case 0:
////                        ((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
////                                .setImageResource(R.drawable.tab_weixin_pressed);
////                        break;
////                    case 1:
////                        ((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
////                                .setImageResource(R.drawable.tab_find_frd_pressed);
////                        break;
////                    case 2:
////                        ((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
////                                .setImageResource(R.drawable.tab_address_pressed);
////                        break;
////                    case 3:
////                        ((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
////                                .setImageResource(R.drawable.tab_settings_pressed);
////                        break;
//                }
//
//                currentIndex = position;
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//            }
//        });


    }

    /**
     *  初始化头标
     */

    private void InitTextView() {
        textView1 = (TextView) view.findViewById(R.id.text1);
        textView2 = (TextView) view.findViewById(R.id.text2);
        textView3 = (TextView) view.findViewById(R.id.text3);

        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));
        textView3.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     2      * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     3 */

    private void InitImageView() {
        imageView= (ImageView) view.findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.tab1_tabbuttonline).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }


    /**
     *
     * 头标点击监听 3 */
    private class MyOnClickListener implements View.OnClickListener {
        private int index=0;
        public MyOnClickListener(int i){
            index=i;
        }
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }

    }


    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) 	{
            container.removeView(mListViews.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return  mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

        public void onPageScrollStateChanged(int arg0) {


        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        public void onPageSelected(int arg0) {
			/*两种方法，这个是一种，下面还有一种，显然这个比较麻烦
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				break;

			}
			*/
            Animation animation = new TranslateAnimation(one * currIndex, one * arg0, 0, 0);//显然这个比较简洁，只有一行代码。
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imageView.startAnimation(animation);
            Toast.makeText(getActivity(), "您选择了" + viewPager.getCurrentItem() + "页卡", Toast.LENGTH_SHORT).show();
        }
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
