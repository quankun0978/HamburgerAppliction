package com.group.hamburgerapplication.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.group.hamburgerapplication.R;

import java.io.IOException;

public class AddProduct extends Fragment {
    private ImageView add_img;
    private Button btn_add_product;
    private TextInputEditText edt_name_product,edt_description_product,edt_price_product;
    public Bitmap bitmap;
    public String path;
    public AddProduct() {
    }
//    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                Intent data = result.getData();
//                if (data == null) {
//                    return;
//                    // Xử lý đường dẫn hình ảnh ở đây
//                }
//                Uri selectedImageUri = data.getData();
//                path = selectedImageUri.toString();
//
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);
//                    add_img.setImageBitmap(bitmap);
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//
//    });

    public static AddProduct newInstance() {
        AddProduct fragment = new AddProduct();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }
}