package com.example.sarnamibasic;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class QuizDialog extends Dialog {
    private Activity activity;
    private Button back;
    private TextView dialogMsg;
    private int total;
    private String score;

    public QuizDialog(Activity a, String score, int total) {
        super(a);
        this.activity = a;
        this.score = score;
        this.total = total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.quiz_end_dialog);
        back = findViewById(R.id.btnTerug);
        dialogMsg = findViewById(R.id.txtQuizDialog);
        dialogMsg.setText("        Quiz Voltooid! Gefeliciteerd!        \n" + score + " / " + total);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });



    }
}
