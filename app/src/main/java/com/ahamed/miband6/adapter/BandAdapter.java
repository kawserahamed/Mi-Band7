package com.ahamed.miband6.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.miband6.R;
import com.ahamed.miband6.callback.WatchClick;
import com.ahamed.miband6.model.BandModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class BandAdapter extends RecyclerView.Adapter<BandAdapter.BandViewHolder> {

    private List<BandModel> list;
    private Context context;
    private WatchClick callback;

    public BandAdapter(List<BandModel> list, Context context, WatchClick callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    public BandAdapter() {
    }

    @NonNull
    @Override
    public BandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_band, parent, false);
        return new BandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BandViewHolder holder, int position) {
        BandModel model = list.get(position);
        holder.bind(model);
        holder.itemView.setOnClickListener(v -> callback.clickListener(model));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BandViewHolder extends RecyclerView.ViewHolder {

        ImageView gifImageView;
        TextView language;
        TextView download;

        public BandViewHolder(@NonNull View itemView) {
            super(itemView);
            gifImageView = itemView.findViewById(R.id.bandImage);
            language = itemView.findViewById(R.id.tv_language);
            download = itemView.findViewById(R.id.tv_download);
        }


        public void bind(BandModel model) {
            Glide.with(context)
                    .load(model.getImage())
                    .into(gifImageView);

            Log.d("TAG", "bind: " + model.getImage());
            language.setText(model.getLanguage());
            String str = model.getDownloads() + 76 + "";
            download.setText(str);
        }
    }


}
