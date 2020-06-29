package com.example.icheck_app_final;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import fragments.HomeFragment;
import fragments.SelectProductsFragment;

public class SelectProductsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_products);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_select_products);

        if (fragment == null){
            fragment = new SelectProductsFragment();
            fm.beginTransaction()
                    .add(R.id.activity_select_products, fragment)
                    .commit();
        }
    }

}
