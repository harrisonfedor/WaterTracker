package com.example.watertracker;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 *
 */

public class SettingsFragment extends Fragment {
    RadioGroup group;
    RadioButton oneMinute;
    RadioButton oneHour;
    RadioButton threeHours;
    RadioButton sixHours;
    Switch notificationSwitch;
    int delay;
    Button save;
    EditText age;
    EditText weight;
    EditText exercise;
    String goalChange;
    int goal;


    OnButtonsChangedListener mOnButtonsChangedListener;
    OnNewGoalListener mOnNewGoalListener;

    public interface OnButtonsChangedListener {
        void onButtonSelected(int mDelay);
    }

    public interface  OnNewGoalListener{
        void onGoalChanged(int mGoal, String goalChange);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        super.onCreate(savedInstanceState);

        group = rootView.findViewById(R.id.group);
        oneMinute = rootView.findViewById(R.id.oneMinute);
        oneHour = rootView.findViewById(R.id.oneHour);
        threeHours = rootView.findViewById(R.id.threeHours);
        sixHours = rootView.findViewById(R.id.sixHours);
        notificationSwitch = rootView.findViewById(R.id.notificationSwitch);

        age = rootView.findViewById(R.id.ageSettings);
        weight = rootView.findViewById(R.id.weightSettings);
        exercise = rootView.findViewById(R.id.exerciseSettings);
        save = rootView.findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (age.getText().toString().matches("") || weight.getText().toString().matches("") ||
                        exercise.getText().toString().matches("") || (int) Math.floor(Double.parseDouble(exercise.getText().toString())) > 24
                        || (int) Math.floor(Double.parseDouble(exercise.getText().toString())) < 0 || Integer.parseInt(age.getText().toString()) > 150)
                    Toast.makeText(getContext(), "Enter a valid age, weight, and number of hours in the day!", Toast.LENGTH_LONG).show();

                else {
                    int weightUse = Integer.parseInt(weight.getText().toString());
                    int exerciseUse = (int) Math.floor(Double.parseDouble(exercise.getText().toString()));
                    int ageUse = Integer.parseInt(age.getText().toString());
                    goal = (int) Math.ceil((((weightUse / 2.2) * 35) / 28.3) + ((12 * exerciseUse) + (ageUse * 0.08)));
                    goalChange = "change";
                    mOnNewGoalListener.onGoalChanged(goal, goalChange);
                    goalChange = "";
                    age.setText("");
                    weight.setText("");
                    exercise.setText("");
                    Toast.makeText(getContext(), "Your new goal is " + goal + " oz of water per day!", Toast.LENGTH_LONG).show();
                }
            }
        });

        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    oneMinute.setChecked(false);
                    oneHour.setChecked(false);
                    threeHours.setChecked(false);
                    sixHours.setChecked(false);
                    oneMinute.setEnabled(false);
                    oneHour.setEnabled(false);
                    threeHours.setEnabled(false);
                    sixHours.setEnabled(false);
                } else {
                    oneMinute.setEnabled(true);
                    oneHour.setEnabled(true);
                    threeHours.setEnabled(true);
                    sixHours.setEnabled(true);
                }
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (oneMinute.isChecked())
                    delay = 60000;
                else if (oneHour.isChecked())
                    delay = 3600000;
                else if (threeHours.isChecked())
                    delay = 3600000 * 3;
                else if (sixHours.isChecked())
                    delay = 3600000 * 6;
                else
                    delay = 999999999;
                mOnButtonsChangedListener.onButtonSelected(delay);
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnButtonsChangedListener = (OnButtonsChangedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnButtonsChangedListener");
        }
        try {
            mOnNewGoalListener = (OnNewGoalListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnNewGoalListener");
        }
    }

}