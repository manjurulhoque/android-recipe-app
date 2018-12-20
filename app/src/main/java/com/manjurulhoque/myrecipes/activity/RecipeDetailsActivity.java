package com.manjurulhoque.myrecipes.activity;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.manjurulhoque.myrecipes.R;
import com.manjurulhoque.myrecipes.dbhelper.FavouriteDbHelper;
import com.manjurulhoque.myrecipes.model.Recipe;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {

    private TextView textViewDescription, textViewDirection;
    private ImageView image;
    private Recipe recipe;
    private FloatingActionButton floatingActionButton;
    private FavouriteDbHelper favouriteDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        favouriteDbHelper = new FavouriteDbHelper(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }


        if (getIntent().getExtras().containsKey("recipe")) {
            recipe = (Recipe) getIntent().getExtras().getSerializable("recipe");
            //Toast.makeText(this, recipe.getName(), Toast.LENGTH_LONG).show();
        }

        image = findViewById(R.id.image);
        Picasso
                .get()
                .load(recipe.getImage())
                .into(image);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        // check if this recipe is favorited by user
        //Toast.makeText(this, String.valueOf(favouriteDbHelper.checkIfExists(recipe)), Toast.LENGTH_SHORT).show();
        if (favouriteDbHelper.checkIfExists(recipe)) {

            floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorited));
        }

        textViewDescription = findViewById(R.id.textViewDescription);
        textViewDirection = findViewById(R.id.textViewDirection);
        //textViewDescription.setText(getResources().getString(R.string.description));
        textViewDescription.setText(Html.fromHtml(recipe.getIngredients()));
        textViewDirection.setText(Html.fromHtml(recipe.getDirection()));

        CollapsingToolbarLayout collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setTitle(recipe.getName());

        AppBarLayout mAppBarLayout = findViewById(R.id.appbar);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                } else if (isShow) {
                    isShow = false;
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favouriteDbHelper.addData(recipe)) {
                    Toast.makeText(getApplicationContext(), "Favourited", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
