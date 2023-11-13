package tn.esprit.myofferpromotion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.myofferpromotion.entity.Reclamation;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.ViewHolder> {

    private List<Reclamation> reclamationList;

    public ReclamationAdapter(List<Reclamation> offersList) {
        this.reclamationList = offersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reclamation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reclamation reclamation = reclamationList.get(position);

        // TODO: Mettez à jour les vues de l'élément d'offre avec les données de l'offre.
        holder.textViewTitle.setText(reclamation.gettitle());
        holder.textViewDescription.setText(reclamation.getDescription());
       // holder.textViewNameProduit.setText(String.valueOf(reclamation.getDescription()));
    }

    @Override
    public int getItemCount() {
        return reclamationList != null ? reclamationList.size() : 0;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewDescription;
       // public TextView textViewNameProduit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
           // textViewNameProduit = itemView.findViewById(R.id.textViewNameProduit);
        }
    }
}
