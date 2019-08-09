package com.example.sharelocation.screens.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharelocation.R;
import com.example.sharelocation.data.model.Place;
import com.example.sharelocation.screens.OnItemRecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class PlaceSearchRVA extends RecyclerView.Adapter<PlaceSearchRVA.ViewHolder> {

    private Context mContext;
    private RecyclerView mRecyclerView;

    private List<Place> mPlaces = new ArrayList<>();
    private OnItemRecyclerViewClickListener<Place> mListener;

    public PlaceSearchRVA(Context context, OnItemRecyclerViewClickListener<Place> listener) {
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
        if (mPlaces != null) {
            holder.bindData(mPlaces.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPlaces == null ? 0 : mPlaces.size();
    }


    public void setPlaces(List<Place> places) {
        mPlaces = places;
        notifyDataSetChanged();
    }

    public void clearPlaces() {
        if (mPlaces == null) return;
        mPlaces.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewName;
        TextView mTextViewAddress;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.tv_name);
            mTextViewAddress = itemView.findViewById(R.id.tv_address);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener == null || mPlaces == null) return;
                    mListener.onItemRecyclerViewClick(mRecyclerView, getAdapterPosition(), mPlaces.get(getAdapterPosition()));
                }
            });
        }

        void bindData(Place place) {
            mTextViewName.setText(place.getName());
            mTextViewAddress.setText(place.getAddress());
        }
    }
}
