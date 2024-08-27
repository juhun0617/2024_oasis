package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChatDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.chat_edittext).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // EditText가 포커스를 얻었을 때, 해당 뷰를 키보드 위로 이동
                    findViewById(R.id.chat_edit).animate().translationY(-750).setDuration(300).start();  // 필요에 따라 위치 조정
                } else {
                    // 포커스가 해제되면 원래 위치로 복귀
                    findViewById(R.id.chat_edit).animate().translationY(0).setDuration(300).start();
                }
            }
        });

        EditText editText = findViewById(R.id.chat_edittext);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    // 엔터가 눌리면 포커스 해제
                    editText.clearFocus();
                    // 키보드 숨기기
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    }

                    findViewById(R.id.invisible_chat).setVisibility(View.VISIBLE);

                    return true;
                }
                return false;
            }});

        View view = findViewById(R.id.inter);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatDetail.this, Chat1.class);
                startActivity(intent);
            }
        });

    }
}