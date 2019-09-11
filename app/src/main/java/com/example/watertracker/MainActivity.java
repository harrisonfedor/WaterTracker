package com.example.watertracker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SettingsFragment.OnButtonsChangedListener, SettingsFragment.OnNewGoalListener{

    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    int notificationId = 0;
    String channelId = "notif";
    String please;
    int goal;
    int delay = 999999999;
    Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        createNotificationChannel();

        Boolean isFirstRun =
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            startActivity(new Intent(this, FirstTime.class));
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

        goal =  getIntent().getIntExtra("KEY_GOAL", 100);
        t = new Timer();

        mPager = findViewById(R.id.viewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new PrimaryFragment();
            else
                return new SettingsFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }


            }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = getString(R.string.notificationChannel);
            NotificationChannel channel = new NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onButtonSelected(int mDelay) {
        delay = mDelay;
        t.cancel();
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                    notificationCreator();
            }
        }, delay,delay);
    }

    public void notificationCreator(){
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, channelId)
                        .setSmallIcon(R.drawable.logo_backgroundless)
                        .setContentTitle("Water Tracker")
                        .setContentText("Stay hydrated! Come add water to your daily progress!")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificationId, builder.build());
            }
        });
    }

    @Override
    public void onGoalChanged(int mGoal, String changed){
        goal = mGoal;
        please = changed;
    }

    public int getGoal(){
        return goal;
    }
    public String getPlease(){
        return please;
    }
}