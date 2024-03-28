package com.group.hamburgerapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.adapter.ProductAdapter;
import com.group.hamburgerapplication.adapter.VoucherFoodAdapter;
import com.group.hamburgerapplication.adapter.VoucherTranspotAdapter;
import com.group.hamburgerapplication.entity.Voucher;
import com.group.hamburgerapplication.entity.Voucher;
import com.group.hamburgerapplication.ultil.Ultils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VoucherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoucherFragment extends Fragment {
    private View view;
    private static VoucherTranspotAdapter voucherTranspotAdapter;

    private static List<Voucher> listVouchersTranspot  ;
    private static List<Voucher> listVouchersFood  ;
    private static RecyclerView rcvVoucherFood,rcvVoucherTranspot;
    private static VoucherFoodAdapter voucherFoodAdapter;
    public VoucherFragment() {
        // Required empty public constructor
    }

    public static VoucherFragment newInstance(String param1, String param2) {
        VoucherFragment fragment = new VoucherFragment();
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
        view=inflater.inflate(R.layout.fragment_voucher, container, false);
        // Inflate the layout for this fragment
        init();
        return view;
    }
    void init(){

        rcvVoucherTranspot = view.findViewById(R.id.rcv_voucher_free_ship);
        rcvVoucherFood = view.findViewById(R.id.rcv_voucher_sale_product);
//        linearLayoutManager=view.findViewById(R.id.layout_progress_bar);
//        linearLayoutManager.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(getContext());
        rcvVoucherTranspot.setLayoutManager(layoutManager1);
        rcvVoucherFood.setLayoutManager(layoutManager2);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
//        rcvVoucherTranspot.addItemDecoration(dividerItemDecoration);
//        rcvVoucherFood.addItemDecoration(dividerItemDecoration);
        listVouchersTranspot= new ArrayList<>();
        listVouchersFood= new ArrayList<>();
        voucherTranspotAdapter = new VoucherTranspotAdapter(listVouchersTranspot);
        voucherFoodAdapter = new VoucherFoodAdapter(listVouchersFood);
        getAllVoucher();
        rcvVoucherTranspot.setAdapter(voucherTranspotAdapter);
        rcvVoucherFood.setAdapter(voucherFoodAdapter);

    }

    public static void getAllVoucher(){
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("voucher");
//        Query query = usersRef.orderByChild("type").equalTo(type);
        usersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Voucher voucher = snapshot.getValue(Voucher.class);

                if(voucher!=null){
                  if(Objects.equals(voucher.getType(), "transpot")){
                      listVouchersTranspot.add(voucher);
                      voucherTranspotAdapter.notifyDataSetChanged();
                  }
                  else {
                      listVouchersFood.add(voucher);
                  }
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