package com.example.foodapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Data Access Object (DAO) for managing Panier items in the database.
 */
@Dao
public interface PanierDao {

    /**
     * Inserts a Panier item into the database.
     *
     * @param panier The Panier item to be inserted.
     */
    @Insert
    void insertPanier(Panier panier);

    /**
     * Retrieves all Panier items from the database.
     *
     * @return A List of all Panier items.
     */
    @Query("SELECT * FROM Panier")
    List<Panier> getAllPanierItems();

    /**
     * Deletes all Panier items from the database.
     */
    @Query("DELETE FROM Panier")
    void deleteAllPanierItems();

    /**
     * Retrieves a Panier item by its product ID.
     *
     * @param productId The ID of the product.
     * @return The Panier item corresponding to the provided product ID.
     */
    @Query("SELECT * FROM Panier WHERE productId = :productId")
    Panier getPanierByProductId(int productId);

    /**
     * Updates a Panier item in the database.
     *
     * @param panier The Panier item to be updated.
     */
    @Update
    void updatePanier(Panier panier);
}
