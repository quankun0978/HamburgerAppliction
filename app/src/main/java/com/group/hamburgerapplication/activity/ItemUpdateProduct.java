package com.group.hamburgerapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.fragment.EditProduct;

public class ItemUpdateProduct extends AppCompatActivity {
    ImageButton image_edit,image_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update_product);
        image_edit = findViewById(R.id.image_edit);
        image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemUpdateProduct.this, EditProduct.class);
                startActivity(intent);
            }
        });
    }
}