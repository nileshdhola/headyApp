package com.heady.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.heady.shop.model.ProductRanking;
import com.heady.shop.model.Ranking;

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
    private String productData, rankingData;
    private ArrayList<Product> productList;
    private ArrayList<ProductRanking> productRankings;

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
            rankingData = bundle.getString("ranking");
        }


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        getProductDetails(productData);

    }

    //region get product details data
    private void getProductDetails(String productData) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Product>>() {
        }.getType();
        productList = gson.fromJson(productData, listType);
        if (productList.size() > 0) {
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
    //endregion

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //region onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menuView:
                filter("1");
                Toast.makeText(getApplicationContext(), "Most View", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuOrder:
                filter("2");
                Toast.makeText(getApplicationContext(), "Most Order", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuShare:
                filter("3");
                Toast.makeText(getApplicationContext(), "Most Share", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuClear:
                filter("4");
                Toast.makeText(getApplicationContext(), "Clear", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    //region filer item
    private void filter(String text) {
        if (text.equalsIgnoreCase("4")) {
            productAdapter.updateList(productList);
            return;
        }
        filterAdapterData(text);
    }
    //endregion

    //region filer item in adapter
    private void filterAdapterData(String text) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Ranking>>() {
        }.getType();
        ArrayList<Ranking> rankingArrayList = gson.fromJson(rankingData, listType);
        productRankings = new ArrayList<>();
        if (text.equalsIgnoreCase("1")) {
            productRankings = rankingArrayList.get(0).getProducts();
        } else if (text.equalsIgnoreCase("2")) {
            productRankings = rankingArrayList.get(1).getProducts();
        } else if (text.equalsIgnoreCase("3")) {
            productRankings = rankingArrayList.get(2).getProducts();
        }

        ArrayList<Product> productRankings1 = new ArrayList<Product>();
        //add item product match
        for (ProductRanking ranking : productRankings) {
            for (Product product : productList) {
                if (ranking.getId().equalsIgnoreCase(product.getId())) {
                    productRankings1.add(product);
                    break;
                }
            }
        }

        //update recyclerview
        productAdapter.updateList(productRankings1);
    }
    //endregion
}
