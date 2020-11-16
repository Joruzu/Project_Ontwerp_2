package com.example.sarnamibasicalphav1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecViewActivity extends AppCompatActivity {
    private RecyclerView listRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recview);
        Intent intent = getIntent();
        String tableInfo = intent.getStringExtra("tableId");

        listRecView = findViewById(R.id.listRecView);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDataBase();
        ArrayList<Translation> transList = databaseHelper.getAllData(tableInfo);
        databaseHelper.close();

        RecViewAdapter adapter = new RecViewAdapter(this);
        adapter.setAudioTableId(tableInfo);
        adapter.setTranslation(transList);
        listRecView.setAdapter(adapter);
        listRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}
