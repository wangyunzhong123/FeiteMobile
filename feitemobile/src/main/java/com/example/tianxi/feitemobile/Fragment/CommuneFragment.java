package com.example.tianxi.feitemobile.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.tianxi.feitemobile.Activity.Tab2_ForumActivity;
import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.Tools.LogUtil;
import com.example.tianxi.feitemobile.Tools.TempDB;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CommuneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CommuneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommuneFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommuneFragment.
     */
    // TODO: Rename and change types and number of parameters

    private View view;

    private ListView tab2_hottopic_listView;
    private List<Map<String,Object>> listData;

    //模块ImageView
    private ImageView imageView1,imageView2,imageView3,
            imageView4,imageView5,imageView6;


    public static CommuneFragment newInstance(String param1, String param2) {
        CommuneFragment fragment = new CommuneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.main_tab2_communefragment, container, false);

        initUI();

        initData();

        return view;
    }


    private void initUI(){
        tab2_hottopic_listView = (ListView)view.findViewById(R.id.tab2_hottopic_listView);
        //模块
        imageView1 = (ImageView)view.findViewById(R.id.imageView1);
        imageView2 = (ImageView)view.findViewById(R.id.imageView2);
        imageView3 = (ImageView)view.findViewById(R.id.imageView3);
        imageView4 = (ImageView)view.findViewById(R.id.imageView4);
        imageView5 = (ImageView)view.findViewById(R.id.imageView5);
        imageView6 = (ImageView)view.findViewById(R.id.imageView6);
        //设置模块监听事件
        imageView1.setOnClickListener(new ImageViewOnClickListener());
        imageView2.setOnClickListener(new ImageViewOnClickListener());
        imageView3.setOnClickListener(new ImageViewOnClickListener());
        imageView4.setOnClickListener(new ImageViewOnClickListener());
        imageView5.setOnClickListener(new ImageViewOnClickListener());
        imageView6.setOnClickListener(new ImageViewOnClickListener());
    }

    private class ImageViewOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            LogUtil.d("点击的版块Id。。。",v.getId()+"");
            Intent intent = new Intent(getActivity(), Tab2_ForumActivity.class);
            startActivity(intent);
        }

    }
    private void initData(){
        listData = TempDB.getHot_topic_listItem();
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),listData,R.layout.tab2_hot_topic_listview_item,
                new String[]{"personName","header","desc"},
                new int[]{R.id.name,R.id.header,R.id.desc});

        tab2_hottopic_listView.setAdapter(simpleAdapter);

        //设置ScrollView里面嵌套ListView
        setListViewHeightBasedOnChildren(tab2_hottopic_listView);

    }

    public void onClickForum(View v){
        LogUtil.d("点击的版块Id。。。",v.getId()+"");
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //实现在ScrollView中嵌套ListView
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
