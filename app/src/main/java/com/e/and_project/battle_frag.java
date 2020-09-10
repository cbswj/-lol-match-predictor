package com.e.and_project;


import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


import android.os.Handler;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class battle_frag extends Fragment {

    private View view;

    CircleImageView profile_img;

    LinearLayout layout[];

    TextView title[];

    ImageView img[] , deep_vs;

    String  line[] = {"top","jg","mid","ad","sup","top","jg","mid","ad","sup"};

    String[] push_cham = new String[10];

    int layout_id, img_id , text_id;

    int count=0;

    int condition;


    /////////////////통신에 필요한것
    private String html = "";
    private Handler mHandler;

    private Socket socket;

    private BufferedReader networkReader;
    private PrintWriter networkWriter;

    private DataOutputStream dos;
    private DataInputStream dis;

    private String ip = "169.254.35.234";            // IP 번호
    private int port = 9999;

    ProgressDialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.battle_frag,container,false);



        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity)getActivity();


        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);

        deep_vs = (ImageView) view.findViewById(R.id.deep_vs);
        profile_img = (CircleImageView) view.findViewById(R.id.profile_img);

        profile_img.setImageResource(R.drawable.ari);

        layout = new LinearLayout[10];
        title = new TextView[10];
        img = new ImageView[10];

        Resources res = getResources();

        for(int i=0; i<=4; i++){
            layout_id = res.getIdentifier("em_"+line[i],"id",getClass().getPackage().getName());
            text_id = res.getIdentifier("em_"+line[i]+"_text","id",getClass().getPackage().getName());
            img_id = res.getIdentifier("em_"+line[i]+"_img","id",getClass().getPackage().getName());

            layout[i] = (LinearLayout) view.findViewById(layout_id);
            title[i] = (TextView) view.findViewById(text_id);
            img[i] = (ImageView) view.findViewById(img_id);

            layout[i].setTag(i);

            layout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), battle_in.class);
                    int tag = (int)v.getTag();
                    intent.putExtra("tag", tag);
                    startActivityForResult(intent,100);
                }
            });
        }

        for(int i=5; i<=9; i++){
            layout_id = res.getIdentifier("team_"+line[i],"id",getClass().getPackage().getName());
            text_id = res.getIdentifier("team_"+line[i]+"_text","id",getClass().getPackage().getName());
            img_id = res.getIdentifier("team_"+line[i]+"_img","id",getClass().getPackage().getName());

            layout[i] = (LinearLayout) view.findViewById(layout_id);
            title[i] = (TextView) view.findViewById(text_id);
            img[i] = (ImageView) view.findViewById(img_id);

            layout[i].setTag(i);

            layout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), battle_in.class);
                    int tag = (int)v.getTag();
                    intent.putExtra("tag", tag);
                    startActivityForResult(intent,200);
                }
            });
        }


        return view;
    }

    View.OnClickListener em_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent( getContext() , battle_in.class);
            startActivityForResult(intent,100);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            if(resultCode== RESULT_OK){
                InputStream inputStream = null;
                AssetManager assetManager = getActivity().getAssets();


                push_cham[count] = data.getStringExtra("name");

                try {
                    inputStream = assetManager.open(data.getStringExtra("img"));
                    int imgPosition = data.getIntExtra("tag",999);
                    img[imgPosition].setBackground(Drawable.createFromStream(inputStream, null));
                    img[imgPosition].setVisibility(View.VISIBLE);
                    title[imgPosition].setVisibility(View.GONE);
                    layout[imgPosition].setBackgroundResource(R.drawable.layout_border_em);
                } catch (IOException e) {
                    e.printStackTrace();
                } try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }else if(requestCode==200) {
            if (resultCode == RESULT_OK) {
                InputStream inputStream = null;
                AssetManager assetManager = getActivity().getAssets();

                push_cham[count] = data.getStringExtra("name");

                try {
                    inputStream = assetManager.open(data.getStringExtra("img"));
                    int imgPosition = data.getIntExtra("tag", 999);
                    img[imgPosition].setBackground(Drawable.createFromStream(inputStream, null));
                    img[imgPosition].setVisibility(View.VISIBLE);
                    title[imgPosition].setVisibility(View.GONE);
                    layout[imgPosition].setBackgroundResource(R.drawable.layout_border_team);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }

        if(count==10){

            for(int i=0; i <10; i++) {
                System.out.println(push_cham[i]+"\n");
               // Log.d("챔피언", "" + push_cham[i]);
            }

            deep_vs.setImageResource(R.drawable.vs);
            deep_vs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new ProgressDialog(getContext());
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("결과 분석 중");
                    dialog.show();// 프로그레스바 시작

                    connect();




                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.cancel();
                            Intent intent = new Intent( getContext() , battle_result_popup.class);
                            intent.putExtra("result",condition);
                            startActivity(intent);
                        }
                    }, 8000);  // 1 초 후에 실행

                }
            });
        }
    }

    void connect(){
        mHandler = new Handler();
        Log.w("connect","연결 하는중");
        // 받아오는거
        Thread checkUpdate = new Thread() {
            public void run() {
                // ip받기
                String newip = ip;

                // 서버 접속
                try {
                    socket = new Socket(newip, port);
                    Log.w("서버 접속됨", "서버 접속됨");
                } catch (IOException e1) {
                    Log.w("서버접속못함", "서버접속못함");
                    e1.printStackTrace();
                }

                Log.w("edit 넘어가야 할 값 : ","안드로이드에서 서버로 연결요청");

                // Buffered가 잘못된듯.
                try {
                    dos = new DataOutputStream(socket.getOutputStream());   // output에 보낼꺼 넣음
                    dis = new DataInputStream(socket.getInputStream());     // input에 받을꺼 넣어짐
                    dos.writeUTF("안드로이드에서 서버로 연결요청");

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w("버퍼", "버퍼생성 잘못됨");
                }
                Log.w("버퍼","버퍼생성 잘됨");

                try {
                    dos.writeUTF(","+push_cham[0]+","+push_cham[1]+","+push_cham[2]+","+push_cham[3]+","+push_cham[4]+","+push_cham[5]+","+push_cham[6]+","+push_cham[7]+","+push_cham[8]+","+push_cham[9]);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                while(true) {
                    // 서버에서 받아옴
                    try {
                        String line = "";
                        int line2;
                        while (true) {
                            //line = (String) dis.readUTF();
                            line2 = (int) dis.read();
                            condition = line2;
                            //Log.w("서버에서 받아온 값 ", "" + line);
                            //Log.w("서버에서 받아온 값 ", "" + line2);

                            if(line2 > 0) {
                                Log.w("------서버에서 받아온 값 ", "" + line2);

                                // 챔피언 보내기



                                //dos.writeUTF("하나 받았습니다. : " + line2);
                                socket.close();
                            }
                            if(line2 == 99) {
                                Log.w("------서버에서 받아온 값 ", "" + line2);
                                dialog.cancel();
                                socket.close();

                                break;
                            }
                        }
                    } catch (Exception e) {

                    }
                }

            }
        };
        // 소켓 접속 시도, 버퍼생성
        checkUpdate.start();
    }
}