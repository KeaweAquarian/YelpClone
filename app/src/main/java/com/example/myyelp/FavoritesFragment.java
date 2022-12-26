package com.example.myyelp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment {
    private final String tableName= "Favorites";
    private final String DataBaseName= "sqLiteDatabase";
    private ArrayList name_id, rating_id, price_id, category_id, phone_id, address_id, city_id, state_id, image_id;
    RecyclerView recyclerView;
    MyDatabaseHelper DB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DB = new MyDatabaseHelper(this.getContext());
        name_id = new ArrayList<>();
        rating_id = new ArrayList();
        price_id = new ArrayList();
        category_id = new ArrayList();
        phone_id = new ArrayList();
        address_id = new ArrayList();
        city_id = new ArrayList();
        state_id = new ArrayList();
        image_id = new ArrayList();

        recyclerView = (RecyclerView) view.findViewById(R.id.rvFavorites);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(this.getContext(), name_id, rating_id, price_id, category_id, phone_id, address_id, city_id, state_id, image_id );
        recyclerView.setAdapter(favoriteAdapter);


    }
    private void displayData() {
        Cursor cursor = DB.getData();
        if(cursor.getCount() == 0 ) {
            Toast.makeText(this.getContext(), "No favorites exist.", Toast.LENGTH_SHORT).show();
            return;
        } else  {
            while (cursor.moveToNext()) {
                name_id.add(cursor.getString(0));
                rating_id.add(cursor.getDouble(1));
                price_id.add(cursor.getString(2));
                category_id.add(cursor.getString(3));
                phone_id.add(cursor.getString(4));
                address_id.add(cursor.getString(5));
                city_id.add(cursor.getString(6));
                state_id.add(cursor.getString(7));
                image_id.add(cursor.getString(8));
            }
        }
    }

}