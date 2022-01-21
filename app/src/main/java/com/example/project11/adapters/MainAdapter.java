package com.example.project11.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project11.R;
import com.example.project11.activity.MainActivity;
import com.example.project11.models.Member;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {

    private final static int TYPE_ITEM_VIEW = 0;
    private final static int TYPE_ITEM_LIST = 1;
    protected MainActivity mainActivity;
    private final ArrayList<Member> members;

    public MainAdapter(MainActivity mainActivity, ArrayList<Member> members) {
        this.mainActivity = mainActivity;
        this.members = members;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 5 || position == 10 || position == 20) {
            return TYPE_ITEM_LIST;
        } else {
            return TYPE_ITEM_VIEW;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_VIEW){
            return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false));
        }else {
            return new CustomViewListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Member member = members.get(position);

        if (holder instanceof CustomViewHolder) {
            TextView tv_name = ((CustomViewHolder) holder).tv_name;
            TextView tv_surname = ((CustomViewHolder) holder).tv_surname;
            LinearLayout layout = ((CustomViewHolder) holder).layout;

            tv_name.setText(member.getName());
            tv_surname.setText(member.getSurname());
            layout.setOnClickListener(view -> mainActivity.openDetailsActivity(member, position));
        }else {
            RecyclerView recyclerView = ((CustomViewListHolder)holder).recyclerView;

            ArrayList<Member> subMembers = prepareMemberList();
            refreshAdapter(recyclerView, subMembers);
        }
    }

    protected ArrayList<Member> prepareMemberList() {
        ArrayList<Member> members = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            members.add(new Member("Muhammadrizo" + i, "Nurullaxo'jayev" + i));
        }
        return members;
    }

    protected void refreshAdapter(RecyclerView recyclerView, ArrayList<Member> subMembers) {
        DetailsAdapter detailsAdapter = new DetailsAdapter(mainActivity, subMembers);
        recyclerView.setAdapter(detailsAdapter);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    private static class CustomViewHolder extends RecyclerView.ViewHolder {

        protected View view;
        protected TextView tv_name, tv_surname;
        protected LinearLayout layout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            tv_name = view.findViewById(R.id.tv_name);
            tv_surname = view.findViewById(R.id.tv_surname);
            layout = view.findViewById(R.id.layout_main);
        }
    }

    private static class CustomViewListHolder extends RecyclerView.ViewHolder {

        protected View view;
        private final RecyclerView recyclerView;

        public CustomViewListHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            recyclerView = view.findViewById(R.id.details_recycler_view);
        }
    }
}
