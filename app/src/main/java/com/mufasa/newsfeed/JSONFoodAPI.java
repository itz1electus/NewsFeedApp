package com.mufasa.newsfeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONFoodAPI {

    @GET("04bdc8eab4059300fe16")
    Call<List<Result>> getRequests();
}

//  32bbbbc8bc864f8e8eb4d9c59dc51b69
