package com.example.foodapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * Represents a Commande entity that holds a list of products and an ID.
 */
@Entity
public class Commande {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private List<Product> products;

    /**
     * Retrieve the ID of the Commande.
     *
     * @return The ID of the Commande.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID for the Commande.
     *
     * @param id The ID of the Commande.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieve the list of products associated with the Commande.
     *
     * @return The list of products in the Commande.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Set the list of products for the Commande.
     *
     * @param products The list of products to be associated with the Commande.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
