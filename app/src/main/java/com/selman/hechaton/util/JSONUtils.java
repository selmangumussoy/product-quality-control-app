package com.selman.hechaton.util;

import android.content.Context;
import java.io.InputStream;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selman.hechaton.model.Ped;

public class JSONUtils {

    public static List<Ped> loadPedData(Context context) {
        try {
            InputStream is = context.getAssets().open("ped_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            return new Gson().fromJson(json, new TypeToken<List<Ped>>(){}.getType());

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
