package com.group.hamburgerapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.database.UserDatabase;
import com.group.hamburgerapplication.databinding.ActivityMainBinding;
import com.group.hamburgerapplication.fragment.AccountFragment;
import com.group.hamburgerapplication.fragment.HomeFragment;
import com.group.hamburgerapplication.fragment.MenuFragment;
import com.group.hamburgerapplication.fragment.SettingFragment;
import com.group.hamburgerapplication.fragment.VoucherFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txt_email;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ChangeFragment(new HomeFragment());
        init();
        if(UserDatabase.checkLogin()){
            txt_email.setText(UserDatabase.getCurrentUser().getEmail().toString());
        }
        initListener();
    }
    void init(){
        txt_email=findViewById(R.id.txt_sub_title);
    }
    void initListener(){
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.account){
                    ChangeFragment(new AccountFragment());
                }
                if(item.getItemId()==R.id.setting){
                    ChangeFragment(new SettingFragment());
                }
                if(item.getItemId()==R.id.voucher){
                    ChangeFragment(new VoucherFragment());
                }
                if(item.getItemId()==R.id.menu){
                    ChangeFragment(new MenuFragment());
                }
                if(item.getItemId()==R.id.homeMain){
                    ChangeFragment(new HomeFragment());
                }
                return  true;
            }
        });
    }
    void handleKillActivity(){
        finishAffinity();
    }
    void ChangeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
}