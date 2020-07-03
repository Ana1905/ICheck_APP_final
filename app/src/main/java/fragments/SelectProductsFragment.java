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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.icheck_app_final.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import labs.ProductLab;
import labs.QueueSingleton;
import labs.UserLab;
import models.Product;

public class SelectProductsFragment extends Fragment {
    private int quantity=0;
    private RecyclerView datalist;
    private static List<Product> mProducts;



    public static SelectProductsFragment newInstance(Context context) {
        return new SelectProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProducts = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_products, container, false);
        datalist = view.findViewById(R.id.recycler_products);

        // Inflate the layout for this fragment
        return view;
    }



    private void updateUI(){
        if (mProducts.size()>0){
            datalist.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            datalist.setAdapter(new ProductsAdapter());
        }
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
            Product product = mProducts.get(position);
            holder.bind(product);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }
    }

    private class ProductsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Controllers
        private TextView mDescription;
        private Product mProduct;

            private ProductsHolder(LayoutInflater inflater, ViewGroup parent){
                super(inflater.inflate(R.layout.item_products, parent, false));
                //Wiring up
                mDescription = itemView.findViewById(R.id.textViewDescription);
                itemView.setOnClickListener(this);
            }

        private void bind(Product product){
            mProduct = product;
            String description = product.getDescription();
            //Update stuff
            mDescription.setText(description);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), mProduct.getDescription(), Toast.LENGTH_LONG).show();
        }
    }

    public void EditProducts(RequestQueue mQueue, int quantity){
        mProducts.clear();
        String username = UserLab.get().getCurrentUser().getUsername();
        String url = "https://checkitdatabase.000webhostapp.com/search-products.php?user=" + quantity;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        Product product = new Product();
                        product.setId_product(object.getInt("quantity"));
                        mProducts.add(product);
                    }
                } catch (JSONException e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                } finally {
                    updateUI();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        QueueSingleton.get(getActivity()).addToRequestQueue(request);
    }

}