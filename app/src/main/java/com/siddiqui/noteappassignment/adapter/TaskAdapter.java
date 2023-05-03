package com.siddiqui.noteappassignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.siddiqui.noteappassignment.R;
import com.siddiqui.noteappassignment.inteface.RecyclerViewClickItem;
import com.siddiqui.noteappassignment.pojo.ListItem;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.CustomViewModel> {
    ArrayList<ListItem> arrayList;
    RecyclerViewClickItem clickItemInterface;

    public TaskAdapter(ArrayList<ListItem> arrayList, RecyclerViewClickItem clickItemInterface) {
        this.arrayList = arrayList;
        this.clickItemInterface = clickItemInterface;
    }

    @NonNull
    @Override
    public CustomViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewModel(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false), clickItemInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewModel holder, int position) {
        ListItem listItem = arrayList.get(position);
        holder.textView.setText(listItem.getTitle());
        holder.checkBox.setChecked(listItem.isCheckBox());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CustomViewModel extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        TextView textView;
        MaterialCheckBox checkBox;
        RecyclerViewClickItem clickItem;

        public CustomViewModel(@NonNull View itemView, RecyclerViewClickItem recyclerViewClickItem) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_textView);
            checkBox = itemView.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener(this);
            clickItem = recyclerViewClickItem;
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (clickItem != null){
                clickItem.onCheckSwitchButton(getAdapterPosition());
            }
        }
    }
}
