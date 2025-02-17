package com.heady.shop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.heady.shop.R;
import com.heady.shop.databinding.CategoriesListItemBinding;
import com.heady.shop.model.Category;
import com.heady.shop.model.Product;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    private ArrayList<Category> results;
    private Context context;
    private IFCItemClick click;


    public CategoriesAdapter(Context context, ArrayList<Category> categories, IFCItemClick ifcItemClick) {
        this.context = context;
        this.results = categories;
        this.click = ifcItemClick;
    }


    public interface IFCItemClick {
        void clickCategoriesItem(String id, int position, ArrayList<Product> result);

    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CategoriesListItemBinding resultListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.categories_list_item, viewGroup, false);

        return new CategoriesViewHolder(resultListItemBinding);
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
    public void onBindViewHolder(@NonNull CategoriesViewHolder CategoriesViewHolder, int i) {
        CategoriesViewHolder.setIsRecyclable(false);
        Category result = results.get(i);
        CategoriesViewHolder.resultListItemBinding.setCategory(result);

        if (!TextUtils.isEmpty(result.getName().trim())) {
            String firstLetter = String.valueOf(result.getName().trim().toUpperCase().charAt(0));
            CategoriesViewHolder.resultListItemBinding.textFirst.setText(firstLetter);
        }
        CategoriesViewHolder.resultListItemBinding.cvEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.clickCategoriesItem(String.valueOf(result.getId()), i, result.getProducts());
            }
        });


    }


    @Override
    public int getItemCount() {
        if (results != null) {
            return results.size();
        } else {
            return 0;
        }
    }


    class CategoriesViewHolder extends RecyclerView.ViewHolder {

        private CategoriesListItemBinding resultListItemBinding;

        public CategoriesViewHolder(@NonNull CategoriesListItemBinding resultListItemBinding) {
            super(resultListItemBinding.getRoot());

            this.resultListItemBinding = resultListItemBinding;
        }
    }


}
