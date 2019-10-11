package com.matahaticarecenter.networking;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {

    @FormUrlEncoded
    @POST("sendMessage")
    Call<String> sendMessage(@Field("user_id") Integer user_id, @Field("title") String title, @Field("message") String message);

    @GET("")
    Call<String> getGoogle();

    @GET("vocab_category")
    Call<HashMap<String, Object>> getVocabCategories();
}
