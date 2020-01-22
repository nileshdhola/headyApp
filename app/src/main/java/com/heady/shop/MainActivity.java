package com.heady.shop;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heady.shop.adapter.CategoriesAdapter;
import com.heady.shop.databinding.ActivityMainBinding;
import com.heady.shop.model.Category;
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
    private CategoriesAdapter categoriesAdapter;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        //bind model data
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getAllEmployee(this);
    }

    //region all employee data item insert in room data base
    private void getAllEmployee(Context context) {
        mainViewModel.getResponseResult(MainActivity.this).observe(this, new Observer<ResultResponse>() {
            @Override
            public void onChanged(ResultResponse resultResponse) {
                System.out.println(resultResponse.getCategories().size());
                if (resultResponse.getCategories().size() > 0) {
                    //categoriesAdapter.setCategoriesList(resultResponse.getCategories());
                    txtData.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    categoriesAdapter = new CategoriesAdapter(MainActivity.this, resultResponse.getCategories(), new CategoriesAdapter.IFCItemClick() {
                        @Override
                        public void clickCategoriesItem(String id, int position, Category result) {
                            Toast.makeText(context, "" + result.getName(), Toast.LENGTH_SHORT).show();
                        }

                    });
                    recyclerView.setAdapter(categoriesAdapter);


                } else {
                    txtData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

        });
    }
    //endregion
}
