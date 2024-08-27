package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button loginButton;
    EditText idEditText;
    EditText pwEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                        101);
            }
        }


        loginButton = findViewById(R.id.login_loginButton);
        idEditText = findViewById(R.id.login_id_edittext);
        pwEditText = findViewById(R.id.login_pw_edittext);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                String pw = pwEditText.getText().toString();
                if (id.matches("") || pw.matches("")){
                    Toast.makeText(MainActivity.this,"ID와 PW를 입력하세요.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,id+"님 안녕하세요.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainReportActivity.class);
                    startActivity(intent);

                    SharedPreferences sharedPreferences = getSharedPreferences("login", MainActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userId",id);
                    finish();
                }
            }
        });



    }
}