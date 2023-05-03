package com.siddiqui.noteappassignment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.siddiqui.noteappassignment.R;
import com.siddiqui.noteappassignment.inteface.BottomSheetListener;
import com.siddiqui.noteappassignment.pojo.ListItem;

import java.util.Objects;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    View view;
    BottomSheetListener mListener;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListItem listItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.btm_sheet_layout, container, false);
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        TextInputEditText inputEditText = view.findViewById(R.id.title_editText);
        MaterialButton button = view.findViewById(R.id.save_btn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Tasks");
        listItem = new ListItem();

        button.setOnClickListener(view -> {
            if (!Objects.requireNonNull(inputEditText.getText()).toString().isEmpty()) {
                addToDataBase(inputEditText.getText().toString());
                mListener.onDataReceived(inputEditText.getText().toString());
            } else {
                Toast.makeText(requireContext(), "Please enter title", Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }

    private void addToDataBase(String title) {
        try {
            listItem.setTitle(title);
            listItem.setCheckBox(false);
            databaseReference.push().setValue(listItem);
            Toast.makeText(requireActivity(), "Title Added", Toast.LENGTH_SHORT).show();
            dismiss();
        }catch (Exception e){
            Log.d("TAG", "addToDataBase: "+e);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException classCastException) {
            throw new ClassCastException(context + " must implement BottomSheetListener");
        }
    }

    public void setListener(BottomSheetListener mListener) {
        this.mListener = mListener;

    }
}
