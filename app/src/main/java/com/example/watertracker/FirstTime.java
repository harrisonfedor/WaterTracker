package com.example.watertracker;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FirstTime extends AppCompatActivity {
    Button quit;
    EditText age;
    EditText weight;
    EditText exercise;
    Button confirmButton;
    TextView confirmText;
    int goal;
    Intent i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        exercise = findViewById(R.id.exercise);
        quit = findViewById(R.id.quit);
        confirmButton = findViewById(R.id.confirmButton);
        confirmText =findViewById(R.id.confirmText);

       i = new Intent(this, MainActivity.class);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (age.getText().toString().matches("") || weight.getText().toString().matches("") ||
                        exercise.getText().toString().matches("") || (int) Math.floor(Double.parseDouble(exercise.getText().toString())) > 24
                || (int) Math.floor(Double.parseDouble(exercise.getText().toString())) < 0 || Integer.parseInt(age.getText().toString()) > 150)
                    Toast.makeText(getApplicationContext(), "Enter a valid age, weight, and number of hours in the day!", Toast.LENGTH_LONG).show();

                else {
                    int weightUse = Integer.parseInt(weight.getText().toString());
                    int exerciseUse = (int) Math.floor(Double.parseDouble(exercise.getText().toString()));
                    int ageUse = Integer.parseInt(age.getText().toString());
                    goal = (int) Math.ceil((((weightUse / 2.2) * 35) / 28.3) + ((12 * exerciseUse) + (ageUse*0.08)));
                    setExtra();
                    confirmButton.setVisibility(View.VISIBLE);
                    confirmText.setText("Your goal is " + goal + " oz of water per day!");
                }
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchClass();
            }
        });


    }

    public void setExtra(){
        i.putExtra("KEY_GOAL", goal);
    }

    public void switchClass(){
        finish();
        startActivity(i);
    }
}
