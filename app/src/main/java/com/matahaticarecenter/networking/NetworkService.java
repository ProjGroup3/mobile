package com.matahaticarecenter.networking;

import com.matahaticarecenter.model.ResponseGallery;
import com.matahaticarecenter.model.ResponseLogin;
import com.matahaticarecenter.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {

    public static String BASE_URL = "http://192.168.68.43/matahati/api/";


    @GET("api/gallery")
    Call<ResponseGallery> getGalleryCall();

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseLogin> loginCall(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/register")
    Call<ResponseRegister> registerCall(
            @Field("username") String username,
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );
}
