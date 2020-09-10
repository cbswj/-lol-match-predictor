package com.e.and_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class battle_result_popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //상태바 제거 (전체화면 모드)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.battle_result_popup);

        ImageView img;

        img = (ImageView) findViewById(R.id.popup_content);

        Intent intent = getIntent();
        int result = intent.getIntExtra("result",9);

        if(result == 0){
            img.setImageResource(R.drawable.victory);
        }else if(result ==1){
            img.setImageResource(R.drawable.defeat);
        }else{

        }

    }
}