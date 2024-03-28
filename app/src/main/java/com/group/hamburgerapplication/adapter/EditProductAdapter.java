package com.group.hamburgerapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.activity.ItemProduct;
import com.group.hamburgerapplication.entity.Product;

import java.util.List;

public class EditProductAdapter extends RecyclerView.Adapter<EditProductAdapter.myViewHolder> {
    private Context context;
    private List<ItemProduct> items;

    public EditProductAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ItemProduct> items){
        this.items = items;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_update_product,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ItemProduct item = items.get(position);
        holder.img.setImageResource(item.getImg());
        holder.name.setText(item.getName());
        holder.describe.setText(item.getDescribe());
        holder.price.setText(item.getPrice());




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView name,describe,price;
        private ImageButton edit,delete;
        private ImageView img;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.id_img);
            name = itemView.findViewById(R.id.id_tensp);
            describe = itemView.findViewById(R.id.id_mota);
            price = itemView.findViewById(R.id.id_tien);
            edit = itemView.findViewById(R.id.image_edit);
            delete = itemView.findViewById(R.id.image_delete);
        }
    }
}
