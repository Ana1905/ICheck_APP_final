package com.example.icheck_app_final;

import androidx.fragment.app.Fragment;

import Abstract.SingleFragmentActivity;
import fragments.SelectProductsFragment;

public class SelectProductsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new SelectProductsFragment();
    }
}
