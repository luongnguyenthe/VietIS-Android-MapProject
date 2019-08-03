package com.example.sharelocation.screens;

import androidx.recyclerview.widget.RecyclerView;

public interface OnItemRecyclerViewClickListener<T> {
    void onItemRecyclerViewClick(RecyclerView recyclerView, int position, T data);
}
