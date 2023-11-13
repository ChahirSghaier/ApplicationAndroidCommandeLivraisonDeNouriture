package com.example.foodapp.data;

import android.content.Context;
import androidx.room.Room;
import java.util.List;

/**
 * Repository handling interactions with the Panier database.
 */
public class PanierRepository {
    private String DB_NAME = "panier_db";
    private AppDatabase appDatabase;

    public PanierRepository(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    /**
     * Inserts a Panier item into the database.
     *
     * @param panier The Panier item to be inserted.
     */
    public void insertPanierItem(Panier panier) {
        appDatabase.panierDao().insertPanier(panier);
    }

    /**
     * Retrieves all Panier items from the database.
     *
     * @return A List of all Panier items.
     */
    public List<Panier> getAllPanierItems() {
        return appDatabase.panierDao().getAllPanierItems();
    }

    /**
     * Deletes all Panier items from the database.
     */
    public void deletePanierItem() {
        appDatabase.panierDao().deleteAllPanierItems();
    }

    /**
     * Retrieves a Panier item by its product ID.
     *
     * @param productId The ID of the product.
     * @return The Panier item corresponding to the provided product ID.
     */
    public Panier getPanierByProductId(int productId) {
        return appDatabase.panierDao().getPanierByProductId(productId);
    }

    /**
     * Updates a Panier item in the database.
     *
     * @param panier The Panier item to be updated.
     */
    public void updatePanierItem(Panier panier) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.panierDao().updatePanier(panier);
            }
        }).start();
    }
}
