package com.ahamed.miband6.utils;

import android.content.Context;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class JsonUtils {
    public static JSONObject loadJSONFromRaw(Context context, int resourceId) {
        String json;
        try {
            InputStream is = context.getResources().openRawResource(resourceId);
            Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
            json = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
            return new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
