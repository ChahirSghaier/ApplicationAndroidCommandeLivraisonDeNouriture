package tn.esprit.myofferpromotion;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.myofferpromotion.dao.DatabaseHelper;
import tn.esprit.myofferpromotion.entity.Offer;

public class OffersActivity extends AppCompatActivity implements OffersAdapter.OnItemClickListener {
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        // Initialiser le gestionnaire de base de données
        databaseHelper = new DatabaseHelper(this);
        // addSampleData();
        // Configuration du RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Initialisez votre liste d'offres depuis la base de données ou d'autres sources.
        List<Offer> offersList = getOffersData();

        // Configuration de l'adaptateur
        OffersAdapter offersAdapter = new OffersAdapter(offersList,this);
        recyclerView.setAdapter(offersAdapter);

        // Ajout d'un écouteur de clic au RecyclerView
    /*    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Répondez au clic sur l'élément de la liste ici
                Offer clickedOffer = offersList.get(position);

                Toast.makeText(OffersActivity.this, "Clic sur l'offre : " + clickedOffer.getTitle() , Toast.LENGTH_SHORT).show();
                // Passer à la nouvelle activité avec les détails de l'offre cliqué
                Intent intent = new Intent(OffersActivity.this, DetailsActivity.class);
                intent.putExtra("clickedOffer",clickedOffer);
                startActivity(intent);
            }
        }));*/

        Button buttonAddOffer = findViewById(R.id.buttonAddOffer);

        buttonAddOffer.setOnClickListener(view -> {
            // Lancer l'activité d'ajout d'offres
            Intent intent = new Intent(OffersActivity.this, AddOfferActivity.class);
            startActivity(intent);
        });
    }

    // Méthode pour ajouter des données d'exemple à la base de données (à faire une seule fois)
    private void addSampleData() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, "Offre 1");
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, "Description de l'offre 1");
        values.put(DatabaseHelper.COLUMN_PRICE, 19.99);
        db.insert(DatabaseHelper.TABLE_OFFERS, null, values);

        values.clear();

        values.put(DatabaseHelper.COLUMN_TITLE, "Offre 2");
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, "Description de l'offre 2");
        values.put(DatabaseHelper.COLUMN_PRICE, 29.99);
        db.insert(DatabaseHelper.TABLE_OFFERS, null, values);

        // Ajoutez autant d'offres que nécessaire
    }




    // Méthode fictive pour obtenir des données d'offres
    // Méthode pour récupérer les offres depuis la base de données
    private List<Offer> getOffersData() {
        List<Offer> offersList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Projection pour spécifier les colonnes à récupérer
        String[] projection = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_TITLE,
                DatabaseHelper.COLUMN_DESCRIPTION,
                DatabaseHelper.COLUMN_PRICE
        };

        // Query pour récupérer les données
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_OFFERS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        // Parcourir le curseur pour récupérer les données
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRICE));

            // Créer un objet Offer à partir des données et l'ajouter à la liste
            Offer offer = new Offer(id, title, description, price);
            offersList.add(offer);
        }

        // Fermer le curseur après utilisation
        cursor.close();

        return offersList;
    }

    @Override
    public void onModifierClick(Offer offre) {
        Intent intent = new Intent(OffersActivity.this , UpdateOfferActivity.class);
        intent.putExtra("mode", "modify"); // You can use this extra to indicate the modification mode
        intent.putExtra("offre", offre);
        startActivity(intent);

    }

    @Override
    public void onSupprimerClick(Offer offre) {
        // Handle the 'Supprimer' button click

        long offerIdToDelete = offre.getId();
        // Call a method to delete the offer from the database or perform other deletion logic
        deleteOfferFromDatabase(offerIdToDelete);
    }

    // Method to delete the offer from the database
    private void deleteOfferFromDatabase(long offerId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Specify the whereClause to delete the specific row
        String whereClause = DatabaseHelper.COLUMN_ID + " = ?";
        String[] whereArgs = { String.valueOf(offerId) };

        // Use the delete method with the whereClause and whereArgs
        int rowsDeleted = db.delete(DatabaseHelper.TABLE_OFFERS, whereClause, whereArgs);

        if (rowsDeleted > 0) {
            // Successful deletion
            Toast.makeText(this, "Offre supprimée avec succès!", Toast.LENGTH_SHORT).show();
            // You might want to refresh the RecyclerView or update the UI here
        } else {
            // Deletion failed
            Toast.makeText(this, "Erreur lors de la suppression de l'offre.", Toast.LENGTH_SHORT).show();
        }
    }
}
