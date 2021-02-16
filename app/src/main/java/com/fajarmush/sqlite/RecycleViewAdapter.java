package com.fajarmush.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<PersonBean> listPersonInfo;

    public RecycleViewAdapter(Context context, List<PersonBean>

            listPersonInfo, OnUserClickListener listener) {
        this.context = context;
        this.listPersonInfo = listPersonInfo;
        this.listener = listener;
    }

    public interface OnUserClickListener {
        void onUserClick(PersonBean currentPerson, String action);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final PersonBean currentPerson = listPersonInfo.get(position);
        holder.ctxtName.setText(currentPerson.getName());
        holder.ctxtAge.setText(currentPerson.getAge() + "");
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson, "Edit");
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson, "Delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView ctxtAge, ctxtName;
        ImageView imgDelete, imgEdit;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtAge = itemView.findViewById(R.id.cTxtAge);
            ctxtName = itemView.findViewById(R.id.cTxtName);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }
    }
}