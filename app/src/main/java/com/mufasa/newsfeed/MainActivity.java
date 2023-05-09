package com.mufasa.newsfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        RecipeAdapter recipeAdapter = new RecipeAdapter();
        recyclerView.setAdapter(recipeAdapter);

        textView = recyclerView.findViewById(R.id.tvTitle);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/recipes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONFoodAPI jsonFoodAPI = retrofit.create(JSONFoodAPI.class);
        Call<List<Result>> apiCall = jsonFoodAPI.getRequests();

        apiCall.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code is: " + response.code());
                    return;
                }
                List<Result> results = response.body();

                for (Result result : results) {
                    textView.setText(result.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Log.e("error", "Error is: " + t.getMessage());
            }
        });

    }
}
