package com.siddiqui.noteappassignment.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.siddiqui.noteappassignment.adapter.TaskAdapter;
import com.siddiqui.noteappassignment.databinding.ActivityMainBinding;
import com.siddiqui.noteappassignment.dialog.BottomSheetDialog;
import com.siddiqui.noteappassignment.inteface.BottomSheetListener;
import com.siddiqui.noteappassignment.inteface.RecyclerViewClickItem;
import com.siddiqui.noteappassignment.pojo.ListItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

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
        adapter = new TaskAdapter(arrayList, new RecyclerViewClickItem() {
            @Override
            public void onCheckSwitchButton(int position) {
                arrayList.get(position).setCheckBox(true);
                Toast.makeText(MainActivity.this, "checked", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCheckSwitchButton: ");
            }
        });
        binding.recyclerView.setAdapter(adapter);

        binding.fabBtn.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
            bottomSheetDialog.setListener(this);
            bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
        });

        @SuppressLint("HardwareIds")
        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tasks").child(deviceId).child("Notes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                if (snapshot.hasChildren()) {
                    Iterable<DataSnapshot> iterator = snapshot.getChildren();
                    for (DataSnapshot data : iterator) {
                        boolean checkBox = Objects.requireNonNull(data.child("checkBox").getValue()).toString().equalsIgnoreCase("true");
                        arrayList.add(new ListItem(Objects.requireNonNull(data.child("title").getValue()).toString(), checkBox));
                    }
                }
                adapter.notifyItemChanged(adapter.getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
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