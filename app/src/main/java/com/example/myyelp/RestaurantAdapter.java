package com.example.myyelp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    private final String tableName= "Favorites";
    private final String DataBaseName= "sqLiteDatabase";

    Context cxt;
    int position;
    ArrayList<YelpResponse.YelpRestaurants> restaurants;
      public RestaurantAdapter(Context cxt, ArrayList<YelpResponse.YelpRestaurants> restaurants) {
          this.cxt = cxt;
          this.restaurants = restaurants;

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

          YelpResponse.YelpRestaurants restaurant = restaurants.get(position);

          holder.bind(restaurant);


    }

    @Override
    public int getItemCount() {


        return restaurants.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{


        public void bind(YelpResponse.YelpRestaurants restaurant) {
            rvName.setText(restaurant.name);
            rvRating.setRating((float)restaurant.rating);
            rvCategory.setText(restaurant.categories.get(0).title);
            rvPhone.setText(restaurant.phone);
            rvAddress.setText(restaurant.locations.address1);
            rvCity.setText(restaurant.locations.city);
            rvProvince.setText(restaurant.locations.state);
            rvPrice.setText(restaurant.price);
            Glide.with(cxt).load(restaurant.image_url).apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(20)))
                    .into(rvImage);
            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
                            builder.setTitle("Add to favorite?");
                            builder.setMessage("Do you want to add this item to your favorites?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                MyDatabaseHelper db_Helper = new MyDatabaseHelper(cxt);

                                   Boolean checkinsertdata = db_Helper.insertuserdata(restaurant.name, restaurant.rating, restaurant.price, restaurant.categories.get(0).title, restaurant.phone, restaurant.locations.address1, restaurant.locations.city, restaurant.locations.state, restaurant.image_url );
                                   if(checkinsertdata == true) {
                                       Toast.makeText(cxt, "Added to favorites!", Toast.LENGTH_SHORT).show();
                                   } else {
                                       Toast.makeText(cxt, "Item already added!", Toast.LENGTH_SHORT).show();
                                   }
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });



        }
          ImageView rvImage;
          TextView rvName, rvCategory, rvPhone, rvAddress, rvCity, rvProvince, rvPrice;
          RatingBar  rvRating;
          ConstraintLayout parentLayout;

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





