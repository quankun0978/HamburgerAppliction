package com.group.hamburgerapplication.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.database.UserDatabase;
import com.group.hamburgerapplication.ultil.FuncHelper;
import com.group.hamburgerapplication.ultil.Ultils;

public class LoginActivity extends AppCompatActivity {
    private TextView txt_forgot_password,txt_register;
    private EditText edt_email,edt_password;
    private ProgressBar progressBar;
    private Button btn_continue_google ,btn_login;
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;

    GoogleSignInOptions googleSignInOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initListener();
    }
    void init(){
        txt_forgot_password=findViewById(R.id.txt_forgot_password);
        btn_continue_google=findViewById(R.id.btn_continue_google);
        btn_login=findViewById(R.id.btn_login);
        txt_register=findViewById(R.id.txt_register);
        edt_email=findViewById(R.id.edt_email);
        edt_password=findViewById(R.id.edt_password);
        progressBar=findViewById(R.id.progress_bar);
        mAuth = FirebaseAuth.getInstance();
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), googleSignInOptions);
    }
    void initListener(){
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickForgotPassword();
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickRegister();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FuncHelper.validateEmail(edt_email.getText().toString(),getApplicationContext())&&FuncHelper.validatePassword(edt_password.getText().toString(),getApplicationContext())){
                    handleOnClickLogin(edt_email.getText().toString(),edt_password.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        btn_continue_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = googleSignInClient.getSignInIntent();
                // Start activity for result
                startActivityForResult(intent, 100);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    void handleOnClickLogin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("user",user.getEmail().toString());
                            startActivity(intent);
                            finishAffinity();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Ultils.displayToast(getApplicationContext(),"Email hoặc mật khẩu không chính xác");
                        }
                    }
                });
    }
    void handleOnClickForgotPassword(){
        Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
    void handleOnClickRegister(){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition
        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
                  // check condition

                // When google sign in successful initialize string
                // Display Toast
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    firebaseAuth(googleSignInAccount.getIdToken());
                    // Check condition
                } catch (ApiException e) {
                    Log.e("ERR", e.toString());
                    progressBar.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }

    }

    private void firebaseAuth(String idToken) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Check condition
                if (task.isSuccessful()) {
                    // When task is successful redirect to profile activity display Toast
                    progressBar.setVisibility(View.INVISIBLE);
                    UserDatabase.writeNewUser(UserDatabase.getCurrentUser().getUid(),null,UserDatabase.getCurrentUser().getEmail(),null,null,null);
                    Ultils.displayToast(getApplicationContext(),"Đăng nhập thành công");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                } else {
                    // When task is unsuccessful display Toast
                    Ultils.displayToast(getApplicationContext(),"Lỗi");
                }
            }
        });

    }



}

