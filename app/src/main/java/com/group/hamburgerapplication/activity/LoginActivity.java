package com.group.hamburgerapplication.activity;

import static android.app.PendingIntent.getActivity;

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
import android.widget.ImageView;
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
    private Button btn_continue_google ,btn_login ;
    ImageView img_back;
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;

    GoogleSignInOptions googleSignInOptions;
    private  Dialog dialog;
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
        img_back=findViewById(R.id.btn_back);
        txt_register=findViewById(R.id.txt_register);
        edt_email=findViewById(R.id.edt_email);
        edt_password=findViewById(R.id.edt_password);
        mAuth = FirebaseAuth.getInstance();
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), googleSignInOptions);

    }
    void initListener(){
        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.activity_progress);
        dialog.setCancelable(false); // Đặt cho Dialog không bị hủy khi nhấn nút Backq
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }

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
//                    progressBar.setVisibility(View.VISIBLE);
//                    dialog.dismiss();
                    dialog.show();

                }
            }
        });
        btn_continue_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = googleSignInClient.getSignInIntent();
                // Start activity for result
                startActivityForResult(intent, 100);
//                progressBar.setVisibility(View.VISIBLE);
                dialog.show();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    void handleOnClickLogin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressBar.setVisibility(View.INVISIBLE);
                        dialog.dismiss();
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
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    firebaseAuth(googleSignInAccount.getIdToken());
                    // Check condition
                } catch (ApiException e) {
                    Log.e("ERR", e.toString());
//                   dialog.dismiss();
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
                   dialog.dismiss();
                    UserDatabase.writeNewUser(UserDatabase.getCurrentUser().getUid(), UserDatabase.getCurrentUser().getDisplayName(), UserDatabase.getCurrentUser().getEmail(), null, null, null);
                    Ultils.displayToast(getApplicationContext(), "Đăng nhập thành công");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    // When task is unsuccessful display Toast
                    Ultils.displayToast(getApplicationContext(), "Lỗi");
                }
            }
        });
    }
}

