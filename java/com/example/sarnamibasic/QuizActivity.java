package com.example.sarnamibasic;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    ArrayList<Translation> transList;
    private boolean answered;
    private int index, questionTotal, totalCorrect, ansOptionId;
    private String question, ansOption1, randOption2, randOption3, tableInfo;
    private ArrayList<String> answers;
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
    private RadioGroup rbGroup;
    private Button nextQuestion;
    private TextView txtQuestion, txtCountQuestion, txtCountCorrect;
    private MediaPlayer mp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent prevIntent = getIntent();
        String tableInfo = prevIntent.getStringExtra("tableInfo");
        String quizName = prevIntent.getStringExtra("quizName");
        setTableInfo(tableInfo);
        getSupportActionBar().setTitle(quizName + " Quiz");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDataBase();
        transList = databaseHelper.getAllData(tableInfo);
        databaseHelper.close();

        nextQuestion = findViewById(R.id.btnNext);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtCountQuestion = findViewById(R.id.txtCountQuestion);
        txtCountCorrect = findViewById(R.id.txtCountCorrect);
        rbGroup = findViewById(R.id.rbGroup);
        radioButtons.add((RadioButton)findViewById(R.id.rBtn1));
        radioButtons.add((RadioButton)findViewById(R.id.rBtn2));
        radioButtons.add((RadioButton)findViewById(R.id.rBtn3));

        Collections.shuffle(transList);
        index = 0;
        questionTotal = transList.size();
        totalCorrect = 0;
        txtCountCorrect.setText("Aantal goed: " + totalCorrect);

        showNextQuestion();

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered) {
                    if(radioButtons.get(0).isChecked()||radioButtons.get(1).isChecked()||radioButtons.get(2).isChecked()) {
                        checkAnswer(ansOption1, ansOptionId);
                        nextQuestion.setText("Volgende");
                    }
                    else {
                        Toast errorToast = Toast.makeText(QuizActivity.this, "Er is geen keuze gemaakt!\nKies een om verder te gaan", Toast.LENGTH_SHORT);
                        LinearLayout toastLayout = (LinearLayout) errorToast.getView();
                        TextView toastTxtView = (TextView) toastLayout.getChildAt(0);
                        errorToast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toastTxtView.setTextSize(20);
                        errorToast.show();
                    }
                }
                else {
                    showNextQuestion();
                    nextQuestion.setText("Check");
                }

            }
        });
    }

    private void showNextQuestion() {
        if(index < questionTotal) {
            txtCountQuestion.setText("Vraag " + (index+1) + " / " + questionTotal);
            question = transList.get(index).getNed();
            txtQuestion.setText(question);
            ansOption1 = transList.get(index).getSar();
            ansOptionId = transList.get(index).getId();
            if (index < questionTotal - 5) {
                randOption2 = transList.get(index + 2).getSar();
                randOption3 = transList.get(index + 4).getSar();
            } else {
                randOption2 = transList.get(index - 2).getSar();
                randOption3 = transList.get(index - 4).getSar();
            }

            answers = new ArrayList<String>() {{
                add(ansOption1);
                add(randOption2);
                add(randOption3);
            }};
            Collections.shuffle(answers);

            for (int i = 0; i < 3; i++) {
                radioButtons.get(i).setText(answers.get(i));
            }
            rbGroup.clearCheck();
            answered = false;
            index++;
        }
        else {
            QuizDialog dialog = new QuizDialog(this, txtCountCorrect.getText().toString(), questionTotal);
            dialog.show();
        }
    }

    private void checkAnswer(String answer, Integer answerId) {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        if(rbSelected.getText().equals(answer)) {
            totalCorrect++;
            txtCountCorrect.setText("Aantal goed: " + totalCorrect);
            Toast correctToast = Toast.makeText(this, "Correct", Toast.LENGTH_SHORT);
            LinearLayout toastLayout = (LinearLayout) correctToast.getView();
            TextView toastTxtView = (TextView) toastLayout.getChildAt(0);
            correctToast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
            toastTxtView.setTextSize(20);
            correctToast.show();
        }
        else {
            Toast incorrectToast = Toast.makeText(this, "Niet Correct!\nHet juiste antwoord was\n'" + ansOption1 + "'", Toast.LENGTH_LONG);
            LinearLayout toastLayout = (LinearLayout) incorrectToast.getView();
            TextView toastTxtView = (TextView) toastLayout.getChildAt(0);
            incorrectToast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
            toastTxtView.setTextSize(20);
            incorrectToast.show();
        }
        stopPlaying();
        mp = MediaPlayer.create(this,
                this.getResources().getIdentifier(getTableInfo() + answerId, "raw",
                        this.getPackageName()));
        mp.start();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void stopPlaying() {
        if(mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    private void setTableInfo(String tableInfo) {
        this.tableInfo = tableInfo;
    }

    private String getTableInfo() {
        return this.tableInfo;
    }

}
