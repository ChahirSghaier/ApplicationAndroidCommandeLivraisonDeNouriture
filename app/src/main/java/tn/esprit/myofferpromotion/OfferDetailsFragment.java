package tn.esprit.myofferpromotion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import tn.esprit.myofferpromotion.entity.Offer;
public class OfferDetailsFragment extends Fragment {

    public OfferDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer_details, container, false);

        // Obtenez l'offre de l'argument (bundle)
        if (getArguments() != null && getArguments().containsKey("clickedOffer")) {

            Offer clickedOffer =(Offer) getArguments().getSerializable("clickedOffer");

            // Utilisez les données de l'offre pour mettre à jour l'interface utilisateur

            TextView titleTextView = view.findViewById(R.id.textViewTitle);
            TextView descriptionTextView = view.findViewById(R.id.textViewDescription);
            TextView priceTextView = view.findViewById(R.id.textViewPrice);

            titleTextView.setText(clickedOffer.getTitle());
            descriptionTextView.setText(clickedOffer.getDescription());
            priceTextView.setText(String.valueOf(clickedOffer.getPrice()));
        }

        return view;
    }
}
