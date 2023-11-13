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
import com.example.foodapp.interfaces.ProductClickListener;
import com.example.foodapp.model.Product;
import java.util.List;

/**
 * Adapter class for displaying products in a RecyclerView.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private ProductClickListener productClickListener;

    /**
     * Constructor to initialize the ProductAdapter.
     *
     * @param productList         List of products to be displayed.
     * @param productClickListener Click listener for product actions.
     */
    public ProductAdapter(List<Product> productList, ProductClickListener productClickListener) {
        this.productList = productList;
        this.productClickListener = productClickListener;
    }

    /**
     * Inflates the layout for each item in the RecyclerView.
     */
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    /**
     * Binds the product data to the ViewHolder's views.
     */
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    /**
     * Returns the total number of items in the list.
     */
    @Override
    public int getItemCount() {
        return productList.size();
    }

    /**
     * Inner ViewHolder class to hold the view for each product item.
     */
    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
        AppCompatButton addButton;

        /**
         * Constructor for the ViewHolder.
         */
        ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageProduct);
            productName = itemView.findViewById(R.id.textProductName);
            productPrice = itemView.findViewById(R.id.textProductPrice);
            addButton = itemView.findViewById(R.id.btnAdd);

            addButton.setOnClickListener(view -> {
                if (productClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        productClickListener.onAddButtonClick(position);
                    }
                }
            });
        }

        /**
         * Binds the product data to the ViewHolder's views.
         */
        void bind(Product product) {
            productImage.setImageResource(product.getImageResource());
            productName.setText(product.getName());
            productPrice.setText(product.getPrice());
        }
    }
}
