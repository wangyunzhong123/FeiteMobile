package com.example.tianxi.feitemobile.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tianxi.feitemobile.Activity.ClassDetail_InfoActivity;
import com.example.tianxi.feitemobile.Adapter.ImageAndTextListAdapter;
import com.example.tianxi.feitemobile.Myview.MyListView;
import com.example.tianxi.feitemobile.R;
import com.example.tianxi.feitemobile.Tools.TempDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Class_MyClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Class_MyClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Class_MyClassFragment extends Fragment {
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
     * @return A new instance of fragment Class_MyClassFragment.
     */

    private MyListView myListView;
    private List<JSONObject> jsonList;

    //VIew
    private View view;
    // TODO: Rename and change types and number of parameters
    public static Class_MyClassFragment newInstance(String param1, String param2) {
        Class_MyClassFragment fragment = new Class_MyClassFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Class_MyClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_class__my_class, container, false);

        initUI();

        initData();

        return view;
    }


    private void initUI(){
        myListView = (MyListView)view.findViewById(R.id.myClassListView);

    }

    private void initData(){
        jsonList = TempDB.getJsonList_Class_AllClass_GridList();
        final ImageAndTextListAdapter adapter = new ImageAndTextListAdapter(getActivity(),jsonList,R.layout.fragment_class_all_class_gridview);

        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ClassDetail_InfoActivity.class);
                startActivity(intent);
            }
        });

        //设置下拉刷新事件
        myListView.setonRefreshListener(new MyListView.OnRefreshListener() {
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
                        try {
                            object.put("id", 1);
                            object.put("imagUrl", "http://img1.imgtn.bdimg.com/it/u=1708563771,3440055422&fm=21&gp=0.jpg");
                            object.put("info", "");
                            object.put("link_url", "");
                            object.put("name", "太极拳1");

                            jsonList.add(object);
                        } catch (JSONException e) {

                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        myListView.onRefreshComplete();
                    }
                }.execute(null, null, null);
            }
        });
    }


//    // TODO: Rename method, update argument and hook method into UI event
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
