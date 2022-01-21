package com.example.project11.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project11.network.BaseServer;

public class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements BaseServer {



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    protected String getServer() {
        if (IS_TESTER) {
            return SERVER_DEPLOYMENT;
        } else {
            return SERVER_DEVELOPMENT;
        }
    }

    protected static boolean isEmpty(final String text) {
        return text == null || text.trim().isEmpty();
    }
}
