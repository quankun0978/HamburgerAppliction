package com.group.hamburgerapplication.adapter;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.group.hamburgerapplication.fragment.AddProduct;
import com.group.hamburgerapplication.fragment.ComingFragment;
import com.group.hamburgerapplication.fragment.EditProduct;
import com.group.hamburgerapplication.fragment.HistoryFragment;
public class ProductManagementAdapter extends FragmentStateAdapter{
    public ProductManagementAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("POSITION", String.valueOf(position));
        if(position==0){
            return AddProduct.newInstance();
        }
        return EditProduct.newInstance();
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}
