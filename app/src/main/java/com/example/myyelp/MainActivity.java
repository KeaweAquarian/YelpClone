package com.example.myyelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String BASE_URL = "https://api.yelp.com/v3/";
    public static  ArrayList<YelpResponse.YelpRestaurants> recyclerDataList;
    private Spinner spinner;
    public RestaurantAdapter restaurantAdapter;
    private String searchTerm = "salad";

    // Client ID
    //
    //yjGE_PTTma6OFfbxADI7SQ
    //API Key
    //
//    private String API_KEY = "1IYpJxJSmbgfY_q5YzOrZlhvlptSMfzFsY5K3fdS2syif33JN-8dR-CgpbthkZ7fj1-r5wCMqtvTjS6CskHuYpIukDAf0Vi7WHSX7zaoe1AFnybJSppyLQgw3O2jYnYx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide app name.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerDataList = new ArrayList<>();

        //Search view
        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                spinner.setSelection(2);
                searchTerm = s;
                recyclerDataList.clear();
                try {
                    beginAPI();
                    if (recyclerDataList.size() == 0) {
                        throw new Exception("Exception message");
                    }

                }catch (Exception e) {

                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        //set up fragments
        if(savedInstanceState ==null) {
            replaceFragment(new MainPageFragment());
        }

  //set up drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.drawerSerach) {

                    replaceFragment(new MainPageFragment());

                } else {

                    Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                    startActivity(intent);
//                    replaceFragment(new FavoritesFragment());
                }
                return false;
            }


        });


//set up spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sortByOptions, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(this);

        beginAPI();

    }
//spinner options
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        switch (i) {
            case 0:

                Collections.sort(recyclerDataList, new Comparator<YelpResponse.YelpRestaurants>() {
                    @Override
                    public int compare(YelpResponse.YelpRestaurants yelpRestaurants, YelpResponse.YelpRestaurants t1) {

                        try {
                            return Double.valueOf(t1.rating).compareTo(yelpRestaurants.rating);
                        }catch (Exception e) {
                            if(yelpRestaurants.rating == 0 && t1.rating == 0) {
                                return Double.compare(0, 0);
                            } else if (yelpRestaurants.rating == 0){
                                return Double.compare(0, t1.rating);
                            } else {
                                return Double.compare(yelpRestaurants.rating, 0);
                            }
                        }
                    }

                });
                replaceFragment(new MainPageFragment());
                Toast.makeText(this, "Rating", Toast.LENGTH_SHORT).show();

                break;
            case 1:
            Collections.sort(recyclerDataList, new Comparator<YelpResponse.YelpRestaurants>() {
                @Override
                public int compare(YelpResponse.YelpRestaurants yelpRestaurants, YelpResponse.YelpRestaurants t1) {

     try {
         return Double.compare(yelpRestaurants.price.length(), t1.price.length());
     }catch (Exception e) {
         if(yelpRestaurants.price == null && t1.price == null) {
             return Double.compare(0, 0);
         } else if (yelpRestaurants.price == null){
             return Double.compare(0, t1.price.length());
         } else {
             return Double.compare(yelpRestaurants.price.length(), 0);
         }
     }

}

            });
                spinner.setSelection(2);
            replaceFragment(new MainPageFragment());
                Toast.makeText(this, "Price", Toast.LENGTH_SHORT).show();

                break;

            default:

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainPageFrame, fragment);
        fragmentTransaction.commit();

    }

    public void beginAPI () {
        try {
            YelpServiceAPI yelpAPI = new YelpClient().build();
            yelpAPI.searchRestaurants( searchTerm, "Montreal").enqueue(new Callback<YelpResponse>() {
                @Override
                public void onResponse(Call<YelpResponse> call, Response<YelpResponse> response) {
                    YelpResponse res = response.body();
                    recyclerDataList.clear();

                    for (int i = 0; i < res.restaurants.size(); i++ ) {
                        recyclerDataList.add(res.restaurants.get(i));
                    }

                    //Set initial recycleview
                    RecyclerView recyclerView = findViewById(R.id.rvRestaurants);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    restaurantAdapter = new RestaurantAdapter(MainActivity.this, recyclerDataList);
                    recyclerView.setAdapter(restaurantAdapter);



                }

                @Override
                public void onFailure(Call<YelpResponse> call, Throwable t) {

                }
            });
        }catch (Exception e) {
            Toast.makeText(this, "No results for that search.", Toast.LENGTH_SHORT).show();
        }

    }
}