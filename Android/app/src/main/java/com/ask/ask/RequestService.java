package com.ask.ask;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by pulakazad on 3/19/18.
 */

public interface RequestService {

    @GET("requests")
    Call<List<Request>> all();

    @GET("requests/{request_id}")
    Call<Request> get(@Path("request_id") String isbn);

    @POST("requests/new")
    Call<Request> create(@Body Request request);



}
