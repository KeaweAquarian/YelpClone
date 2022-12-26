package com.example.myyelp;

import com.google.gson.JsonObject;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpServiceAPI {



    @GET("businesses/search")
    Call<YelpResponse> searchRestaurants(
     @Query("term") String term,
     @Query("location") String location);
}

//public interface API {
//
//    @GET("businesses/search")
//    Call<JsonObject> getBusinesses(@Query("term") String term, @Query("location") String location);
////                                   @Query("name") String name,
////                                    @Query("phone") String phone,
////                                    @Query("rating") Double rating,
////                                    @Query("image_url") int image_url,
////                                    @Query("price") String price);
//
//}
