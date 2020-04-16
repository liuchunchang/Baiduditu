package com.example.bishe_git;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class List_Fragment extends Fragment {


    public List_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AddJson addJson = new AddJson("zhongdananfan");
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < addJson.jingdian.length(); i++) {
            try {
                String name = addJson.jingdian.getJSONObject(i).getString("name");
                data.add(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ListView listView = getActivity().findViewById(R.id.listview_fragment);
            //创建ArrayAdapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (getActivity(), android.R.layout.simple_expandable_list_item_1, data);
            listView.setAdapter(adapter);
        }
    }
}
