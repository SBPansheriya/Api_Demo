package com.kmsoft.api_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kmsoft.api_demo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RecyclerView recyclerView;
    RecyclerView_Adpater adapter;
    Modal modall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_Name.class);
                intent.putExtra("button","add");
                startActivity(intent);
            }
        });

        Call<List<Modal>> listCall = Api_Instance.getRetrofitInstance().create(Interface_Api.class).getalldata();
        listCall.enqueue(new Callback<List<Modal>>() {
            @Override
            public void onResponse(Call<List<Modal>> call, Response<List<Modal>> response) {
                getlist(response.body());
            }

            @Override
            public void onFailure(Call<List<Modal>> call, Throwable t) {

            }
        });

//     ArrayList<Modal> list = new ArrayList<>();
//        Call<Modal> listCall = Api_Instance.getRetrofitInstance().create(Interface_Api.class).getalldata(modall.getId(), modall.getName(), modall.getSuperiorId());
//
//        listCall.enqueue(new Callback<Modal>() {
//            @Override
//            public void onResponse(Call<Modal> call, Response<Modal> response) {
//                getlist(list);
//            }
//
//            @Override
//            public void onFailure(Call<Modal> call, Throwable t) {
//                Toast.makeText(MainActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    private void getlist(List<Modal> list) {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new RecyclerView_Adpater(MainActivity.this, list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}