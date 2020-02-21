package com.seeds.seeds_calculator.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seeds.seeds_calculator.Model.License;
import com.seeds.seeds_calculator.R;
import com.seeds.seeds_calculator.Utils.ContextHelper;

import java.util.List;

public class Adapter_OpenSource extends RecyclerView.Adapter {
    private List<License> list;

    public Adapter_OpenSource(List<License> licenses) {
        this.list = licenses;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ContextHelper.retrieveContext()).inflate(R.layout.item_license, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView title = holder.itemView.findViewById(R.id.license_item_title);
        TextView content = holder.itemView.findViewById(R.id.license_item_content);

        title.setText(list.get(position).getTitle().substring(list.get(position).getTitle().lastIndexOf("_") + 1));
        content.setText(list.get(position).getContent().trim());
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
