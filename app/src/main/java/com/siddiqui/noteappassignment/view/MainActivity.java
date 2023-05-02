package com.siddiqui.noteappassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.siddiqui.noteappassignment.adapter.TaskAdapter;
import com.siddiqui.noteappassignment.databinding.ActivityMainBinding;
import com.siddiqui.noteappassignment.dialog.BottomSheetDialog;
import com.siddiqui.noteappassignment.inteface.BottomSheetListener;
import com.siddiqui.noteappassignment.pojo.ListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomSheetListener {
    ActivityMainBinding binding;
    ArrayList<ListItem> arrayList = new ArrayList<>();
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.fabBtn.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
            bottomSheetDialog.setListener(this);
            bottomSheetDialog.show(getSupportFragmentManager(),bottomSheetDialog.getTag());
        });
    }

    @Override
    public void onDataReceived(String data) {
            arrayList.add(new ListItem(data, false));
            adapter = new TaskAdapter(arrayList);
            binding.recyclerView.setAdapter(adapter);
            adapter.notifyItemChanged(adapter.getItemCount());
    }
}