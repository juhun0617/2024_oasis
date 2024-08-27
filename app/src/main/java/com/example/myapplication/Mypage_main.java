package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Mypage_main extends AppCompatActivity {

    View mypage_1,mypage_2;

    ImageView imageView;
    private static final String CHANNEL_ID = "my_channel_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mypage_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mypage_1 = findViewById(R.id.mypage_1);
        mypage_2 = findViewById(R.id.mypage_2);
        imageView = findViewById(R.id.nav_menu);


        mypage_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage_main.this, Mypage_1.class);
                startActivity(intent);
            }
        });

        mypage_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage_main.this, Mypage_2.class);
                startActivity(intent);
            }
        });

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.logo);

        createNotificationChannel();

        Intent intent = new Intent(Mypage_main.this, Mypage_main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                Mypage_main.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(Mypage_main.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo_1)
                        .setContentTitle("사과나무 탄저병 주의 <경보 발생>")
                        .setContentText("고온다습한 기후로 인해 탄저병 발생률이 높아지고 있으니,\n사과나무 관리에 대해 더 주의하여 주십시오.")
                        .setAutoCancel(true)
                        .setLargeIcon(largeIcon)
                        .setShowWhen(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setFullScreenIntent(pendingIntent,true);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());

                System.out.println("noti");
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "Channel for my notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}