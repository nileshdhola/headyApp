package com.heady.shop.repo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.heady.shop.utils.CommonUtils;

public class OkHttpAync extends AsyncTask<Object, Object, Object> {
    private AsyncResponse ifcResponse;
    private Context context;
    private ProgressDialog pDialog;

    public OkHttpAync(Context context, AsyncResponse ifcResponse) {
        this.context = context;
        this.ifcResponse = ifcResponse;
    }

    public interface AsyncResponse {
        void processFinish(Object output);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setTitle("Please wait");
        pDialog.setMessage("download data...");
        pDialog.show();
    }

    @Override
    protected Object doInBackground(Object... params) {
        return CommonUtils.getHttpResponse();

    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        pDialog.dismiss();
        if (result != null) {
            ifcResponse.processFinish(result);
        }

    }
}
