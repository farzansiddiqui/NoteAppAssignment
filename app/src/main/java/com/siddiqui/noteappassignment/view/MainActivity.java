package com.siddiqui.noteappassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import android.util.Log;
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
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAG, "onCreate: ");

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.fabBtn.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
            bottomSheetDialog.setListener(this);
            bottomSheetDialog.show(getSupportFragmentManager(),bottomSheetDialog.getTag());
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart:");
    }

    @Override
    public void onDataReceived(String data) {
            arrayList.add(new ListItem(data, false));
            adapter = new TaskAdapter(arrayList);
            binding.recyclerView.setAdapter(adapter);
            adapter.notifyItemChanged(adapter.getItemCount());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}