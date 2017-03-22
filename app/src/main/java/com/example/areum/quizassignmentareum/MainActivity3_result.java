package com.example.areum.quizassignmentareum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.areum.quizassignmentareum.R.id.imageView;

public class MainActivity3_result extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_result);

        // passed value
        int myPassedResult = Integer.valueOf(getIntent().getStringExtra("MY_RESULT"));

        // result message
        TextView lblResultMessage = (TextView) findViewById(R.id.tvResultMessage);
        String resultMessage;

        // result image
        ImageView imagv = (ImageView) findViewById(R.id.imageView);
        Resources res = getResources();
        Drawable sadface = res.getDrawable(R.drawable.sadface);
        Drawable goodjob = res.getDrawable(R.drawable.goodjob);
        Drawable excellent = res.getDrawable(R.drawable.excellent);
        Drawable genius = res.getDrawable(R.drawable.genius);


        // set message and image depends on score
        if ((myPassedResult == 0) || (myPassedResult == 1) || (myPassedResult == 2) || (myPassedResult == 3)) {
            resultMessage = " You got " + myPassedResult + "/5 \n" + " Please, Try Again!";
            imagv.setImageDrawable(sadface);
        } else if (myPassedResult == 3) {
            resultMessage = " You got " + myPassedResult + "/5 \n" + " Good job!";
            imagv.setImageDrawable(goodjob);
        } else if (myPassedResult == 4) {
            resultMessage = " You got " + myPassedResult + "/5 \n" + " Excellent Work!";
            imagv.setImageDrawable(excellent);
        } else if (myPassedResult == 5) {
            resultMessage = " You got " + myPassedResult + "/5 \n" + " Your are a genius!";
            imagv.setImageDrawable(genius);
        } else {
            resultMessage = " ERROR!! ";
            imagv.setImageDrawable(sadface);
        }

        lblResultMessage.setText(resultMessage);

        // Try again button
        Button btnTryControl = (Button) findViewById(R.id.btnTry);
        btnTryControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3_result.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Exit button
        Button btnExitControl = (Button) findViewById(R.id.btnExit);
        btnExitControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                finish();
            }
        });


    }

}
