package com.mufasa.newsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ResultViewModel resultViewModel;
    List<Result> results;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        RecipeAdapter recipeAdapter = new RecipeAdapter();
        recyclerView.setAdapter(recipeAdapter);

        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        resultViewModel.getAllResults().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultList) {
                recipeAdapter.setRecipeList(resultList);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.npoint.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONFoodAPI jsonFoodAPI = retrofit.create(JSONFoodAPI.class);
        Call<List<Result>> apiCall = jsonFoodAPI.getRequests();

        apiCall.enqueue(new Callback<List<Result>>() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onResponse(@NonNull Call<List<Result>> call, @NonNull Response<List<Result>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                } else {
                    results = response.body();
                    RecipeAdapter recipeAdapter = new RecipeAdapter();
                    recipeAdapter.setRecipeList(results);
                    recyclerView.setAdapter(recipeAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Result>> call, @NonNull Throwable t) {
                Log.e("error", "Error is: " + t.getMessage());
            }
        });

    }

}
