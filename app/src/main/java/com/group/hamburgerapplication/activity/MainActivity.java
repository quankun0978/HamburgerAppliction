package com.group.hamburgerapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.database.UserDatabase;
import com.group.hamburgerapplication.databinding.ActivityMainBinding;
//import com.group.hamburgerapplication.fragment.AccountFragment;
import com.group.hamburgerapplication.fragment.AccountFragment;
import com.group.hamburgerapplication.fragment.HomeFragment;
import com.group.hamburgerapplication.fragment.MenuFragment;
import com.group.hamburgerapplication.fragment.SettingFragment;
import com.group.hamburgerapplication.fragment.VoucherFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FloatingActionButton btn_cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ChangeFragment(new HomeFragment());
        init();
        initListener();
    }
    void init(){
        btn_cart=findViewById(R.id.btn_cart);

    }
    void initListener(){
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapUserActivity.class);
                startActivity(intent);
            }
        });
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.account){
                    ChangeFragment(new AccountFragment());
                }
                if(item.getItemId()==R.id.menu){
                    ChangeFragment(new MenuFragment());
                }
                if(item.getItemId()==R.id.voucher){
                    ChangeFragment(new VoucherFragment());
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