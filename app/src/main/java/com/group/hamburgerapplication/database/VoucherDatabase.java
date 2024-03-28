package com.group.hamburgerapplication.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.group.hamburgerapplication.adapter.ProductAdapter;
import com.group.hamburgerapplication.entity.Voucher;

import java.util.ArrayList;
import java.util.List;

public class VoucherDatabase {

    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("voucher");

    private static Voucher voucher = new Voucher();

    public static void setProduct(Voucher voucher) {
        VoucherDatabase.voucher = voucher;
    }

    private final static List<Voucher> listProduct=new ArrayList<>();;

    public static List<Voucher> getListProduct() {
        return listProduct;
    }

    private static void clearListProduct() {
        listProduct.clear();
    }
    public static void writeNewVoucher( String codeId,double discount , String expiry_date , String type,boolean isPercent,String title, String description) {
        DatabaseReference newProductRef = mDatabase.push();
        String productId = newProductRef.getKey();
        Voucher voucher = new Voucher(codeId,type,expiry_date,description,discount,isPercent,title);
        newProductRef.setValue(voucher);
    }

    public static void getTypeVoucherByType(ProductAdapter productAdapter ,String type){
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("voucher");
        Query query = usersRef.orderByChild("type").equalTo(type);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Voucher voucher = snapshot.getValue(Voucher.class);
                if(voucher!=null){
                    listProduct.add(voucher);
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
