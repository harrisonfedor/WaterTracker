package com.example.watertracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrimaryFragment extends Fragment {

    ImageButton drink;
    List<Integer> waterLevels;
    int current;
    int goal;
    int progress;
    int percentageFinal;
    int percentageInit;
    int amountToBeAdded;
    int count;
    int count2;
    int unchangedGoal;
    int tempGoal;
    String help;
    ImageButton plus;
    ImageButton minus;
    TextView amountToBeAddedView;
    TextView currentLevel;
    TextView goalLevel;
    MediaPlayer mMediaPlayer;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_primary, container, false);

        drink = rootView.findViewById(R.id.drink_button);
        count = 0;
        progress = 0;
        current = 0;
        amountToBeAdded = 8;
        plus = rootView.findViewById(R.id.plus);
        minus = rootView.findViewById(R.id.minus);
        amountToBeAddedView = rootView.findViewById(R.id.amountLabel);
        currentLevel = rootView.findViewById(R.id.currentView);
        goalLevel = rootView.findViewById(R.id.goalView);
        goal = ((MainActivity)getActivity()).getGoal();
        goalLevel.setText(goal+"");

        waterLevels = new ArrayList<>();
        waterLevels.add(R.drawable.zero);
        waterLevels.add(R.drawable.one);
        waterLevels.add(R.drawable.two);
        waterLevels.add(R.drawable.three);
        waterLevels.add(R.drawable.four);
        waterLevels.add(R.drawable.five);
        waterLevels.add(R.drawable.six);
        waterLevels.add(R.drawable.seven);
        waterLevels.add(R.drawable.eight);
        waterLevels.add(R.drawable.nine);
        waterLevels.add(R.drawable.ten);
        waterLevels.add(R.drawable.eleven);
        waterLevels.add(R.drawable.twelve);
        waterLevels.add(R.drawable.thirteen);
        waterLevels.add(R.drawable.fourteen);
        waterLevels.add(R.drawable.fifteen);
        waterLevels.add(R.drawable.sixteen);
        waterLevels.add(R.drawable.seventeen);
        waterLevels.add(R.drawable.eighteen);
        waterLevels.add(R.drawable.nineteen);
        waterLevels.add(R.drawable.twenty);
        waterLevels.add(R.drawable.twenty1);
        waterLevels.add(R.drawable.twenty2);
        waterLevels.add(R.drawable.twenty3);
        waterLevels.add(R.drawable.twenty4);
        waterLevels.add(R.drawable.twenty5);
        waterLevels.add(R.drawable.twenty6);
        waterLevels.add(R.drawable.twenty7);
        waterLevels.add(R.drawable.twenty8);
        waterLevels.add(R.drawable.twenty9);
        waterLevels.add(R.drawable.thirty);
        waterLevels.add(R.drawable.thirty1);
        waterLevels.add(R.drawable.thirty2);
        waterLevels.add(R.drawable.thirty3);
        waterLevels.add(R.drawable.thirty4);
        waterLevels.add(R.drawable.thirty5);
        waterLevels.add(R.drawable.thirty6);
        waterLevels.add(R.drawable.thirty7);
        waterLevels.add(R.drawable.thirty8);
        waterLevels.add(R.drawable.thirty9);
        waterLevels.add(R.drawable.forty);
        waterLevels.add(R.drawable.forty1);
        waterLevels.add(R.drawable.forty2);
        waterLevels.add(R.drawable.forty3);
        waterLevels.add(R.drawable.forty4);
        waterLevels.add(R.drawable.forty5);
        waterLevels.add(R.drawable.forty6);
        waterLevels.add(R.drawable.forty7);
        waterLevels.add(R.drawable.forty8);
        waterLevels.add(R.drawable.forty9);
        waterLevels.add(R.drawable.fifty);
        waterLevels.add(R.drawable.fifty1);
        waterLevels.add(R.drawable.fifty2);
        waterLevels.add(R.drawable.fifty3);
        waterLevels.add(R.drawable.fifty4);
        waterLevels.add(R.drawable.fifty5);
        waterLevels.add(R.drawable.fifty6);
        waterLevels.add(R.drawable.fifty7);
        waterLevels.add(R.drawable.fifty8);
        waterLevels.add(R.drawable.fifty9);
        waterLevels.add(R.drawable.sixty);
        waterLevels.add(R.drawable.sixty1);
        waterLevels.add(R.drawable.sixty2);
        waterLevels.add(R.drawable.sixty3);
        waterLevels.add(R.drawable.sixty4);
        waterLevels.add(R.drawable.sixty5);
        waterLevels.add(R.drawable.sixty6);
        waterLevels.add(R.drawable.sixty7);
        waterLevels.add(R.drawable.sixty8);
        waterLevels.add(R.drawable.sixty9);
        waterLevels.add(R.drawable.seventy);
        waterLevels.add(R.drawable.seventy1);
        waterLevels.add(R.drawable.seventy2);
        waterLevels.add(R.drawable.seventy3);
        waterLevels.add(R.drawable.seventy4);
        waterLevels.add(R.drawable.seventy5);
        waterLevels.add(R.drawable.seventy6);
        waterLevels.add(R.drawable.seventy7);
        waterLevels.add(R.drawable.seventy8);
        waterLevels.add(R.drawable.seventy9);
        waterLevels.add(R.drawable.eighty);
        waterLevels.add(R.drawable.eighty1);
        waterLevels.add(R.drawable.eighty2);
        waterLevels.add(R.drawable.eighty3);
        waterLevels.add(R.drawable.eighty4);
        waterLevels.add(R.drawable.eighty5);
        waterLevels.add(R.drawable.eighty6);
        waterLevels.add(R.drawable.eighty7);
        waterLevels.add(R.drawable.eighty8);
        waterLevels.add(R.drawable.eighty9);
        waterLevels.add(R.drawable.ninety);
        waterLevels.add(R.drawable.ninety1);
        waterLevels.add(R.drawable.ninety2);
        waterLevels.add(R.drawable.ninety3);
        waterLevels.add(R.drawable.ninety4);
        waterLevels.add(R.drawable.ninety5);
        waterLevels.add(R.drawable.ninety6);
        waterLevels.add(R.drawable.ninety7);
        waterLevels.add(R.drawable.ninety8);
        waterLevels.add(R.drawable.ninety9);
        waterLevels.add(R.drawable.hundred);

        checkGoal();

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        Boolean hasRun = preferences.getBoolean("hasRun", false);
        if (!hasRun) {
            editor.putInt("goal", goal);
            editor.putInt("progress",progress);
            editor.putInt("percentInit",percentageInit);
            editor.putBoolean("hasRun",true);
            editor.commit();
            preferences.edit().putBoolean("my_first_time", false).commit();
        }
        getPreferences();

        drink.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                percentageInit = (int) Math.floor(1.0 * current/goal*100);
                progress += amountToBeAdded;
                editor.putInt("progress",progress);
                editor.commit();
                percentageFinal = (int) Math.floor(1.0 * progress/goal*100);
                while (percentageInit <= percentageFinal) {
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    if(percentageInit <= 101 && percentageInit>-1)
                                    drink.setImageResource(waterLevels.get(percentageInit-1));

                                }
                            }, 1);
                    current++;
                    percentageInit = (int) Math.floor(1.0 * current/goal*100);
                    editor.putInt("percentInit",percentageInit);
                    editor.putInt("goal", goal);
                    editor.commit();
                }
                current = progress;
                currentLevel.setText(""+current);
                mMediaPlayer =  mMediaPlayer.create(getContext(),R.raw.drip);
                mMediaPlayer.start();
                MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                        if(progress>= goal && count==0){
                            mMediaPlayer = mMediaPlayer.create(getContext(),R.raw.completegoal);
                            mMediaPlayer.start();
                            count=1;
                        }

                    }
                };
                mMediaPlayer.setOnCompletionListener(listener);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (progress > goal)
                            drink.setImageResource(waterLevels.get(waterLevels.size() - 1));
                    }
                },100);
                return false;
            }

        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amountToBeAdded+1 > 36)
                    Toast.makeText(getContext(), "Don't drink that much!", Toast.LENGTH_SHORT).show();
                else {
                    amountToBeAdded++;
                    amountToBeAddedView.setText("" + amountToBeAdded);
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amountToBeAdded-1 < 1)
                    Toast.makeText(getContext(), "Can't add nothing!", Toast.LENGTH_SHORT).show();
                else {
                    amountToBeAdded--;
                    amountToBeAddedView.setText("" + amountToBeAdded);
                }
            }
        });

        return rootView;
    }

    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void checkGoal(){
        Timer t = new Timer();
        count2 = 0;
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (count2==0) {
                    Looper.prepare();
                    count2++;
                }
                unchangedGoal = goal;
                MainActivity act = (MainActivity) getActivity();
                tempGoal = act.getGoal();
                help = act.getPlease();
                if(unchangedGoal != tempGoal &&
                        help==("change")) {
                    goal = tempGoal;
                    help = "";
                    editor.putInt("goal", goal);
                    editor.commit();
                    unchangedGoal = tempGoal;
                    current = 0;
                    progress = 0;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            goalLevel.setText(""+goal);
                            drink.setImageResource(waterLevels.get(0));
                            currentLevel.setText(""+0);
                        }
                    });
                }

            }
        }, 1000,1000);
    }

    public void getPreferences(){
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        goal = preferences.getInt("goal",999);
        unchangedGoal = goal;
        goalLevel.setText(""+goal);
        progress = preferences.getInt("progress",1);
        current = progress;
        percentageInit = preferences.getInt("percentInit",50);
        currentLevel.setText(""+current);
                if(percentageInit > 100)
                    drink.setImageResource(waterLevels.get(waterLevels.size()-1));
                else
                    drink.setImageResource(waterLevels.get(percentageInit));
            }

}