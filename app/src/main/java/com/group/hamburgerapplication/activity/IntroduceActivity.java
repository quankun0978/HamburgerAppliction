package com.group.hamburgerapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.ultil.Ultils;

public class IntroduceActivity extends AppCompatActivity {
    private  Button btn_login,btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        init();
        initListenner();
    }
    private  void  initListenner(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickLogin();
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickContinue();
            }
        });
    }
    private  void handleOnClickLogin(){
        Intent intent = new Intent(IntroduceActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    private  void handleOnClickContinue(){
        Intent intent = new Intent(IntroduceActivity.this,MainActivity.class);
        startActivity(intent);
    }
    void init(){
        btn_login = findViewById(R.id.btn_login);
        btn_continue = findViewById(R.id.btn_continue);
    }

}