package com.example.simplechatapp;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private final Context context;
    private final List<UserModel> userModelList;


    public UsersAdapter(Context context,List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
    }


    public void add(UserModel userModel){

        userModelList.add(userModel);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clear(){
        userModelList.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.MyViewHolder holder, int position) {
         UserModel userModel = userModelList.get(position);
         holder.name.setText(userModel.getUserName());
        holder.email.setText(userModel.getUserEmail());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,ChatActivity.class);
            intent.putExtra("id",userModel.getUserID());
            intent.putExtra("name",userModel.getUserName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {

        return userModelList.size();
    }

    public List<UserModel> getUserModelList(){
        return userModelList;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

       private final TextView name;
        private final TextView email;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.useremail);
        }
    }
}
