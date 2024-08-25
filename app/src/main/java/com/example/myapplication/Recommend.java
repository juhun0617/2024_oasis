package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Recommend extends AppCompatActivity {

    ConstraintLayout recommend1,recommend2,recommend3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recommend);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recommend1 = findViewById(R.id.recommend_1);
        recommend2 = findViewById(R.id.recommend_2);
        recommend3 = findViewById(R.id.recommend_3);

        AnimatorSet set1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.slide_left_fade);
        AnimatorSet set2 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.slide_left_fade);
        AnimatorSet set3 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.slide_left_fade);

        set1.setTarget(recommend1);
        set2.setTarget(recommend2);
        set3.setTarget(recommend3);

        recommend2.setVisibility(View.INVISIBLE);
        recommend3.setVisibility(View.INVISIBLE);

        set1.start();

        set1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation){
                recommend2.setVisibility(View.VISIBLE);
                set2.start();
                set2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        recommend3.setVisibility(View.VISIBLE);
                        set3.start();
                    }
                });
            }
        });

    }
}