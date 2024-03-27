package com.group.hamburgerapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.activity.ItemProduct;
import com.group.hamburgerapplication.adapter.EditProductAdapter;

import java.util.ArrayList;
import java.util.List;


public class EditProduct extends Fragment {
    private RecyclerView recyclerView;
    private EditProductAdapter adapter;
    View view;

    public EditProduct() {
    }

    public static EditProduct newInstance() {
        EditProduct fragment = new EditProduct();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        adapter = new EditProductAdapter(getContext());
    }

    private List<ItemProduct> getList() {
        List<ItemProduct> items = new ArrayList<>();
        items.add(new ItemProduct("iPhone 15 Pro Max", "256GB", "26.190.000đ", R.drawable.img, R.drawable.baseline_edit_square_24, R.drawable.baseline_delete_24));
        items.add(new ItemProduct("iPhone 15 Pro Max", "256GB", "26.190.000đ", R.drawable.img, R.drawable.baseline_edit_square_24, R.drawable.baseline_delete_24));
        items.add(new ItemProduct("iPhone 15 Pro Max", "256GB", "26.190.000đ", R.drawable.img, R.drawable.baseline_edit_square_24, R.drawable.baseline_delete_24));
        items.add(new ItemProduct("iPhone 15 Pro Max", "256GB", "26.190.000đ", R.drawable.img, R.drawable.baseline_edit_square_24, R.drawable.baseline_delete_24));
        return items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_product, container, false);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recyclerView = view.findViewById(R.id.list_item);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.setData(getList());
        recyclerView.setAdapter(adapter);
    }
}
