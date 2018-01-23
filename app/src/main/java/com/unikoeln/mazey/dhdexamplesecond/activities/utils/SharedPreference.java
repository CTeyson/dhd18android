package com.unikoeln.mazey.dhdexamplesecond.activities.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;

public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<EventItem> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, EventItem eventItem) {
        List<EventItem> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<EventItem>();
        favorites.add(eventItem);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, EventItem eventItem) {
        ArrayList<EventItem> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(eventItem);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<EventItem> getFavorites(Context context) {
        SharedPreferences settings;
        List<EventItem> favorites;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            EventItem[] favoriteItems = gson.fromJson(jsonFavorites, EventItem[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<EventItem>(favorites);
        } else
            return null;

        return (ArrayList<EventItem>) favorites;
    }
}
