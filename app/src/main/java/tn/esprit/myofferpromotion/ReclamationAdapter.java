package tn.esprit.myofferpromotion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.myofferpromotion.entity.Offer;
import tn.esprit.myofferpromotion.entity.Reclamation;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.ViewHolder> {

    private List<Reclamation> reclamationList;
    private OnItemClickListener onItemClickListener;



    // Define an interface to handle item clicks
    public interface OnItemClickListener {
        void onModifierClick(Reclamation reclamation);
        void onSupprimerClick(Reclamation reclamation);
    }

    public ReclamationAdapter(List<Reclamation> reclamationList,OnItemClickListener onItemClickListener) {
        this.reclamationList = reclamationList;
        this.onItemClickListener = onItemClickListener;

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

        // Ajoutez un gestionnaire de clic pour l'élément d'offre ici
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Répondez au clic sur l'élément ici
                // Par exemple, ouvrez une nouvelle activité ou affichez plus de détails
                Toast.makeText(v.getContext(), "Clic sur l'offre : " + reclamation.gettitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action à effectuer lors du clic sur le bouton Modifier
                onItemClickListener.onModifierClick(reclamation);

            }
        });

        // Bouton Supprimer
        holder.btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action à effectuer lors du clic sur le bouton Supprimer
                onItemClickListener.onSupprimerClick(reclamation);

            }
        });
    }

    @Override
    public int getItemCount() {
        return reclamationList != null ? reclamationList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewDescription;

        Button btnModifier;
        Button btnSupprimer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);

            btnModifier = itemView.findViewById(R.id.btnModifier);
            btnSupprimer = itemView.findViewById(R.id.btnSupprimer);

        }
    }
}


