package com.group.hamburgerapplication.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.common.Validate;
import com.group.hamburgerapplication.database.UserDatabase;
import com.group.hamburgerapplication.entity.User;
import com.group.hamburgerapplication.ultil.FuncHelper;
import com.group.hamburgerapplication.ultil.Ultils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ForgotPasswordActivity extends AppCompatActivity {
    private Button btn_confirm ;
    private EditText edt_email ;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        FirebaseApp.initializeApp(getApplicationContext()); // Thêm dòng này để khởi tạo Firebase
        mAuth = FirebaseAuth.getInstance();
        init();
        initListener();
    }
    void init(){
        btn_confirm=findViewById(R.id.btn_confirm);
        edt_email=findViewById(R.id.edt_email);
        progressBar =findViewById(R.id.progress_bar);
    }
    void initListener(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickConfirm();
            }
        });
    }
private void handleOnClickConfirm() {
    if (FuncHelper.validateEmail(edt_email.getText().toString(),getApplicationContext())) {
        UserDatabase.getUserByEmail(edt_email.getText().toString(), getApplicationContext(), new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                if(user!=null){
                    ResetPassword(edt_email.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);
                    return;
                }
                Ultils.displayToast(getApplicationContext(),"Tài khoản đã tồn tại trong hệ thống");
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
}
    private void ResetPassword(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Ultils.displayToast(getApplicationContext(),"Link đặt lại mật khẩu đã được gửi tới Email đăng ký của bạn");
                        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Ultils.displayToast(getApplicationContext(),"Vui lòng nhập email chính xác");
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }
}