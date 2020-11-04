package com.example.catchtheamongus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.InsetDialogOnTouchListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    int score;
    ImageView red;
    ImageView yellow;
    ImageView cyan;
    ImageView black;
    ImageView pink;
    ImageView green;
    ImageView black2;
    ImageView red2;
    ImageView yellow2;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        red = findViewById(R.id.red);
        yellow = findViewById(R.id.yellow);
        cyan = findViewById(R.id.cyan);
        black = findViewById(R.id.black);
        pink = findViewById(R.id.pink);
        black2 = findViewById(R.id.black2);
        red2 = findViewById(R.id.red2);
        yellow2 = findViewById(R.id.yellow2);
        green = findViewById(R.id.green);
        imageArray = new ImageView[] {red,yellow,cyan,black,pink,green,black2,red2,yellow2};
        hideImages();
        score = 0;


        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time :" +millisUntilFinished/1000 );
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray ){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart ? ");
                alert.setMessage("Are you sure to Restart Game ?");
                alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();
            }
        }.start();

    }

    public void updateScore(View view){
        scoreText.setText("Score:" + score);
        score ++;

    }

     public void hideImages(){

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray ){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };

        handler.post(runnable);



    }

}