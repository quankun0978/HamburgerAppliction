package com.group.hamburgerapplication.fragment;


import android.content.Intent;



import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.activity.ChangePasswordActivity;
import com.group.hamburgerapplication.activity.LoginActivity;
import com.group.hamburgerapplication.activity.OrderTrackingActivity;
import com.group.hamburgerapplication.activity.ProductManagement;
import com.group.hamburgerapplication.activity.UpdateInformationActivity;
import com.group.hamburgerapplication.database.UserDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btn_tracking,btn_edit_acc,btn_change_pw,btn_logout,btn_update_management;

    public AccountFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {

        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        init(view);
        if(UserDatabase.checkLogin()){
            btn_logout.setVisibility(View.VISIBLE);
        }
        else {
            btn_logout.setVisibility(View.INVISIBLE);
        }
        btn_tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrderTrackingActivity.class);
                startActivity(intent);
            }
        });
        btn_change_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btn_edit_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateInformationActivity.class);
                startActivity(intent);
            }
        });
        btn_update_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductManagement.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogout();


            }
        });
        return view;
    }
    private void init(@NonNull View view) {

        btn_tracking = view.findViewById(R.id.btn_tracking);
        btn_change_pw = view.findViewById(R.id.btn_change_pw);
        btn_edit_acc = view.findViewById(R.id.btn_edit_acc);
        btn_logout=view.findViewById(R.id.btn_logout);
        btn_update_management = view.findViewById(R.id.btn_update_management);
        // Inflate the layout for this fragment
    }

    void handleLogout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent  = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);


    }
    void handleRedireactLogin(){
       if(!UserDatabase.checkLogin()){
           Intent intent  = new Intent(getContext(),LoginActivity.class);
           startActivity(intent);
       }
    }
}