package com.heady.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heady.shop.R;
import com.heady.shop.adapter.ProductAdapter;
import com.heady.shop.model.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtData)
    TextView txtData;
    @BindView(R.id.viewUserRecyclerviewProduct)
    RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private String productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //ProductActivity activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        //init
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            productData = bundle.getString("product");
        }


        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        getProductDetails(productData);
    }

    private void getProductDetails(String productData) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Product>>() {
        }.getType();
        ArrayList<Product> productList = gson.fromJson(productData, listType);
        if (productList.size() > 0) {
            //categoriesAdapter.setCategoriesList(resultResponse.getCategories());
            txtData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            productAdapter = new ProductAdapter(ProductActivity.this, productList, new ProductAdapter.IFCItemClick() {
                @Override
                public void clickCategoriesItem(String id, int position, Product result) {

                }
            });
            recyclerView.setAdapter(productAdapter);


        } else {
            txtData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}
