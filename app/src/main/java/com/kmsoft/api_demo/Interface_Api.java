package com.kmsoft.api_demo;

import androidx.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Interface_Api {

    @GET("get")
    Call<List<Modal>> getalldata();

    @FormUrlEncoded
    @POST("insert")
    Call<Modal> insertData(@Field("name") String name);

    @FormUrlEncoded
    @PUT("update")
    Call<Modal> updateData(@Field("id") String id, @Field("name") String name);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = Api_Instance.BASE_URL + "delete", hasBody = true)
    Call<Modal> deleteData(@Field("id") String id);
}
