package com.heady.shop.utils;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.heady.shop.model.ResultResponse;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Headyepository headyepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        headyepository = new Headyepository();
    }


    public ResultResponse getResponseResult(Context context) {
        return headyepository.getMutableLiveData(context);
    }




}
