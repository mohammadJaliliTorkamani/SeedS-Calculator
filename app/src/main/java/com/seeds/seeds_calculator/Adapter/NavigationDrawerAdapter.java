package com.seeds.seeds_calculator.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seeds.seeds_calculator.Interface.DrawerClickListener;
import com.seeds.seeds_calculator.Model.DrawerItem;
import com.seeds.seeds_calculator.R;
import com.seeds.seeds_calculator.Utils.ContextHelper;
import com.seeds.seeds_calculator.Utils.Helper;

import java.util.LinkedList;
import java.util.List;


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.DrawerViewHolder> {
    private DrawerClickListener listener;
    private List<DrawerItem> list = new LinkedList<>();

    public NavigationDrawerAdapter(DrawerClickListener listener) {
        this.listener = listener;
        list.clear();
        list.addAll(Helper.getDrawerItems());
    }

    @NonNull
    @Override
    public DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DrawerViewHolder(LayoutInflater.from(ContextHelper.retrieveContext()).inflate(R.layout.item_navigation_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerViewHolder holder, int position) {
        ImageView imageView;
        TextView textViewPlus;
        imageView = holder.itemView.findViewById(R.id.item_navigation_drawer_icon);
        textViewPlus = holder.itemView.findViewById(R.id.item_navigation_drawer_text);
        textViewPlus.setText(list.get(position).getName());
        imageView.setImageResource(list.get(position).getIcon());
        holder.itemView.setOnClickListener(v -> listener.OnDrawerItemClicked(list.get(position)));

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class DrawerViewHolder extends RecyclerView.ViewHolder {
        public DrawerViewHolder(@NonNull View view) {
            super(view);
        }
    }
}