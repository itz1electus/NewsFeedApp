package com.mufasa.newsfeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONFoodAPI {

    @GET("complexSearch?apiKey=32bbbbc8bc864f8e8eb4d9c59dc51b69&query=pasta")
    Call<List<Result>> getRequests();
}
