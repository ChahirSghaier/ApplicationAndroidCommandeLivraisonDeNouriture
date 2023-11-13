package tn.esprit.myofferpromotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.myofferpromotion.dao.DataBaseReclamation;


import tn.esprit.myofferpromotion.entity.Reclamation;

public class ReclamationActivity extends AppCompatActivity {

    private DataBaseReclamation dataBaseReclamation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);

        // Initialiser le gestionnaire de base de données
        dataBaseReclamation = new DataBaseReclamation(this);
         addSampleData();
        // Configuration du RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Initialisez votre liste d'offres depuis la base de données ou d'autres sources.
        List<Reclamation> reclamationsList = getOffersData();

        // Configuration de l'adaptateur
        ReclamationAdapter reclamationAdapter = new ReclamationAdapter(reclamationsList);
        recyclerView.setAdapter(reclamationAdapter);
        // Ajout d'un écouteur de clic au RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Répondez au clic sur l'élément de la liste ici
                // Par exemple, ouvrez une nouvelle activité ou affichez plus de détails
                Reclamation clickedReclamation = reclamationsList.get(position);
                Toast.makeText(ReclamationActivity.this, "Clic sur l'offre : " + clickedReclamation.gettitle() , Toast.LENGTH_SHORT).show();
                // Passer à la nouvelle activité avec les détails de l'offre cliqué

            }
        }));

        Button buttonAddOffer = findViewById(R.id.buttonAddReclamation);

        buttonAddOffer.setOnClickListener(view -> {
            // Lancer l'activité d'ajout d'offres
            Intent intent = new Intent(ReclamationActivity.this, AddOfferActivity.class);
            startActivity(intent);
        });
    }

    // Méthode pour ajouter des données d'exemple à la base de données (à faire une seule fois)
    private void addSampleData() {
        SQLiteDatabase db = dataBaseReclamation.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseReclamation.COLUMN_TITLE, "reclamation 1");
        values.put(DataBaseReclamation.COLUMN_DESCRIPTION, "Description de l'reclamation 1");
        db.insert(DataBaseReclamation.TABLE_RECLAMATION, null, values);

        values.clear();

        values.put(DataBaseReclamation.COLUMN_TITLE, "reclamation 2");
        values.put(DataBaseReclamation.COLUMN_DESCRIPTION, "Description de l'reclamation 2");

        db.insert(DataBaseReclamation.TABLE_RECLAMATION, null, values);

        // Ajoutez autant d'offres que nécessaire
    }




    // Méthode fictive pour obtenir des données d'offres
    // Méthode pour récupérer les offres depuis la base de données
    private List<Reclamation> getOffersData() {
        List<Reclamation> reclamationsList = new ArrayList<>();
        SQLiteDatabase db = dataBaseReclamation.getReadableDatabase();

        // Projection pour spécifier les colonnes à récupérer
        String[] projection = {
                DataBaseReclamation.COLUMN_ID,
                DataBaseReclamation.COLUMN_TITLE,
                DataBaseReclamation.COLUMN_DESCRIPTION
        };

        // Query pour récupérer les données
        Cursor cursor = db.query(
                DataBaseReclamation.TABLE_RECLAMATION,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        // Parcourir le curseur pour récupérer les données
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseReclamation.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseReclamation.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseReclamation.COLUMN_DESCRIPTION));


            // Créer un objet Offer à partir des données et l'ajouter à la liste
            Reclamation reclamation = new Reclamation(id, title, description);
            reclamationsList.add(reclamation);
        }

        // Fermer le curseur après utilisation
        cursor.close();

        return reclamationsList;
    }


    public void onModifierClick(Reclamation reclamation) {
        Intent intent = new Intent(this, AddOfferActivity.class);
        intent.putExtra("mode", "modify"); // You can use this extra to indicate the modification mode
        intent.putExtra("offre", reclamation);
        startActivity(intent);
    }


    public void onSupprimerClick(Reclamation reclamation) {
        // Handle the 'Supprimer' button click

        long offerIdToDelete = reclamation.getId();
        // Call a method to delete the offer from the database or perform other deletion logic
        deleteOfferFromDatabase(offerIdToDelete);
    }

    // Method to delete the offer from the database
    private void deleteOfferFromDatabase(long reclamationId) {
        DataBaseReclamation dbHelper = new DataBaseReclamation(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Specify the whereClause to delete the specific row
        String whereClause = DataBaseReclamation.COLUMN_ID + " = ?";
        String[] whereArgs = { String.valueOf(reclamationId) };

        // Use the delete method with the whereClause and whereArgs
        int rowsDeleted = db.delete(DataBaseReclamation.TABLE_RECLAMATION, whereClause, whereArgs);

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
