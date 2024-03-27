package com.group.hamburgerapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.group.hamburgerapplication.R;
import com.group.hamburgerapplication.adapter.ProductManagementAdapter;
import com.group.hamburgerapplication.adapter.ViewPagerAdapter;

public class ProductManagement extends AppCompatActivity {
    private ProductManagementAdapter mAdapter;
    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private int[] mTabTitles = new int[]{R.string.add_dishes,R.string.edit_dishes};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);
        mAdapter = new ProductManagementAdapter(this);
        mViewPager2 = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager2.setAdapter(mAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(ProductManagement.this.getResources().getString(mTabTitles[position]));
            }
        }).attach();
    }
}