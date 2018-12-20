package com.manjurulhoque.myrecipes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manjurulhoque.myrecipes.R;
import com.manjurulhoque.myrecipes.activity.RecipeDetailsActivity;
import com.manjurulhoque.myrecipes.model.Favorite;
import com.manjurulhoque.myrecipes.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteRecyclerViewAdapter.ViewHolder> {

    private List<Recipe> favorites;
    private Context context;
    private Recipe favorite;

    public FavoriteRecyclerViewAdapter(Context context, List<Recipe> favorites) {
        this.favorites = favorites;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_favorite_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        favorite = favorites.get(position);

        holder.title.setText(favorite.getName());
        Picasso
                .get()
                .load(favorite.getImage())
                .into(holder.image);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Recipe f = favorites.get(position);
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra("recipe", f);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;
        public RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.news_title);
            image = view.findViewById(R.id.news_image);
            relativeLayout = view.findViewById(R.id.relativeLayout);
        }
    }
}
