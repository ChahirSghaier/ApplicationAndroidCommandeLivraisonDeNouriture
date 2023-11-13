package com.example.foodapp.controller;

import android.content.Intent;
import android.os.Bundle;
import com.example.foodapp.model.Product;

/**
 * The ProductWalletController handles retrieving a product from an Intent.
 */
public class ProductWalletController {

    /**
     * Retrieves a Product from the provided Intent.
     *
     * @param intent The intent that holds the product data.
     * @return The retrieved Product from the intent, or null if not found.
     */
    public Product getProductFromIntent(Intent intent) {
        Product product = null;
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("selected_product")) {
                product = (Product) extras.getSerializable("selected_product");
            }
        }
        return product;
    }
}
