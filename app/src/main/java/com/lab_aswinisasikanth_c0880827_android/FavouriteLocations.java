package com.lab_aswinisasikanth_c0880827_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavouriteLocations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_locations);
        ListView listView=findViewById(R.id.locationList);


        List<String> retrievedList = getList();



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                retrievedList );

        listView.setAdapter(arrayAdapter);



    }
    public List<String> getList() {
        String savedString = PreferenceManager.getDefaultSharedPreferences(FavouriteLocations.this).getString("My_SAVED_LIST", "");
        List<String> list = new ArrayList<>();
        if (!savedString.isEmpty()) {
            list = Arrays.asList(savedString.split("__,__"));
        }
        return list;
    }

    public void Add(View view) {
        startActivity(new Intent(FavouriteLocations.this,MainActivity.class));
    }
}