package com.ahamed.miband6.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahamed.miband6.model.BandModel;

public class LocalViewModel extends ViewModel {
    private final MutableLiveData<BandModel> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<BandModel> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(BandModel bandModel) {
        mutableLiveData.postValue(bandModel);
    }


}
