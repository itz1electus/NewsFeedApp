package com.mufasa.newsfeed;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.TitleHolder> {
    private List<Result> recipeList = new ArrayList<>();
    final int[] count = {2};

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newspost, parent, false);
        return new TitleHolder(itemView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull TitleHolder holder, int position) {
        Result currentRecipe = recipeList.get(position);
        holder.textViewTitle.setText(currentRecipe.getTitle());
        Picasso.get().load(currentRecipe.getImage()).into(holder.imageViewRecipe);
        holder.textViewDescription.setText(currentRecipe.getDescription());
        holder.textViewUsername.setText(currentRecipe.getUsername());
        holder.id = currentRecipe.getId();
        holder.imageViewRecipe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.toggleButtonLike.setChecked(true);
                return false;
            }
        });
        holder.textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count[0] % 2 == 0) {
                    holder.textViewDescription.setSingleLine(false);
                    holder.textViewButton.setText("less");
                } else {
                    holder.textViewDescription.setSingleLine(true);
                    holder.textViewButton.setText("more");
                }
                count[0]++;
            }
        });
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
        private final TextView textViewUsername;
        private int id;
        private final ToggleButton toggleButtonLike;
        private final TextView textViewButton;

        @SuppressLint("ClickableViewAccessibility")
        public TitleHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tvTitle);
            imageViewRecipe = itemView.findViewById(R.id.ivRecipeImg);
            textViewDescription = itemView.findViewById(R.id.tvDescription);
            textViewUsername = itemView.findViewById(R.id.tvUsername);
            toggleButtonLike = itemView.findViewById(R.id.tbLike);
            textViewButton = itemView.findViewById(R.id.tvButton);
        }

    }

}
