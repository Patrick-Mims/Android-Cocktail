package edu.sfsu.api_cocktail.asynctask;

import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
/*
 * Start a separate thread to execute a timed task
 * */
public class DataTimer {
    public void startTimer(TextView tv) {
        final long startMilliseconds = System.currentTimeMillis();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                long elapsedMilliseconds = System.currentTimeMillis() - startMilliseconds;
                updateView(elapsedMilliseconds, tv);
            }
        };

        Log.v("LOG", "Start Timer has been executed...");

        Timer timer = new Timer(true);
        // timer.schedule(timerTask, 0, 10000);
        timer.schedule(timerTask, 1000 * 60 * 60 * 2);
    }

    private void updateView(final long elapsedMilliseconds, TextView messageTextView) {
        messageTextView.post(new Runnable() {
            @Override
            public void run() {
                Log.v("LOG", "The View was just updated another: " + 1000 * 60 * 60 * 2);
                messageTextView.setText("Number of Seconds that have lapsed => " + elapsedMilliseconds);
            }
        });
    }
}