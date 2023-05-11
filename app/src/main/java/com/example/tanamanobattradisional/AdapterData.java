package com.example.tanamanobattradisional;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    ArrayList<ModelData> listData;
    LayoutInflater inflater;

    public AdapterData(Context context, ArrayList<ModelData> listData) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public AdapterData.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_main, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.HolderData holder, int position) {
        holder.tvNamaTanaman.setText(listData.get(position).getNama());
        holder.cvListTanaman.setOnClickListener(view -> {
            Intent intent = new Intent(inflater.getContext(), DetailActivity.class);
            intent.putExtra("nama",  listData.get(position).getNama());
            intent.putExtra("image_url",  listData.get(position).getImage_url());
            intent.putExtra("deskripsi",  listData.get(position).getDeskripsi());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            inflater.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        CardView cvListTanaman;
        TextView tvNamaTanaman;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvNamaTanaman = itemView.findViewById(R.id.tvNamaTanaman);
            cvListTanaman = itemView.findViewById(R.id.cvListTanaman);
        }
    }
}
