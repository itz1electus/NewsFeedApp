package com.mufasa.newsfeed;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
        Picasso.get().load(currentRecipe.getImage()).into(holder.imageViewRecipe);
        holder.textViewDescription.setText(currentRecipe.getDescription());
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

    static class TitleHolder extends RecyclerView.ViewHolder {

        private final TextView textViewTitle;
        private final ImageView imageViewRecipe;
        private final TextView textViewDescription;


        public TitleHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tvTitle);
            imageViewRecipe = itemView.findViewById(R.id.ivRecipeImg);
            textViewDescription = itemView.findViewById(R.id.tvDescription);
        }
    }

}
