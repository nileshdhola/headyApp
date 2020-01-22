package com.heady.shop.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.heady.shop.model.ResultResponse;

import org.json.JSONObject;

public class Headyepository {
    private static final String TAG = "Headyepository";
    // private ArrayList<ResultResponse> responseArrayList = new ArrayList<>();
    //private MutableLiveData<List<ResultResponse>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResultResponse> mutableLiveData = new MutableLiveData<>();
    private ResultResponse resultResponse;


    public Headyepository() {
    }

    /*public MutableLiveData<List<ResultResponse>> getMutableLiveData(Context context) {
        MutableLiveData<List<ResultResponse>> liveData = callAPI(context);
        return liveData;
    }*/

    public MutableLiveData<ResultResponse> getMutableLiveData(Context context) {
        MutableLiveData<ResultResponse> liveData = callAPI(context);
        return liveData;
    }


    private MutableLiveData<ResultResponse> callAPI(Context context) {

        OkHttpAync okHttpAync = new OkHttpAync(context, new OkHttpAync.AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                Gson gson = new Gson();
                try {
                    if (!TextUtils.isEmpty(output.toString())) {
                        JSONObject jObject = new JSONObject(output.toString());
                        ResultResponse resultResponse = gson.fromJson(jObject.toString(), ResultResponse.class);
                        mutableLiveData.setValue(resultResponse);

                    } else {
                        Toast.makeText(context, "No data found.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
        okHttpAync.execute();

        return mutableLiveData;
    }


}
