package com.heady.shop.utils;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.heady.shop.model.ResultResponse;
import com.heady.shop.repo.Headyepository;

public class MainViewModel extends AndroidViewModel {

    private Headyepository headyepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        headyepository = new Headyepository();
    }


    //region get response from service result
    public MutableLiveData<ResultResponse> getResponseResult(Context context) {
        return headyepository.getMutableLiveData(context);
    }
    //endregion



}
