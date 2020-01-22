package com.heady.shop.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommonUtils {

    public static Object getHttpResponse() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .callTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

        Request request = new Request.Builder()
                .url(Constant.URL)
                .build();

        Response response = null;
        try {

            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return e.getMessage();
        }
        //return null;
    }

}
