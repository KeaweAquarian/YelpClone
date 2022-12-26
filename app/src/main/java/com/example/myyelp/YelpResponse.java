package com.example.myyelp;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;
import java.util.ArrayList;

//Mapper
public class YelpResponse {
    @SerializedName("total")
    public int total;
    @SerializedName("businesses")
    public ArrayList<YelpRestaurants> restaurants;


    public class YelpRestaurants{
    @SerializedName("name")
    public String name;
    @SerializedName("rating")
    public double rating;

    @SerializedName("price")
    public String price;

    @SerializedName("image_url")
    public String image_url;

    @SerializedName("phone")
    public String phone;

    @SerializedName("categories")
    public ArrayList<YelpCategory> categories;

    @SerializedName("location")
    public YelpLocation locations;

    }
     public  class  YelpCategory{
         @SerializedName("title")
         public String title;
     }

    public  class  YelpLocation{
        @SerializedName("address1")
        public String address1;
        @SerializedName("city")
        public String city;
        @SerializedName("state")
        public String state;
    }


//
//    public String getRestaurantName() {
//        return name;
//    }
//
//    public void setRestName(String rating) {
//        this.name = name;
//    }
//
//    public double getRestauranRating() {
//        return rating;
//    }
//
//    public void setRestRating(double rating) {
//        this.rating = rating;
//    }
//
//    public String getRestaurantPrice() {
//        return price;
//    }
//
//    public void setRestPrice(String price) {
//        this.price = price;
//    }
//
//    public String getRestaurantImage() {
//        return image_url;
//    }
//
//    public void setRestImage(String image_url) {
//        this.image_url = image_url;
//    }
//
//    public String getRestaurantPhone() {
//        return phone;
//    }
//
//    public void setRestPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getRestaurantLocation() {
//        return location;
//    }
//
//    public void setRestLocation(String location) {
//        this.location = location;
//    }


//    public void Businesses(String name, double rating, String price, String image_url, String phone, String location) {
//        this.name = name;
//        this.price = price;
//        this.image_url = image_url;
//        this.phone = phone;
//        this.location = location;
//    }
}