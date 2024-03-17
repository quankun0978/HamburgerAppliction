package com.group.hamburgerapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.activity.LoginActivity;
import com.group.hamburgerapplication.database.UserDatabase;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {
    private Button btn_logout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        btn_logout = view.findViewById(R.id.btn_logout);
        if(UserDatabase.checkLogin()){
            btn_logout.setVisibility(View.VISIBLE);
            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Hiển thị Toast khi button được nhấn
                    handleLogout();
                }
            });
        }
        else {
            btn_logout.setVisibility(View.INVISIBLE);
        }

        return view;
    }
//    void init(View view){
//        btn_logout=view.findViewById(R.id.btn_logout);
//    }
//    void initListener(){
//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Button Logout clicked", Toast.LENGTH_SHORT).show();
//                handleLogout();
//            }
//        });
//    }
    void handleLogout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent  = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}