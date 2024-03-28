package com.group.hamburgerapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.entity.Voucher;
import com.group.hamburgerapplication.ultil.Ultils;

import java.util.List;

public class VoucherTranspotAdapter extends RecyclerView.Adapter<VoucherTranspotAdapter.VoucherFoodViewHolder> {
    private final List<Voucher> listVouchers;
    public VoucherTranspotAdapter(List<Voucher> listProducts) {
        this.listVouchers = listProducts;
        for (Voucher voucher1 : listProducts){
            Log.d("VOUCHER",voucher1.getDescription());
        }
    }
    @NonNull
    @Override
    public VoucherFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher_transpot,parent,false);
        return new VoucherFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherFoodViewHolder holder, int position) {
        Voucher voucher = listVouchers.get(position);
        Log.d("VOUCHER", voucher.getDescription());
        if (voucher == null) {
            return;
        }
        // Đảm bảo các thành phần UI cho mục chẵn và lẻ đều được cập nhật
        holder.txt_date.setText("HSD:"+" hết ngày "+voucher.getExpiry_date());
        holder.txt_description.setText(voucher.getDescription());
    }



    @Override
    public int getItemCount() {
        if(listVouchers!=null) return listVouchers.size();
        return 0;
    }

    public static class VoucherFoodViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_date ,txt_description;
        public VoucherFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_description=itemView.findViewById(R.id.txt_description);

        }
    }
}
