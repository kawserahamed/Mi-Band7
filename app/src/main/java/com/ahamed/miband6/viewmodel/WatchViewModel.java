package com.ahamed.miband6.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ahamed.miband6.model.BandModel;
import com.ahamed.miband6.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class WatchViewModel extends AndroidViewModel {
    private final Application application;

    public WatchViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }


    public LiveData<List<BandModel>> allWatch(int resID) {
        MutableLiveData<List<BandModel>> liveData = new MutableLiveData<>();

        JSONObject jsonData = JsonUtils.loadJSONFromRaw(application, resID);
        if (jsonData != null) {
            try {
                JSONArray resultsArray = jsonData.getJSONArray("results");
                List<BandModel> tempList = new ArrayList<>();
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject item = resultsArray.getJSONObject(i);
                    BandModel model = new BandModel();
                    model.setObjectId(item.getString("objectId"));
                    model.setImage(item.getString("image"));
                    model.setAuthor(item.getString("author"));
                    model.setSpecification(item.getString("specification"));
                    model.setLanguage(item.getString("language"));
                    model.setTimeformat(item.getString("timeformat"));
                    model.setTitle(item.getString("title"));
                    model.setType(item.getString("type"));
                    model.setUrl(item.getString("url"));
                    model.setBackground(item.getString("background"));
                    model.setCategory(item.getString("category"));
                    model.setCreatedAt(item.getString("createdAt"));
                    model.setUpdatedAt(item.getString("updatedAt"));


                    model.setSteps(item.getBoolean("Steps"));
                    model.setCalorie(item.getBoolean("Calorie"));
                    model.setPulse(item.getBoolean("Pulse"));
                    model.setTime(item.getBoolean("Time"));
                    model.setDate(item.getBoolean("Date"));
                    model.setWeather(item.getBoolean("Weather"));
                    model.setBattery(item.getBoolean("Battery"));
                    model.setAlarm(item.getBoolean("Alarm"));
                    model.setPAI(item.getBoolean("PAI"));
                    model.setDay(item.getBoolean("Day"));
                    model.setDistance(item.getBoolean("Distance"));

                    model.setDownloads(item.getInt("downloads"));

                    tempList.add(model);
                }
                liveData.postValue(tempList);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return liveData;
    }

}
