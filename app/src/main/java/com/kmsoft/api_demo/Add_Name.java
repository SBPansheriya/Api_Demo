package com.kmsoft.api_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kmsoft.api_demo.databinding.ActivityAddNameBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Name extends AppCompatActivity {

    ActivityAddNameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String button = getIntent().getStringExtra("button");
        if (button.equals("add")){
            binding.btnadd.setVisibility(View.VISIBLE);
        }
        if (button.equals("update")){
            binding.btnupdate.setVisibility(View.VISIBLE);
        }

        binding.addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Interface_Api api = Api_Instance.getRetrofitInstance().create(Interface_Api.class);
                Call<Modal> call = api.insertData(binding.addname.getText().toString());

                call.enqueue(new Callback<Modal>() {
                    @Override
                    public void onResponse(Call<Modal> call, Response<Modal> response) {
                        Intent intent = new Intent(Add_Name.this, MainActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<Modal> call, Throwable t) {
                        // Handle network failure
                        Log.e("TTT", "onFailure: "+t.getLocalizedMessage() );
                        Toast.makeText(Add_Name.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");

        binding.addname.setText(name);

        binding.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Interface_Api api = Api_Instance.getRetrofitInstance().create(Interface_Api.class);

             //   Modal modal = new Modal("",binding.addname.getText().toString(),"");

                Call<Modal> call = api.updateData(id,binding.addname.getText().toString());
                call.enqueue(new Callback<Modal>() {
                    @Override
                    public void onResponse(Call<Modal> call, Response<Modal> response) {
                        Log.d("TTT", "onResponse: "+response.body());
                        Intent intent = new Intent(Add_Name.this, MainActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<Modal> call, Throwable t) {
                        // Handle network failure
                        Log.e("TTT", "onFailure: "+t.getLocalizedMessage());
                        Toast.makeText(Add_Name.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}