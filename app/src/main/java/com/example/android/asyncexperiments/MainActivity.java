package com.example.android.asyncexperiments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rlMain;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlMain = findViewById(R.id.rlMain);
        tvStatus = findViewById(R.id.tvStatus);

        Button btnSlowForeground = findViewById(R.id.btnCountdown);
        btnSlowForeground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdownA();
            }
        });
    }

    void countdownA() {
        tvStatus.setText("Counting down...");
        for (int i = 5; i >= 0; i--) {
            tvStatus.setText(i);
        }
    }

    void countdownB() {
        tvStatus.setText("Counting down...");
        for (int i = 5; i >= 0; i--) {
            tvStatus.setText(String.valueOf(i));
        }
    }

    void countdownC() {
        tvStatus.setText("Counting down...");
        try {
            for (int i = 5; i >= 0; i--) {
                Thread.sleep(1000); // Wait 1 second
                tvStatus.setText(String.valueOf(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void countdownD() {
        tvStatus.setText("Counting down...");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            public void run() {
                try {
                    for (int i = 5; i >= 0; i--) {
                        Thread.sleep(1000); // Wait 1 second
                        tvStatus.setText(String.valueOf(i));
                    }
                } catch (
                        InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void countdownE() {
        tvStatus.setText("Counting down...");
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            public void run() {
                tvStatus.setText(tvStatus.getText() + ".");
            }
        };
        handler.postDelayed(runnable, 1000); // run in 1 second
    }

    void countdownF() {
        tvStatus.setText("Counting down...");
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            public void run() {
                tvStatus.setText(tvStatus.getText() + ".");
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000); // run in 1 second
    }

/*    void countdownG() {
        tvStatus.setText("Counting down...");
        Handler handler = new Handler(Looper.getMainLooper());
        int count = 5;
        Runnable runnable = new Runnable() {
            public void run() {
                if (count >= 0) {
                    tvStatus.setText(String.valueOf(count--));
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(runnable, 1000); // run in 1 second
    }*/

    class IntHolder {
        int i;

        IntHolder(int start) {
            i = start;
        }
    }

    void countdownH() {
        tvStatus.setText("Counting down...");
        Handler handler = new Handler(Looper.getMainLooper());
        IntHolder ih = new IntHolder(5);

        Runnable runnable = new Runnable() {
            public void run() {
                if (ih.i >= 0) {
                    tvStatus.setText(String.valueOf(ih.i--));
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(runnable, 1000); // run in 1 second
    }
}