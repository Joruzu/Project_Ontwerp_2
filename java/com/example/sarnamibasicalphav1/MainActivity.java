package com.example.sarnamibasicalphav1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mainRecView;
    private static ArrayList<TableInfo> tableInfo = new ArrayList<TableInfo>() {
        {
            add(new TableInfo("groeten", "Groeten"));
            add(new TableInfo("introducties", "Introducties"));
            add(new TableInfo("uitdrukkingen", "Uitdrukkingen"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.createDataBase();
        }
        catch (Exception e){
            throw new Error("Error copying");
        }

        mainRecView = findViewById(R.id.mainRecView);
        MainViewAdapter adapter = new MainViewAdapter(this);
        adapter.setTableInfo(tableInfo);
        mainRecView.setAdapter(adapter);
        mainRecView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}