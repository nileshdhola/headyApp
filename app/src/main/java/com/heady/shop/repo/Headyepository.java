package com.heady.shop.repo;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.heady.shop.model.ResultResponse;

import org.json.JSONObject;

public class Headyepository {
    private static final String TAG = "Headyepository";
    private MutableLiveData<ResultResponse> mutableLiveData = new MutableLiveData<>();
    private ResultResponse resultResponse;


    public Headyepository() {
    }

    //region live data from service
    public MutableLiveData<ResultResponse> getMutableLiveData(Context context) {
        MutableLiveData<ResultResponse> liveData = callAPI(context);
        return liveData;
    }
    //endregion

    //region calling api webservice
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
    //endregion


}
