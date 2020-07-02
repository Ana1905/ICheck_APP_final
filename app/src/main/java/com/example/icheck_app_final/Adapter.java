package com.example.icheck_app_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> mProductname;
    Context mContext;

    //Builder
    public Adapter(Context mContext, List<String> mProductname ){
        this.mProductname= mProductname;
        this.mContext = mContext;
    }

    //Holder
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDescription = itemView.findViewById(R.id.TextViewDescription);
        }
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_products, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
    holder.mDescription.setText(mProductname.get(position));
    }

        @Override
    public int getItemCount() {
        return mProductname.size();
    }
}

