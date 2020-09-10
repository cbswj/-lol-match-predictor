package com.e.and_project;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;

public class log_MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<log_MyItem> items = new ArrayList<log_MyItem>();

    Resources resources;



    public log_MyAdapter(ArrayList<log_MyItem> items, Resources resources) {
        addItems(items);
        this.resources = resources;
    }


    public class ChatMessageViewHolder extends RecyclerView.ViewHolder {  // 상대 메시지 뷰홀더 객체 재사용 하기 위해서 사용
        CircleImageView team_top;  CircleImageView team_jg;
        CircleImageView team_mid;  CircleImageView team_ad;  CircleImageView team_sup;
        CircleImageView em_top; CircleImageView em_jg; CircleImageView em_mid;
        CircleImageView em_ad; CircleImageView em_sup;


        public ChatMessageViewHolder(@NonNull final View itemView) {
            super(itemView);
            team_top = (CircleImageView) itemView.findViewById(R.id.team_top);
            team_jg = (CircleImageView) itemView.findViewById(R.id.team_jg);
            team_mid = (CircleImageView) itemView.findViewById(R.id.team_mid);
            team_ad = (CircleImageView) itemView.findViewById(R.id.team_ad);
            team_sup = (CircleImageView) itemView.findViewById(R.id.team_sup);




            itemView.setOnClickListener(new View.OnClickListener() {  // 이거는 프로필 사진 보이는 클릭
                @Override
                public void onClick(View v ) {

//                    final profile_friend_two_MyItem model = items.get(getAdapterPosition());
//
//                    Intent intent = new Intent(v.getContext(), chatroom_in_profile.class);
//                    intent.putExtra("NICKNAME","0");
//                   intent.putExtra("UID","O");
//                    intent.putExtra("Fnickname",model.getName());
//                    intent.putExtra("FUID",model.getFuid());
//                    intent.putExtra("FURL",model.getImg());
//                    intent.putExtra("URL","0") ;
//                    //  닉넴 URL 0 인이유 여기서  이거 클릭했다는건 이미 친구상태임
//                    // 저 클래스는 채팅창 내부에서도 쓰이는거라 거기선 친구인지 아닌지 몰라서 내 닉넴이랑 URL 필요하지만
//                    // 여기선 무적권 친구상태라 내 껀 필요업승ㅁ
//                   intent.putExtra("name","friend_two");  //클레스 어디서 눌렀나 구분
//
//                    // model getname,getfuid,getimg 이 셋 제외하고 나머지 데이터 피요없음
//                    //하지만 안보내주면 오류뜸 (클레스를 하나만 쓰기때문)
//
//                    v.getContext().startActivity(intent);
//                    // TODO : process click event.
                }
            });
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // 뷰타입을 구분해서 xml 선언
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_listview,parent, false);
        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {  // 뷰타입 구분해서 데이터를 입력해주는곳!
        final log_MyItem model = items.get(position);

        ChatMessageViewHolder holder1 = (ChatMessageViewHolder) holder;

        //Log.d("열었을때","3");

        AssetManager assetManager = resources.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(model.getTeam_top()+".jpg");
            holder1.team_top.setImageDrawable(Drawable.createFromStream(inputStream, null));
            inputStream = assetManager.open(model.getTeam_jg()+".jpg");
            holder1.team_jg.setImageDrawable(Drawable.createFromStream(inputStream, null));
            inputStream = assetManager.open(model.getTeam_mid()+".jpg");
            holder1.team_mid.setImageDrawable(Drawable.createFromStream(inputStream, null));
            inputStream = assetManager.open(model.getTeam_ad()+".jpg");
            holder1.team_ad.setImageDrawable(Drawable.createFromStream(inputStream, null));
            inputStream = assetManager.open(model.getTeam_sup()+".jpg");
            holder1.team_sup.setImageDrawable(Drawable.createFromStream(inputStream, null));


//            inputStream = assetManager.open(model.getTeam_mid());
//            inputStream = assetManager.open(model.getTeam_ad());
//            inputStream = assetManager.open(model.getTeam_sup());
//
//            inputStream = assetManager.open(model.getTeam_top());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //아이템을 추가해주고싶을때 이거쓰면됨
    public void addItem(log_MyItem item) {
        items.add(item);
    }

    //한꺼번에 추가해주고싶을떄
    public void addItems(ArrayList<log_MyItem> items) {
        this.items = items;
    }


    @Override
    public int getItemCount() {return items.size(); } // 현재 데이터 크기
}
