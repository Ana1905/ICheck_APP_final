package fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.icheck_app_final.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Product;
import android.content.Context;

class GridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> arrayList;

    public GridAdapter(Context context, ArrayList<Product> arrayList){

        this.context = context;
        this.arrayList = arrayList;
    }



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Product getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_products, null);
        }

        return convertView;
    }
}
