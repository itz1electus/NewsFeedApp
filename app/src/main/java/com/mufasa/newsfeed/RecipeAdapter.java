package com.mufasa.newsfeed;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.TitleHolder> {
    private List<Result> recipeList = new ArrayList<>();

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newspost, parent, false);
        return new TitleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleHolder holder, int position) {
        Result currentRecipe = recipeList.get(position);
        holder.textViewTitle.setText(currentRecipe.getTitle());
        holder.imageViewRecipe.setImageBitmap(getBitmapFromURL(currentRecipe.getImage()));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setRecipeList(List<Result> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    class TitleHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private ImageView imageViewRecipe;


        public TitleHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tvTitle);
            imageViewRecipe = itemView.findViewById(R.id.ivRecipeImg);
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

}
