package tn.esprit.myofferpromotion;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.myofferpromotion.entity.Offer;

public class OfferDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);

        // Ajoutez le fragment OfferDetailsFragment dans l'activité
        if (savedInstanceState == null) {
            OfferDetailsFragment fragment = new OfferDetailsFragment();
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("clickedOffer")) {
                Offer clickedOffer = (Offer) intent.getSerializableExtra("clickedOffer");
                Bundle bundle = new Bundle();
                bundle.putSerializable("clickedOffer",clickedOffer);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
            }
        }
    }
}
