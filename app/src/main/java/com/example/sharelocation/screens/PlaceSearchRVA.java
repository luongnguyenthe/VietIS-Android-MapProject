package com.example.sharelocation.screens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sharelocation.OnItemRecyclerViewClickListener;
import com.example.sharelocation.R;

public class PlaceSearchRVA extends RecyclerView.Adapter<PlaceSearchRVA.ViewHolder> {

    private Context mContext;
    private RecyclerView mRecyclerView;

    private OnItemRecyclerViewClickListener<Object> mListener;

    public PlaceSearchRVA(Context context, OnItemRecyclerViewClickListener<Object> listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_place_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Pass Place object to binData(Place place)
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemRecyclerViewClick(mRecyclerView, getAdapterPosition(), null);
                }
            });
        }

        void bindData() {

        }
    }
}
