package com.group.hamburgerapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.group.hamburgerapplication.R;

import com.group.hamburgerapplication.activity.MapShopActivity;
import com.group.hamburgerapplication.activity.MapUserActivity;
import com.group.hamburgerapplication.database.UserDatabase;
import com.group.hamburgerapplication.entity.User;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private View view;
    private TextView txt_email;
    private LinearLayout layout_map_ship,layout_map_home;
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        initListener();
        handleChangeSlide();
        return view;
    }
    void init(){
        layout_map_ship=view.findViewById(R.id.layout_map_ship);
        layout_map_home=view.findViewById(R.id.layout_map_home);
        txt_email=view.findViewById(R.id.txt_sub_title);
        LinearLayoutManager layoutManager
                = new GridLayoutManager(getContext(),2);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);


    }
//    public static void getAllProduct(){
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("product");
////        Query query = usersRef.orderByChild("email").equalTo(email);
//        usersRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Product product = snapshot.getValue(Product.class);
//                if(product!=null){
//                    listProduct.add(product);
//                    productAdapter.notifyDataSetChanged();
//                }
//            }
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }


    void initListener(){
        if(UserDatabase.checkLogin()){

            UserDatabase.getUserByEmail(UserDatabase.getCurrentUser().getEmail().toString(), getContext(), new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User user = dataSnapshot.getValue(User.class);
                    if(user!=null){
                        txt_email.setText("Xin chào "+user.getName());
                    }
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        layout_map_ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapShopActivity.class);
                 startActivity(intent);
            }
        });
        layout_map_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapUserActivity.class);
                startActivity(intent);
            }
        });
    }
    void handleChangeSlide(){
        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

// imageList.add(new SlideModel("String Url" or R.drawable)
// imageList.add(new SlideModel("String Url" or R.drawable, "title")) You can add title

//
        imageList.add(new SlideModel("https://res.cloudinary.com/da8l8b4tg/image/upload/v1711464851/logo1_chdkug.png", null,ScaleTypes.FIT));
        imageList.add(new SlideModel("https://res.cloudinary.com/da8l8b4tg/image/upload/v1711464851/logo2_ny8ugg.png", null,ScaleTypes.FIT));
        imageList.add(new SlideModel("https://res.cloudinary.com/da8l8b4tg/image/upload/v1711464852/logo3_xxuvif.png", null,ScaleTypes.FIT));
        imageList.add(new SlideModel("https://res.cloudinary.com/da8l8b4tg/image/upload/v1711464850/logo4_m3oosj.jpg", null,ScaleTypes.FIT));
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
    }
}