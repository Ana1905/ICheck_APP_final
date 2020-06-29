package fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.icheck_app_final.R;
import com.example.icheck_app_final.SignUpActivity;

import java.util.ArrayList;

import models.Product;


public class SelectProductsFragment extends Fragment {

    private GridView mGrid;
    //private GridAddapter mAddapter;

    public static SelectProductsFragment newInstance(Context context) {
        return new SelectProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_products, container, false);


        ArrayList<Product> array= new ArrayList<>();



        mGrid = view.findViewById(R.id.select_grid);
       // mAddapter = new GridAdapter(this.getContext(),array);
      //  mGrid.setAdapter(mAddapter);

        // Inflate the layout for this fragment
        return view;
    }
}