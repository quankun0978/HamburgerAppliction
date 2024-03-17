package com.group.hamburgerapplication.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.database.ProductDatabase;
import com.group.hamburgerapplication.ultil.Ultils;

import java.io.IOException;

public class AddProductActivity extends AppCompatActivity {
    private Button btn_add;
    private EditText edt_name_product,edt_price_product,edt_description_product,edt_category_product;
    private ProgressBar progressBar;
    private ImageView img_product;
    public Bitmap bitmap;
    public String path;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data == null) {
                    return;
                    // Xử lý đường dẫn hình ảnh ở đây
                }
                Uri selectedImageUri = data.getData();
                path = selectedImageUri.toString();

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);
                    img_product.setImageBitmap(bitmap);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }
    void init(){
        btn_add = findViewById(R.id.btn_add);
        edt_description_product=findViewById(R.id.edt_description_product);
        edt_name_product=findViewById(R.id.edt_name_product);
        edt_price_product=findViewById(R.id.edt_price_product);
        edt_category_product=findViewById(R.id.edt_category_product);
        progressBar =findViewById(R.id.progressBar);
        img_product=findViewById(R.id.img_product);
    }
    void initListener(){
       img_product.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent gallaryIntent= new Intent();
               gallaryIntent.setAction(Intent.ACTION_GET_CONTENT);
               gallaryIntent.setType("image/*");
               activityResultLauncher.launch(Intent.createChooser(gallaryIntent,"Select picture"));
           }
       });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name_product.getText().toString();
                String category = edt_category_product.getText().toString();
                String description = edt_description_product.getText().toString();
                int price = Integer.parseInt(edt_price_product.getText().toString());
                ProductDatabase.writeNewProduct(name,description,price,category,path);
            }
        });
    }
}