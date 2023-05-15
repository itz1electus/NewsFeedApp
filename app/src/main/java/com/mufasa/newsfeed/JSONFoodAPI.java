package com.mufasa.newsfeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONFoodAPI {

    @GET("c81dab0e3242dc7ac0a5")
    Call<List<Result>> getRequests();
}

//  32bbbbc8bc864f8e8eb4d9c59dc51b69
