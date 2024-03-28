package com.group.hamburgerapplication.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.common.Validate;
import com.group.hamburgerapplication.database.UserDatabase;
import com.group.hamburgerapplication.entity.User;
import com.group.hamburgerapplication.ultil.FuncHelper;
import com.group.hamburgerapplication.ultil.Ultils;

public class RegisterActivity extends AppCompatActivity {
    private TextView txt_login;
    private  Button btn_register;
    private  EditText edt_name,edt_email,edt_address,edt_phone,edt_password;
    private FirebaseAuth mAuth;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        initListener();
    }
    void init(){
        txt_login=findViewById(R.id.txt_login);
        btn_register=findViewById(R.id.btn_register);
        edt_email=findViewById(R.id.edt_email);
        edt_name=findViewById(R.id.edt_name);
        edt_address=findViewById(R.id.edt_address);
        edt_password=findViewById(R.id.edt_password);
        edt_phone=findViewById(R.id.edt_phone);
        mAuth = FirebaseAuth.getInstance();
    }
    void initListener(){
        dialog = new Dialog(RegisterActivity.this);
        dialog.setContentView(R.layout.activity_progress);
        dialog.setCancelable(false); // Đặt cho Dialog không bị hủy khi nhấn nút Backq
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            ((Window) window).setAttributes(layoutParams);
        }
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickRegister();
            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    void handleOnClickRegister(){
        if(FuncHelper.validateName(edt_name.getText().toString(),getApplicationContext())&&FuncHelper.validateEmail(edt_email.getText().toString(),getApplicationContext())&&FuncHelper.validatePhone(edt_phone.getText().toString(),getApplicationContext())&&FuncHelper.validatePhone(edt_phone.getText().toString(),getApplicationContext())&&FuncHelper.validatePassword(edt_password.getText().toString(),getApplicationContext())){

            UserDatabase.getUserByEmail(edt_email.getText().toString(), getApplicationContext(), new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    dialog.show();
                    User user = dataSnapshot.getValue(User.class);
                    if(user!=null){
                        Ultils.displayToast(getApplicationContext(),"Email này đã tồn tại trong hệ thống");
                    }
                    else {
                        Ultils.displayToast(getApplicationContext(),"hihi");
                        handleCreateUser(edt_email.getText().toString(),edt_password.getText().toString());
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
                    dialog.show();
                }
            });
        }
    }
    void handleCreateUser(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserDatabase.writeNewUser( user.getUid().toString(),edt_name.getText().toString(),edt_email.getText().toString(),edt_phone.getText().toString(),edt_address.getText().toString(),edt_password.getText().toString());
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}