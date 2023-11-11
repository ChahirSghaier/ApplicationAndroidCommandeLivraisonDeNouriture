package tn.esprit.myofferpromotion;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.myofferpromotion.entity.Offer;

public class OffersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        // Configuration du RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Initialisez votre liste d'offres depuis la base de données ou d'autres sources.
        List<Offer> offersList = getOffersData();

        // Configuration de l'adaptateur
        OffersAdapter offersAdapter = new OffersAdapter(offersList);
        recyclerView.setAdapter(offersAdapter);
    }

    // Méthode fictive pour obtenir des données d'offres
    private List<Offer> getOffersData() {
        // TODO: Récupérez les données d'offres depuis la base de données ou une autre source.
        // Retournez une liste d'objets Offer.
        return new ArrayList<>();
    }
}
