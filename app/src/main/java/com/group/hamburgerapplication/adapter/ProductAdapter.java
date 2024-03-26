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
        if (product == null) {
            return;
        }
        // Đảm bảo các thành phần UI cho mục chẵn và lẻ đều được cập nhật
            holder.txt_price.setText("1.99$");
            holder.txt_name.setText(product.getName());
    }



    @Override
    public int getItemCount() {
        if(listProducts!=null) return listProducts.size();
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_price ,txt_name;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_price = itemView.findViewById(R.id.price_product);
            txt_name=itemView.findViewById(R.id.name_product);

        }
    }
}
