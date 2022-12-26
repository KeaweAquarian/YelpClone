package com.example.myyelp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;


public class MainPageFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<YelpResponse.YelpRestaurants> restaurants;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restaurants = MainActivity.recyclerDataList;

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_page, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        recyclerView = (RecyclerView) view.findViewById(R.id.rvRestaurants);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this.getContext(), restaurants);
//        RestaurantAdapter.setClickListener(this);
        recyclerView.setAdapter(restaurantAdapter);

    }

}