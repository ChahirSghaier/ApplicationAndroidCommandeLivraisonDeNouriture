package com.example.foodapp.controller;

import android.content.Context;
import android.content.Intent;
import com.example.foodapp.R;
import com.example.foodapp.model.Product;
import com.example.foodapp.ui.AddProductWalletActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProductController manages product data and navigation logic.
 */
public class ProductController {
    private List<Product> productList;

    /**
     * Populates the list of available products.
     *
     * @return List of available products.
     */
    public List<Product> populateProductList() {
        productList = new ArrayList<>();

        productList.add(new Product(1, R.drawable.pop_1, "Product 1", "$10.99", "Description for Product 1"));
        productList.add(new Product(2, R.drawable.pop_2, "Product 2", "$15.49", "Description for Product 2"));
        productList.add(new Product(3, R.drawable.pop_3, "Product 3", "$5.49", "Description for Product 3"));

        return productList;
    }

    /**
     * Adds a product to the wallet and navigates to the 'AddProductWalletActivity'.
     *
     * @param context The context from which the function is called.
     * @param product The selected product to add.
     */
    public void addProductAndNavigate(Context context, Product product) {
        Intent intent = new Intent(context, AddProductWalletActivity.class);
        intent.putExtra("selected_product", product);
        context.startActivity(intent);
    }
}
