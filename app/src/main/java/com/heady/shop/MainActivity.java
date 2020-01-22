package com.heady.shop;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.heady.shop.databinding.ActivityMainBinding;
import com.heady.shop.model.ResultResponse;
import com.heady.shop.utils.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MainViewModel mainViewModel;
    private TextView txtData;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        //init
        ButterKnife.bind(this);

        txtData = activityMainBinding.txtData;
        toolbar = activityMainBinding.toolbar;
        setSupportActionBar(toolbar);

        recyclerView = activityMainBinding.viewUserRecyclerview;
        //bind model data
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getAllEmployee(this);
    }

    //region all employee data item insert in room data base
    private void getAllEmployee(Context context) {
        mainViewModel.getResponseResult(MainActivity.this).getCategories();
        mainViewModel.getResponseResult(MainActivity.this).observe(this, new Observer<ResultResponse>() {
            @Override
            public void onChanged(ResultResponse resultResponse) {
                System.out.println(resultResponse.getCategories().size());
            }

        });
    }
    //endregion
}
