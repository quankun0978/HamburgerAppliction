package com.group.hamburgerapplication.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.hamburgerapplication.adapter.ProductAdapter;
import com.group.hamburgerapplication.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {

    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("product");

    private static Product product = new Product();

    public static void setProduct(Product product) {
        ProductDatabase.product = product;
    }

    private final static List<Product> listProduct=new ArrayList<>();;

    public static List<Product> getListProduct() {
        return listProduct;
    }

    private static void clearListProduct() {
        listProduct.clear();
    }
    public static void writeNewProduct( String name, String description, int price, String category,String path) {
        DatabaseReference newProductRef = mDatabase.push();
        String productId = newProductRef.getKey();
        Product product = new Product(productId, name, description, category, path, price);
        newProductRef.setValue(product);
    }

    public static void getAllProduct(ProductAdapter productAdapter){
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("product");
//        Query query = usersRef.orderByChild("email").equalTo(email);
        usersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                if(product!=null){
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
