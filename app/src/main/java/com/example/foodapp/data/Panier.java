package com.example.foodapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class representing a shopping basket item (Panier).
 */
@Entity
public class Panier {
    @PrimaryKey(autoGenerate = true)
    private int panierId;

    // Product details embedded in Panier
    private int productId;
    private String productName;
    private String productDescription;
    private int productImage;
    private String productPrice;
    private int numberOfItems;

    /**
     * Constructs a Panier object representing a shopping basket item.
     *
     * @param productId         The ID of the product.
     * @param productName       The name of the product.
     * @param productDescription The description of the product.
     * @param productImage      The resource ID of the product image.
     * @param productPrice      The price of the product.
     * @param numberOfItems     The number of items of the product in the basket.
     */
    public Panier(int productId, String productName, String productDescription, int productImage, String productPrice, int numberOfItems) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.numberOfItems = numberOfItems;
    }

    public int getPanierId() {
        return panierId;
    }

    public void setPanierId(int panierId) {
        this.panierId = panierId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

