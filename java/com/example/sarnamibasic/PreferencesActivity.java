package com.example.sarnamibasic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class PreferencesActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView txtSeekBarValue;
    private int valueSb;
    private RadioGroup rbGroupTheme;
    private RadioButton rbDarkTheme, rbLightTheme;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor userEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        sharedPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE);
        userEdit = sharedPreferences.edit();

        seekBar = findViewById(R.id.seekBar);
        txtSeekBarValue = findViewById(R.id.txtSeekBar);
        rbGroupTheme = findViewById(R.id.rbGroupTheme);
        rbDarkTheme = findViewById(R.id.rbDarkMode);
        rbLightTheme = findViewById(R.id.rbLightMode);

        //rbGroupTheme.check(R.id.rbLightMode);
        rbGroupTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == rbDarkTheme.getId()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        seekBar.setProgress(sharedPreferences.getInt("textSize", 18));
        txtSeekBarValue.setText(String.valueOf(seekBar.getProgress()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtSeekBarValue.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                valueSb = seekBar.getProgress();
                userEdit.putInt("textSize", valueSb).commit();
            }
        });
    }
}

