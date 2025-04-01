package com.example.shijianshenapp.ui.index;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.ui.login.LoginActivity;

public class IndexActivity extends AppCompatActivity {

    @BindView(R.id.ai_iv_head)
    ImageView ivHead;

    private int time =3;
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    time--;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IndexActivity.this, LoginActivity.class));
                finish();
            }
        },3000);
    }
}