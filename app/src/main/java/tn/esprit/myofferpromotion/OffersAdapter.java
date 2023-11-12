package tn.esprit.myofferpromotion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.myofferpromotion.entity.Offer;

    public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

        private List<Offer> offersList;

        public OffersAdapter(List<Offer> offersList) {
            this.offersList = offersList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offer, parent, false);
            return new ViewHolder(view);
        }



        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Offer offer = offersList.get(position);

            // TODO: Mettez à jour les vues de l'élément d'offre avec les données de l'offre.
            holder.textViewTitle.setText(offer.getTitle());
            holder.textViewDescription.setText(offer.getDescription());
            holder.textViewPrice.setText(String.valueOf(offer.getPrice()));
        }

        @Override
        public int getItemCount() {
            return offersList != null ? offersList.size() : 0;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textViewTitle;
            public TextView textViewDescription;
            public TextView textViewPrice;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                textViewDescription = itemView.findViewById(R.id.textViewDescription);
                textViewPrice = itemView.findViewById(R.id.textViewPrice);
            }
        }
    }


