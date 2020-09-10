package com.e.and_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    int fragnum; // 화면전환 숫자
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = getWindow().getDecorView();  // 액티비티의 view 뷰 정보 가져오기

        if (Build.VERSION.SDK_INT >= 21) {
            //21 버전보다 낮으면 검은색 바탕
            getWindow().setStatusBarColor(Color.BLACK);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (view != null) {
                // 23 버전 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정

                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);  // 밝은 상태바 요청

                getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));

            }

        }


        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.battle_downbar:
                        fragnum=0;
                        setFrag(fragnum);
                        break;
                    case R.id.log_downbar:
                        fragnum=1;
                        setFrag(fragnum);
                        break;
                    case R.id.solang_downbar:
                        fragnum=2;
                        setFrag(fragnum);
                        break;
                    case R.id.analysis_downbar:
                        fragnum=3;
                        setFrag(fragnum);
                        break;
                }
                return true;
            }
        });
        fragnum=0;

        setFrag(fragnum); // 첫 프래그먼트 화면 지정


    }

    public void onClickListener(View view){
        switch (view.getId()) {
            case R.id.em_top:
                Intent intent = new Intent(MainActivity.this , battle_in.class);
                startActivityForResult(intent,100);
                break;
            case R.id.em_jg:
                fragnum = 6;
                setFrag(fragnum);
                break;
            case R.id.em_mid:
                fragnum = 7;
                setFrag(fragnum);
                break;
            case R.id.em_ad:
                fragnum = 8;
                setFrag(fragnum);
                break;
            case R.id.em_sup:
                fragnum = 9;
                setFrag(fragnum);
                break;
            case R.id.team_top:
                fragnum = 10;
                setFrag(fragnum);
                break;
            case R.id.team_jg:
                fragnum = 11;
                setFrag(fragnum);
                break;
            case R.id.team_mid:
                fragnum = 12;
                setFrag(fragnum);
                break;
            case R.id.team_ad:
                fragnum = 13;
                setFrag(fragnum);
                break;
            case R.id.team_sup:
                fragnum = 14;
                setFrag(fragnum);
                break;
        }
    }



    //프래그먼트 교체가 일어나는 실행문
    public void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                fragment = new battle_frag();
                ft.replace(R.id.main_frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 1:
                fragment = new log_frag();
                ft.replace(R.id.main_frame, fragment);
                ft.addToBackStack(null);   // 스택에 저장해서 뒤로 돌아갈수 있게 해주는것
                ft.commit();
                break;
            case 2:
                fragment = new rank_frag();
                ft.replace(R.id.main_frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 3:
                fragment = new analysis_frag();
                ft.replace(R.id.main_frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

        }
    }
}