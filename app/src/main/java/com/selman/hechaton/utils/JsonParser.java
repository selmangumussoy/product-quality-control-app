package com.selman.hechaton.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selman.hechaton.models.Product;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonParser {
    public static List<Product> loadProductData(Context context) {
        try {
            InputStream is = context.getAssets().open("ped_data.json");
            InputStreamReader reader = new InputStreamReader(is);
            return new Gson().fromJson(reader, new TypeToken<List<Product>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

