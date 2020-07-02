package fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icheck_app_final.Adapter;
import com.example.icheck_app_final.R;

import java.util.ArrayList;
import java.util.List;
//hola david
public class SelectProductsFragment extends Fragment {
    RecyclerView datalist;
    List<String> mProductname;

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

        datalist = view.findViewById(R.id.recycler_products);
        mProductname= new ArrayList<>();

        mProductname.add("Leche");
        mProductname.add("tortillas");
        mProductname.add("coca");

        Adapter adapter = new Adapter(getActivity(), mProductname);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2,
                GridLayoutManager.VERTICAL,false);

        datalist.setLayoutManager(gridLayoutManager);
        datalist.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }
}