package com.fajarmush.sqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener, RecycleViewAdapter.OnUserClickListener {
    RecyclerView recyclerView;
    EditText editName, editAge;
    Button btnSubmit;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<PersonBean> listPersonInfo;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        recyclerView = view.findViewById(R.id.recylceView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        editAge = view.findViewById(R.id.editAge);
        editName = view.findViewById(R.id.editName);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);

        setupRecycleView();
    }

    private void setupRecycleView() {
        DatabaseHelper db = new DatabaseHelper(context);
        listPersonInfo = db.selectUserData();

        RecycleViewAdapter adapter = new RecycleViewAdapter(context, listPersonInfo, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSubmit) {
            DatabaseHelper db = new DatabaseHelper(context);
            PersonBean currentPerson = new PersonBean();
            String btnStatus = btnSubmit.getText().toString();

            if (btnStatus.equals("Submit")) {
                currentPerson.setName(editName.getText().toString());
                currentPerson.setAge(Integer.parseInt(editAge.getText().toString()));
                db.insert(currentPerson);
            }
            if (btnStatus.equals("Update")) {
                currentPerson.setName(editName.getText().toString());
                currentPerson.setAge(Integer.parseInt(editAge.getText().toString()));
                db.update(currentPerson);
            }
            setupRecycleView();
            editName.setText("");
            editAge.setText("");
            editName.setFocusable(true);
            btnSubmit.setText("Submit");
        }
    }

    @Override
    public void onUserClick(PersonBean currentPerson, String action) {
        if(action.equals("Edit")) {
            editName.setText(currentPerson.getName());
            editName.setFocusable(false);
            editAge.setText(currentPerson.getAge());
            btnSubmit.setText("Update");
        }
        if (action.equals("Delete")) {
            DatabaseHelper db = new DatabaseHelper(context);
            db.delete(currentPerson.getName());
            setupRecycleView();
        }
    }
}
