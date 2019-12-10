package com.matahaticarecenter.networking;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {

    public static String BASE_URL = "http://192.168.68.43/api/";


    @GET("gallery")
    Call<HashMap<String, Object>> getGalleryCall();

    @FormUrlEncoded
    @POST("login")
    Call<HashMap<String, Object>> loginCall(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<HashMap<String, Object>> registerCall(
            @Field("username") String username,
            @Field("email") String email,
            @Field("fullname") String fullname,
            @Field("phone") String phone,
            @Field("password") String password
    );
}
