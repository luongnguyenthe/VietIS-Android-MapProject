package com.example.sharelocation;

import androidx.recyclerview.widget.RecyclerView;

public interface OnItemRecyclerViewClickListener<T> {
    void onItemRecyclerViewClick(RecyclerView recyclerView, int position, T data);
}
