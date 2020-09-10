package com.e.and_project;


import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class battle_in_MyAdapter extends BaseAdapter {

    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<battle_in_MyItem> mItems;
    Resources resources;

    public battle_in_MyAdapter(ArrayList<battle_in_MyItem>  items, Resources resources) {
        mItems = items;
        this.resources = resources;
    }



    public static class ViewHolder {
        ImageView img;
        TextView title;
    }

    ViewHolder holder;

    public ArrayList<battle_in_MyItem>  getmItems(){
        return mItems;
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public battle_in_MyItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    //ListView 가 각각의 list item 을 그릴때 Adapter에게 어떻게 그려야 할 지 묻는다. 그때 사용하는 함수가 getView()


    // LIstView에 보여지게 되는 데이터인 Voca 객체 리스트의 인덱스

    // 주어진 데이터를 보여주기 위해 사용될 한 줄(row)을 위한 뷰(View)
    // 값이 null인 경우에만 새로 생성하고 그 외에는 재사용됩니다.

    // XML 레이아웃 파일을 View 객체로 변환하기 위해 사용되는 부모 뷰그룹

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.battle_in_listview, parent, false);

            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.cham_img);
            holder.title = (TextView) convertView.findViewById(R.id.cham_title);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */




        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        battle_in_MyItem battleMyItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */

        holder.title.setText(battleMyItem.getName());

        InputStream inputStream = null;
        AssetManager assetManager = resources.getAssets();
        try {
            inputStream = assetManager.open(battleMyItem.getImg());
            holder.img.setImageDrawable(Drawable.createFromStream(inputStream, null));
        } catch (IOException e) {
            e.printStackTrace();
        } try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */

       //getFirebaseDatabase2();





        return convertView;
    }










}