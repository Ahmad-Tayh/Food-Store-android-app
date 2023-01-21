package com.example.foodapp_2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp_2.R;
import com.google.android.material.textview.MaterialTextView;

public class IntroActivity extends AppCompatActivity {

    private ConstraintLayout startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        startBtn = (ConstraintLayout) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                }
            }
        );
    }
}