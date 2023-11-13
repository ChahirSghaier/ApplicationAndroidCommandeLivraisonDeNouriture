package com.example.foodapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * The AppDatabase class represents the Room database for managing Panier and Commande entities.
 */
@Database(entities = {Panier.class, Commande.class}, version = 1)
@TypeConverters(ProductTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Provides access to the Panier Data Access Object (DAO) for database operations.
     *
     * @return The Panier Data Access Object.
     */
    public abstract PanierDao panierDao();

    /**
     * Provides access to the Commande Data Access Object (DAO) for database operations.
     *
     * @return The Commande Data Access Object.
     */
    public abstract CommandeDao commandeDao();
}
