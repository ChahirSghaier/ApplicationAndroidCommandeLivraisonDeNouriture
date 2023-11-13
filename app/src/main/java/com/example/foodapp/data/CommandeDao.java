package com.example.foodapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object (Dao) interface for Commande entity operations in the Room database.
 */
@Dao
public interface CommandeDao {

    /**
     * Insert a Commande entity into the database.
     *
     * @param commande The Commande entity to be inserted.
     */
    @Insert
    void insertCommande(Commande commande);

    /**
     * Retrieve all Commande entities from the database.
     *
     * @return A list of all Commande entities stored in the database.
     */
    @Query("SELECT * FROM Commande")
    List<Commande> getAllCommandes();
}
