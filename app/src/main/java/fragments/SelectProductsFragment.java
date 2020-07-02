package fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icheck_app_final.R;

import java.util.ArrayList;
import java.util.List;

public class SelectProductsFragment extends Fragment {
    private RecyclerView datalist;
    private List<String> mProductname;

    public static SelectProductsFragment newInstance(Context context) {
        return new SelectProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductname= new ArrayList<>();
        mProductname.add("Leche");
        mProductname.add("tortillas");
        mProductname.add("coca");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_products, container, false);
        datalist = view.findViewById(R.id.recycler_products);

        datalist.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        datalist.setAdapter(new ProductsAdapter());

        // Inflate the layout for this fragment
        return view;
    }

    private class ProductsAdapter extends RecyclerView.Adapter<ProductsHolder>{

        @NonNull
        @Override
        public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ProductsHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductsHolder holder, int position) {
            String product = mProductname.get(position);
            holder.bind(product);
        }

        @Override
        public int getItemCount() {
            return mProductname.size();
        }
    }

    private class ProductsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private String mProduct;
        //Controllers
        private TextView mDescription;

            private ProductsHolder(LayoutInflater inflater, ViewGroup parent){
                super(inflater.inflate(R.layout.item_products, parent, false));
                //Wiring up
                mDescription = itemView.findViewById(R.id.textViewDescription);
            }

        private void bind(String product){
            mProduct = product;
            //Update stuff
            mDescription.setText(mProduct);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), mProduct, Toast.LENGTH_LONG).show();
        }
    }
}