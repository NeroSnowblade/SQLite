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
    List <PersonBean> listPersonInfo;

    public RecycleViewAdapter(Context context, List<PersonBean> listPersonInfo, MainFragment mainFragment) {
    }

    public interface OnUserClickListener {
        void onUserClick(PersonBean currentPerson, String action);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.UserViewHolder holder, int position) {
        final PersonBean currentPerson = listPersonInfo.get(position);
        holder.cTxtName.setText(currentPerson.getName());
        holder.cTxtAge.setText(currentPerson.getAge());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClick(currentPerson, "Edit");
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClick(currentPerson, "Delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView cTxtAge, cTxtName;
        ImageView imgDelete, imgEdit;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            cTxtAge = itemView.findViewById(R.id.cTxtAge);
            cTxtName = itemView.findViewById(R.id.cTxtName);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgDelete = itemView.findViewById(R.id.imgEdit);
        }
    }
}
