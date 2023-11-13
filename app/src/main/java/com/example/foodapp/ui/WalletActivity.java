package com.example.foodapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.WalletAdapter;
import com.example.foodapp.data.Commande;
import com.example.foodapp.data.CommandeRepository;
import com.example.foodapp.data.Panier;
import com.example.foodapp.data.PanierRepository;
import com.example.foodapp.data.Product;
import com.example.foodapp.interfaces.PanierClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WalletActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WalletAdapter walletAdapter;
    private PanierRepository panierRepository;
    private List<Panier> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        // Initialize Panier Repository and RecyclerView
        panierRepository = new PanierRepository(this);
        recyclerView = findViewById(R.id.panierRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup action when the submit button is clicked
        AppCompatButton submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view -> submitCommande());

        // Fetch and display Panier items
        fetchPanierItemsInBackground();
    }

    private void fetchPanierItemsInBackground() {
        new Thread(() -> {
            // Fetch Panier items asynchronously
            items = panierRepository.getAllPanierItems();

            // Update UI after fetching data
            new Handler(Looper.getMainLooper()).post(() -> {
                setWalletAdapter(items);
                calculateAndDisplaySum(items);
            });
        }).start();
    }

    private void setWalletAdapter(List<Panier> panierItems) {
        walletAdapter = new WalletAdapter(panierItems);
        recyclerView.setAdapter(walletAdapter);

        walletAdapter.setPanierClickListener(new PanierClickListener() {
            @Override
            public void onIncreaseClicked(Panier panier) {
                increasePanierQuantity(panier);
            }

            @Override
            public void onDecreaseClicked(Panier panier) {
                decreasePanierQuantity(panier);
            }
        });
    }

    private void increasePanierQuantity(Panier panier) {
        // Increase the quantity of a Panier item
        int currentQuantity = panier.getNumberOfItems();
        panier.setNumberOfItems(currentQuantity + 1);
        panierRepository.updatePanierItem(panier);
        updateAdapter();
    }

    private void decreasePanierQuantity(Panier panier) {
        // Decrease the quantity of a Panier item
        int currentQuantity = panier.getNumberOfItems();
        if (currentQuantity > 1) {
            panier.setNumberOfItems(currentQuantity - 1);
            panierRepository.updatePanierItem(panier);
            updateAdapter();
        }
    }

    private void updateAdapter() {
        new Thread(() -> {
            items = panierRepository.getAllPanierItems();
            new Handler(Looper.getMainLooper()).post(() -> {
                walletAdapter.setItems(items);
                walletAdapter.notifyDataSetChanged();
                calculateAndDisplaySum(items);
            });
        }).start();
    }

    private void calculateAndDisplaySum(List<Panier> panierItems) {
        // Calculate the total sum and display costs
        final double DELIVERY_COST = 15.0;
        final double TAX = 5.0;

        double totalSum = 0;
        for (Panier panier : panierItems) {
            int quantity = panier.getNumberOfItems();
            String priceString = panier.getProductPrice().replaceAll("\\$", "");
            double price = Double.parseDouble(priceString);
            totalSum += quantity * price;
        }

        double sumWithDeliveryAndTax = totalSum + DELIVERY_COST + TAX;

        // Display the total, delivery cost, and tax
        TextView sumTextView = findViewById(R.id.totalText);
        TextView deliveryCostText = findViewById(R.id.deliveryCostText);
        TextView taxText = findViewById(R.id.taxText);
        TextView total = findViewById(R.id.total);

        deliveryCostText.setText(String.format(Locale.US, "$ %.2f", DELIVERY_COST));
        taxText.setText(String.format(Locale.US, "$ %.2f", TAX));
        sumTextView.setText(String.format(Locale.US, "$ %.2f", sumWithDeliveryAndTax));
        total.setText(String.format(Locale.US, "$ %.2f", sumWithDeliveryAndTax));
    }

    private void submitCommande() {
        // Handle submission of the command
        Commande commande = new Commande();
        List<Product> products = new ArrayList<>();

        for (Panier panier : items) {
            Product product = new Product();
            product.setProductName(panier.getProductName());
            product.setProductImage(panier.getProductImage());
            product.setProductPrice(panier.getProductPrice());
            product.setQuantity(panier.getNumberOfItems());
            products.add(product);
        }

        CommandeRepository commandeRepository = new CommandeRepository(WalletActivity.this);
        new Thread(() -> {
            commande.setProducts(products);
            commandeRepository.insertCommande(commande);
            panierRepository.deletePanierItem();

            // Clear items and update the view on the main thread
            new Handler(Looper.getMainLooper()).post(() -> {
                items.clear();
                walletAdapter.notifyDataSetChanged();
                // Navigate back to the previous screen
                onBackPressed();
            });
        }).start();
    }
}
