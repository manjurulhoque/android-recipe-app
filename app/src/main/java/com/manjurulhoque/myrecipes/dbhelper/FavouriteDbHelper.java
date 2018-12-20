package com.manjurulhoque.myrecipes.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.manjurulhoque.myrecipes.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class FavouriteDbHelper extends SQLiteOpenHelper {

    private final static String TABLE = "my_recipes";
    private Context context;

    public FavouriteDbHelper(Context context) {
        super(context, TABLE, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE + " (id TEXT PRIMARY KEY, name TEXT, direction TEXT, ingredients TEXT, image TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public boolean addData(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", recipe.getKey());
        contentValues.put("name", recipe.getName());
        contentValues.put("direction", recipe.getDirection());
        contentValues.put("ingredients", recipe.getIngredients());
        contentValues.put("image", recipe.getImage());

        long result = db.insert(TABLE, null, contentValues);

        return result != -1;
    }

    public List<Recipe> getAll() {
        List<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String direction = cursor.getString(2);
            String ingredients = cursor.getString(3);
            String image = cursor.getString(4);
            Recipe recipe = new Recipe(id, name, direction, ingredients, image);
            recipes.add(recipe);
        }

        cursor.close();

        return recipes;
    }

    public boolean checkIfExists(Recipe recipe) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE + " WHERE id = " + recipe.getKey();
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() <= 0) {
                cursor.close();
                return false;
            }

            cursor.close();
        } catch (Exception e) {
            Log.d("FavouriteDbHelper", e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deleteById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE, "id=?", new String[]{id}) > 0;
    }
}
