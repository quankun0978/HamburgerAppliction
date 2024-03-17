package com.group.hamburgerapplication.fragment;


import android.content.Intent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.activity.ChangePasswordActivity;
import com.group.hamburgerapplication.activity.OrderTrackingActivity;
import com.group.hamburgerapplication.activity.UpdateInformationActivity;
import com.group.hamburgerapplication.database.ProductDatabase;
import com.group.hamburgerapplication.entity.Product;
import com.group.hamburgerapplication.ultil.Ultils;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Button btn_tracking;
    private Button btn_change_pw;
    private Button btn_edit_acc;
    private ImageButton btn_setting;


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
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private void init(View view) {
        btn_tracking = view.findViewById(R.id.btn_tracking);
        btn_change_pw = view.findViewById(R.id.btn_change_pw);
        btn_edit_acc = view.findViewById(R.id.btn_edit_acc);
        btn_setting = view.findViewById(R.id.btn_setting);

        // Inflate the layout for this fragment

    }



}