package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainReportActivity extends AppCompatActivity {
    TextView temp;
    ConstraintLayout searchButton,weatherButton,announceButton,myPageButton;
    ImageView nav_community,nav_chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nav_community = findViewById(R.id.nav_cumu);
        searchButton = findViewById(R.id.search_button);
        weatherButton = findViewById(R.id.weather_button);
        announceButton = findViewById(R.id.announcement_button);
        myPageButton = findViewById(R.id.mycrop_button);
        nav_chat = findViewById(R.id.nav_chat);

        nav_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReportActivity.this, Community.class);
                startActivity(intent);
            }
        });

        nav_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainReportActivity.this,Chat.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainReportActivity.this, Search.class);
                startActivity(intent);
            }
        });

        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.weather.go.kr/w/index.do"));
                startActivity(urlintent);
            }
        });

        announceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainReportActivity.this,Announcement.class);
                startActivity(intent);
            }
        });

        myPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainReportActivity.this,Mypage_main.class);
                startActivity(intent);
            }
        });

    }
}