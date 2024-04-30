package com.example.infosphere;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnteirtainmentFragment extends Fragment {

    String api = "adb919038a9a4a1898725640eef15945";
    ArrayList<Model> modelArrayList;
    Adapter adapter;
    String country ="in";
    private RecyclerView recyclerViewOfScience;
    private String category = "entertainment";







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_enteirtainment, container, false);
        recyclerViewOfScience = v.findViewById(R.id.recyclerviewOfEntertainment);
        modelArrayList = new ArrayList<>();
        recyclerViewOfScience.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter= new Adapter(getContext(),modelArrayList);
        recyclerViewOfScience.setAdapter(adapter);

        findNews();
        return v;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<mainNews>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful())
                {
                    modelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}