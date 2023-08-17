package com.kmsoft.api_demo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerView_Adpater extends RecyclerView.Adapter<RecyclerView_Adpater.ViewHolder> {
    MainActivity mainActivity;
    List<Modal> list;

    public RecyclerView_Adpater(MainActivity mainActivity, List<Modal> list) {
        this.mainActivity = mainActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView_Adpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.recyclerview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_Adpater.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id1.setText(list.get(position).getId());
        holder.name1.setText("Name : "+list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mainActivity,view, Gravity.RIGHT);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId()==R.id.update){
                            Intent intent = new Intent(mainActivity,Add_Name.class);
                            intent.putExtra("button","update");
                            intent.putExtra("name",list.get(position).getName());
                            intent.putExtra("id",list.get(position).getId());
                            mainActivity.startActivity(intent);
                        }
                        if (menuItem.getItemId()==R.id.delete){
                            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                            builder.setMessage("Are you Sure!!! You Want To Delete ?");
                            builder.setCancelable(true);
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Interface_Api api = Api_Instance.getRetrofitInstance().create(Interface_Api.class);

                                    Call<Modal> responseCall = api.deleteData(list.get(position).getId());

                                    responseCall.enqueue(new Callback<Modal>() {
                                        @Override
                                        public void onResponse(Call<Modal> call, Response<Modal> response) {
                                            list.remove(position);
                                            notifyDataSetChanged();
                                            System.out.println(""+response.body());
                                        }

                                        @Override
                                        public void onFailure(Call<Modal> call, Throwable t) {
                                            Log.e("TTT", "onFailure: "+t.getLocalizedMessage() );
                                        }
                                    });
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name1,id1;
        ImageView morevert;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id1 = itemView.findViewById(R.id.id1);
            name1 = itemView.findViewById(R.id.name);
            morevert = itemView.findViewById(R.id.morevert);
        }
    }
}
