package com.example.foodapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.R;
import com.example.foodapp.data.Panier;
import com.example.foodapp.interfaces.PanierClickListener;
import java.util.List;

/**
 * Adapter class to display Panier items in a RecyclerView.
 */
public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.PanierViewHolder> {
    private List<Panier> panierList;
    private PanierClickListener panierClickListener;

    /**
     * Constructs the WalletAdapter.
     *
     * @param panierList The list of Panier items to display.
     */
    public WalletAdapter(List<Panier> panierList) {
        this.panierList = panierList;
    }

    /**
     * Sets a listener for Panier item click events.
     *
     * @param listener Listener for Panier item clicks.
     */
    public void setPanierClickListener(PanierClickListener listener) {
        this.panierClickListener = listener;
    }

    /**
     * Updates the Panier items in the adapter and refreshes the UI.
     *
     * @param panierItems List of Panier items to update.
     */
    public void setItems(List<Panier> panierItems) {
        this.panierList = panierItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PanierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet, parent, false);
        return new PanierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanierViewHolder holder, int position) {
        Panier panier = panierList.get(position);
        holder.bind(panier);
    }

    @Override
    public int getItemCount() {
        return panierList.size();
    }

    /**
     * View holder for the Panier items.
     */
    class PanierViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productQuantity;
        AppCompatButton plusButton, minusButton;

        PanierViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productQuantity = itemView.findViewById(R.id.quantityText);
            plusButton = itemView.findViewById(R.id.plusButton);
            minusButton = itemView.findViewById(R.id.minusButton);

            plusButton.setOnClickListener(v -> {
                int currentPosition = getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION && panierClickListener != null) {
                    Panier currentPanier = panierList.get(currentPosition);
                    panierClickListener.onIncreaseClicked(currentPanier);
                }
            });

            minusButton.setOnClickListener(v -> {
                int currentPosition = getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION && panierClickListener != null) {
                    Panier currentPanier = panierList.get(currentPosition);
                    panierClickListener.onDecreaseClicked(currentPanier);
                }
            });
        }

        /**
         * Binds the Panier item data to the views.
         */
        void bind(Panier panier) {
            int imageResource = panier.getProductImage();
            productImage.setImageResource(imageResource);
            productName.setText(panier.getProductName());
            productQuantity.setText(String.valueOf(panier.getNumberOfItems()));
        }
    }
}
