package com.example.foodapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.example.foodapp.R;
import com.example.foodapp.adapter.ProductAdapter;
import com.example.foodapp.controller.ProductController;
import com.example.foodapp.interfaces.ProductClickListener;
import com.example.foodapp.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

/**
 * The main activity displaying a list of products and supporting navigation to the wallet.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access the product controller to fetch the product list
        ProductController productController = new ProductController();
        productList = productController.populateProductList();

        // Set up the RecyclerView and adapter
        recyclerView = findViewById(R.id.list_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(productList, new ProductClickListener() {
            @Override
            public void onAddButtonClick(int position) {
                Product product = productList.get(position);
                productController.addProductAndNavigate(MainActivity.this, product);
            }
        });
        recyclerView.setAdapter(adapter);

        // Open the WalletActivity on FloatingActionButton click
        FloatingActionButton fabWallet = findViewById(R.id.fab_wallet);
        fabWallet.setOnClickListener(view -> openWalletActivity());
    }

    // Navigates to the WalletActivity
    private void openWalletActivity() {
        Intent intent = new Intent(this, WalletActivity.class);
        startActivity(intent);
    }
}
