package com.example.foodapp.data;

import android.content.Context;
import androidx.room.Room;
import java.util.List;

/**
 * Repository class for handling Commande entity data operations.
 */
public class CommandeRepository {
    private String DB_NAME = "commande_db";
    private AppDatabase appDatabase;

    /**
     * Constructs a CommandeRepository.
     *
     * @param context The application context used to create the database instance.
     */
    public CommandeRepository(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    /**
     * Inserts a Commande entity into the database.
     *
     * @param commande The Commande entity to be inserted.
     */
    public void insertCommande(Commande commande) {
        appDatabase.commandeDao().insertCommande(commande);
    }

    /**
     * Retrieves all Commande entities from the database.
     *
     * @return A list of all Commande entities stored in the database.
     */
    public List<Commande> getAllCommandes() {
        return appDatabase.commandeDao().getAllCommandes();
    }
}
