package com.group.hamburgerapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.adapter.ProductAdapter;

import com.group.hamburgerapplication.entity.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    private View view;
    private static RecyclerView rcvProduct;
    private static ProductAdapter productAdapter;
    private static List<Product> listProduct  ;
    private static LinearLayout linearLayoutManager;


    public MenuFragment() {
        // Required empty public constructor
    }


    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_menu, container, false);
        init();
        return view;
    }

    void init(){

        rcvProduct = view.findViewById(R.id.rcv_product);
        linearLayoutManager=view.findViewById(R.id.layout_progress_bar);
        linearLayoutManager.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager
                = new GridLayoutManager(getContext(),2);
        rcvProduct.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcvProduct.addItemDecoration(dividerItemDecoration);
        listProduct= new ArrayList<>();
        productAdapter = new ProductAdapter(listProduct);
        getAllProduct();
        rcvProduct.setAdapter(productAdapter);
    }
    public static void getAllProduct(){
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("product");
//        Query query = usersRef.orderByChild("email").equalTo(email);
        usersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                if(product!=null){
                    linearLayoutManager.setVisibility(View.INVISIBLE);
                    listProduct.add(product);
                    productAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}