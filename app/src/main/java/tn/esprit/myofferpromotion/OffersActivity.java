package tn.esprit.myofferpromotion;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.myofferpromotion.dao.DatabaseHelper;
import tn.esprit.myofferpromotion.entity.Offer;

public class OffersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OffersAdapter offersAdapter;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        // Initialiser le gestionnaire de base de données
        databaseHelper = new DatabaseHelper(this);
        // addSampleData();
        // Configuration du RecyclerView
        recyclerView = findViewById(R.id.recyclerViewOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Initialisez votre liste d'offres depuis la base de données ou d'autres sources.
        List<Offer> offersList = getOffersData();

        // Configuration de l'adaptateur
        offersAdapter = new OffersAdapter(offersList);
        recyclerView.setAdapter(offersAdapter);
        // Ajout d'un écouteur de clic au RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Répondez au clic sur l'élément de la liste ici
                // Par exemple, ouvrez une nouvelle activité ou affichez plus de détails
                Offer clickedOffer = offersList.get(position);
                Toast.makeText(OffersActivity.this, "Clic sur l'offre : " + clickedOffer.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));
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
}
