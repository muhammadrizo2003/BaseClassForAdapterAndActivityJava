package com.example.project11.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project11.R;
import com.example.project11.activity.MainActivity;
import com.example.project11.models.Member;

import java.util.ArrayList;

public class DetailsAdapter extends BaseAdapter{
    protected MainActivity mainActivity;
    private final ArrayList<Member> members;

    public DetailsAdapter(MainActivity mainActivity, ArrayList<Member> members) {
        this.mainActivity = mainActivity;
        this.members = members;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.details_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Member member = members.get(position);

        if (holder instanceof CustomViewHolder){
            Log.d("@@@", "onBindViewHolder: "+member.toString());
        }
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    private static class CustomViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
