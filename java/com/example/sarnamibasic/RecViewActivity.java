package com.example.sarnamibasic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecViewActivity extends AppCompatActivity {
    RecViewAdapter adapter;
    private RecyclerView listRecView;
    SharedPreferences shared;
    private int textSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent prevIntent = getIntent();
        String tableInfo = prevIntent.getStringExtra("tableId");
        String cardName = prevIntent.getStringExtra("cardName");
        getSupportActionBar().setTitle(cardName);

        listRecView = findViewById(R.id.listRecView);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDataBase();
        ArrayList<Translation> transList = databaseHelper.getAllData(tableInfo);
        databaseHelper.close();

        shared = getSharedPreferences("userPreferences", MODE_PRIVATE);
        textSize = shared.getInt("textSize", 18);
        adapter = new RecViewAdapter(this);
        adapter.setAudioTableId(tableInfo);
        adapter.setTranslation(transList);
        adapter.setTextSizeValue(textSize);
        listRecView.setAdapter(adapter);
        listRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quiz_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.quizMenuItem:
                Intent quizIntent = new Intent(this, QuizActivity.class);
                Intent prevIntent = getIntent();
                String tableInfo = prevIntent.getStringExtra("tableId");
                String quizName = prevIntent.getStringExtra("cardName");
                quizIntent.putExtra("quizName", quizName);
                quizIntent.putExtra("tableInfo", tableInfo);
                this.startActivity(quizIntent);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
