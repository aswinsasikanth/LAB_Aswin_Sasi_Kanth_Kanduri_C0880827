package com.lab_aswinisasikanth_c0880827_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> retrievedList = getList();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(retrievedList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<String> retrievedList = getList();
                List<String> retrievedLocation = getList2();

                ArrayList<String> newItems = new ArrayList<>(retrievedLocation);
                ArrayList<String> newItems2 = new ArrayList<>(retrievedList);
                newItems.remove(position);
                newItems2.remove(position);
                retrievedLocation = newItems;
                retrievedList=newItems2;
                adapter.notifyItemRemoved(position);

                adapter.notifyItemRangeRemoved(1, 8);
                adapter.notifyItemRangeInserted(1,8);

                saveList(retrievedList);
                saveList2(retrievedLocation);
                adapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

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
    public List<String> getList2() {

        String savedString = PreferenceManager.getDefaultSharedPreferences(FavouriteLocations.this).getString("My_SAVED_LIST2", "");
        List<String> list = new ArrayList<>();
        if (!savedString.isEmpty()) {
            list = Arrays.asList(savedString.split("__,__"));
        }
        return list;
    }

    public void saveList(List<String> list) {

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(FavouriteLocations.this).edit();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s).append("__,__");
        }
        editor.putString("My_SAVED_LIST", stringBuilder.toString());
        editor.apply();

    }

    public void saveList2(List<String> list) {

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(FavouriteLocations.this).edit();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s).append("__,__");
        }
        editor.putString("My_SAVED_LIST2", stringBuilder.toString());
        editor.apply();

    }

}