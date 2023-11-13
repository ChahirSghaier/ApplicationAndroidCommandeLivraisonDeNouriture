package com.example.foodapp.interfaces;

import com.example.foodapp.data.Panier;

public interface PanierClickListener {
    void onIncreaseClicked(Panier panier);
    void onDecreaseClicked(Panier panier);
}
