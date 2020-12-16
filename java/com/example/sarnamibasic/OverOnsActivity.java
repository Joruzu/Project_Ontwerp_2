package com.example.sarnamibasic;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class OverOnsActivity extends AppCompatActivity {
    private TextView resLink1, resLink2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_ons);
        getSupportActionBar().setTitle("Over Ons");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resLink1 = findViewById(R.id.txtResource1);
        resLink2 = findViewById(R.id.txtResource2);

        resLink1.setMovementMethod(LinkMovementMethod.getInstance());
        resLink2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
