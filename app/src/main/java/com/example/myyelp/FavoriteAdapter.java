package com.example.myyelp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    private final String tableName= "Favorites";
    private final String DataBaseName= "sqLiteDatabase";
    private ArrayList name_id, rating_id, price_id, category_id, phone_id, address_id, city_id, state_id, image_id;

    Context cxt;
    int position;
    ArrayList<YelpResponse.YelpRestaurants> restaurants;
    public FavoriteAdapter(Context cxt, ArrayList name_id, ArrayList rating_id, ArrayList price_id, ArrayList category_id, ArrayList phone_id, ArrayList address_id, ArrayList city_id, ArrayList state_id, ArrayList image_id) {
        this.cxt = cxt;
        this.name_id = name_id;
        this.rating_id = rating_id;
        this.price_id = price_id;
        this.category_id = category_id;
        this.phone_id = phone_id;
        this.address_id= address_id;
        this.city_id = city_id;
        this.state_id = state_id;
        this.image_id = image_id;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cxt);
        View view = inflater.inflate(R.layout.eachyelprestaurant, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.rvName.setText(String.valueOf(name_id.get(position)));
        holder.rvRating.setRating(Float.parseFloat(String.valueOf(rating_id.get(position))));
        holder.rvCategory.setText(String.valueOf(category_id.get(position)));
        holder.rvPhone.setText(String.valueOf(phone_id.get(position)));
        holder.rvAddress.setText(String.valueOf(address_id.get(position)));
        holder.rvCity.setText(String.valueOf(city_id.get(position)));
        holder.rvProvince.setText(String.valueOf(state_id.get(position)));
        holder.rvPrice.setText(String.valueOf(price_id.get(position)));
            Glide.with(cxt).load(String.valueOf(image_id.get(position))).apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(20)))
                    .into(holder.rvImage);


    }



    @Override
    public int getItemCount() {

        return name_id.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView rvImage;
        TextView rvName, rvCategory, rvPhone, rvAddress, rvCity, rvProvince, rvPrice;
        RatingBar rvRating;
        ConstraintLayout parentLayout;

//        public void bind(YelpResponse.YelpRestaurants restaurant) {
//            rvName.setText(restaurant.name);
//            rvRating.setRating((float)restaurant.rating);
//            rvCategory.setText(restaurant.categories.get(0).title);
//            rvPhone.setText(restaurant.phone);
//            rvAddress.setText(restaurant.locations.address1);
//            rvCity.setText(restaurant.locations.city);
//            rvProvince.setText(restaurant.locations.state);
//            rvPrice.setText(restaurant.price);
//            Glide.with(cxt).load(restaurant.image_url).apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(20)))
//                    .into(rvImage);
//
//        }


        public MyViewHolder(View itemView) {
            super(itemView);
            rvImage = itemView.findViewById(R.id.rvImage);
            rvName = itemView.findViewById(R.id.rvName);
            rvRating = itemView.findViewById(R.id.rvRating);
            rvCategory = itemView.findViewById(R.id.rxCategory);
            rvPhone = itemView.findViewById(R.id.rxPhone);
            rvAddress = itemView.findViewById(R.id.rvAddress);
            rvCity = itemView.findViewById(R.id.rvCity);
            rvProvince = itemView.findViewById(R.id.rvProvince);
            rvPrice = itemView.findViewById(R.id.rvPrice);
            parentLayout = itemView.findViewById(R.id.eachYelpRestaurant);


        }


    }
}


