package com.example.foodapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.controller.ProductWalletController;
import com.example.foodapp.data.Panier;
import com.example.foodapp.data.PanierRepository;
import com.example.foodapp.model.Product;

/**
 * Activity to add products to the shopping cart.
 */
public class AddProductWalletActivity extends AppCompatActivity {

    private TextView quantityTextView;
    private int quantity = 1;
    private PanierRepository panierRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_wallet);

        panierRepository = new PanierRepository(this);

        // Retrieve selected product from intent
        ProductWalletController productWalletController = new ProductWalletController();
        Product selectedProduct = productWalletController.getProductFromIntent(getIntent());

        if (selectedProduct != null) {
            // Display product details
            ImageView productImage = findViewById(R.id.productImage);
            TextView productName = findViewById(R.id.titleProduct);
            TextView productPrice = findViewById(R.id.priceText);
            TextView productDescription = findViewById(R.id.descriptionText);

            productImage.setImageResource(selectedProduct.getImageResource());
            productName.setText(selectedProduct.getName());
            productDescription.setText(selectedProduct.getDescription());
            productPrice.setText(selectedProduct.getPrice());
        }

        // Manage quantity buttons
        AppCompatButton plusButton = findViewById(R.id.plusButton);
        AppCompatButton minusButton = findViewById(R.id.minusButton);
        quantityTextView = findViewById(R.id.quantityText);

        quantityTextView.setText(String.valueOf(quantity));

        plusButton.setOnClickListener(v -> increaseQuantity());
        minusButton.setOnClickListener(v -> decreaseQuantity());

        // Add to cart button
        AppCompatButton addCartButton = findViewById(R.id.addToCartButton);
        addCartButton.setOnClickListener(v -> updateQuantityInDatabase(selectedProduct, Integer.parseInt(quantityTextView.getText().toString())));
    }

    // Increase the product quantity
    private void increaseQuantity() {
        quantity++;
        quantityTextView.setText(String.valueOf(quantity));
    }

    // Decrease the product quantity
    private void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
            quantityTextView.setText(String.valueOf(quantity));
        }
    }

    // Update the quantity of the product in the database (cart)
    private void updateQuantityInDatabase(Product product, int quantity) {
        new Thread(() -> {
            Panier existingItem = panierRepository.getPanierByProductId(product.getId());

            if (existingItem != null) {
                existingItem.setNumberOfItems(quantity);
                panierRepository.updatePanierItem(existingItem);
                showUpdateSuccessToast();
            } else {
                Panier newPanierItem = new Panier(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getImageResource(),
                        product.getPrice(),
                        quantity
                );
                panierRepository.insertPanierItem(newPanierItem);
                showAddSuccessToast();
            }
            navigateBack();
        }).start();
    }

    // Display update success toast
    private void showUpdateSuccessToast() {
        runOnUiThread(() -> {
            Toast.makeText(AddProductWalletActivity.this, "Item updated successfully", Toast.LENGTH_SHORT).show();
        });
    }

    // Display add success toast
    private void showAddSuccessToast() {
        runOnUiThread(() -> {
            Toast.makeText(AddProductWalletActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
        });
    }

    // Navigate back to the previous activity
    private void navigateBack() {
        finish();
    }
}
