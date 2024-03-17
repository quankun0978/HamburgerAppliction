package com.group.hamburgerapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.entity.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> listProducts;


    public ProductAdapter(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = listProducts.get(position);
        if(product==null) {
            return;
        }
        holder.txt_id.setText( product.getName());
        holder.txt_name.setText("Giá :"+product.getPrice());
    }

    @Override
    public int getItemCount() {
        if(listProducts!=null) return listProducts.size();
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_id ,txt_name;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id=itemView.findViewById(R.id.txt_id);
            txt_name=itemView.findViewById(R.id.txt_name);
        }
    }
}
