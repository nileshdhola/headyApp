package com.heady.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.heady.shop.R;
import com.heady.shop.databinding.ProductListItemBinding;
import com.heady.shop.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CategoriesViewHolder> {
    private ArrayList<Product> products;
    private Context context;
    private ProductAdapter.IFCItemClick click;

    public ProductAdapter(Context context, ArrayList<Product> products, ProductAdapter.IFCItemClick ifcItemClick) {
        this.context = context;
        this.products = products;
        this.click = ifcItemClick;
    }


    public interface IFCItemClick {
        void clickCategoriesItem(String id, int position, Product result);

    }

    @NonNull
    @Override
    public ProductAdapter.CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ProductListItemBinding resultListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.product_list_item, viewGroup, false);

        return new ProductAdapter.CategoriesViewHolder(resultListItemBinding);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.CategoriesViewHolder CategoriesViewHolder, int i) {
        CategoriesViewHolder.setIsRecyclable(false);
        Product result = products.get(i);
        CategoriesViewHolder.resultListItemBinding.setProduct(result);

        CategoriesViewHolder.resultListItemBinding.cvEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.clickCategoriesItem(String.valueOf(result.getId()), i, result);
                // DrawableCompat.setTint(CategoriesViewHolder.employeeListItemBinding.like.getDrawable(), ContextCompat.getColor(context, R.color.colorPrimary));
            }
        });


    }


    @Override
    public int getItemCount() {
        if (products != null) {
            return products.size();
        } else {
            return 0;
        }
    }


    class CategoriesViewHolder extends RecyclerView.ViewHolder {

        private ProductListItemBinding resultListItemBinding;

        public CategoriesViewHolder(@NonNull ProductListItemBinding resultListItemBinding) {
            super(resultListItemBinding.getRoot());

            this.resultListItemBinding = resultListItemBinding;
        }
    }

}