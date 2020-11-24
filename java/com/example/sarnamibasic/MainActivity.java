package com.example.sarnamibasic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsInflater = getMenuInflater();
        optionsInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuPrefItem:
                Intent prefIntent = new Intent(this, PreferencesActivity.class);
                this.startActivity(prefIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}