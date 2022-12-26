package com.example.myyelp;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpClient {

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            return chain.proceed(chain.request()
                                    .newBuilder()
                                    .addHeader("Authorization", "Bearer 1IYpJxJSmbgfY_q5YzOrZlhvlptSMfzFsY5K3fdS2syif33JN-8dR-CgpbthkZ7fj1-r5wCMqtvTjS6CskHuYpIukDAf0Vi7WHSX7zaoe1AFnybJSppyLQgw3O2jYnYx")
                                    .build());
                        }
                    }).build();

    public YelpServiceAPI build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(YelpServiceAPI.class);
    }
}
