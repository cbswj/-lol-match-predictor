package com.e.and_project;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class battle_in extends AppCompatActivity {


    String[] list;
    ArrayList<battle_in_MyItem> myitem;

    public ListView mListView;
    public battle_in_MyAdapter mCourseMyAdapter;

    int imgPosition;


    EditText search_edit;
    TextView toolbar_title;
    ArrayList<battle_in_MyItem> copy;   //검색에 필요한 부분


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_in);
        mListView = (ListView) findViewById(R.id.listview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        battle_in activity = this;
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        search_edit = (EditText) findViewById(R.id.search_text);
        toolbar_title = (TextView) findViewById(R.id.trevel_toolbar_title);


        Intent intent = getIntent();
        imgPosition = intent.getIntExtra("tag",999);

        try {
            AssetManager assetMgr = getResources().getAssets();
            list = assetMgr.list("");
            // TODO:

        } catch (IOException e) {
            e.printStackTrace();
        }

        myitem = new ArrayList<>();
        copy = new ArrayList<>();


        // 챔피언 등록
        for(int i=0; i<150; i++){
            String st = list[i].replace("cham","");
            st = st.replace(".jpg","");
            myitem.add(new battle_in_MyItem(list[i],cham_name[Integer.valueOf(st)]));
        }

        copy.addAll(myitem);

        mCourseMyAdapter = new battle_in_MyAdapter(myitem, getResources());
        mListView.setAdapter(mCourseMyAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                myitem.get(position);
                intent.putExtra("img", myitem.get(position).getImg());
                intent.putExtra("tag",imgPosition);
                intent.putExtra("name",myitem.get(position).getName());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = search_edit.getText().toString();
                search(text);
            }
        });


    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        myitem.clear();
        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (TextUtils.isEmpty(charText)) {
            myitem.addAll(copy);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < copy.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (copy.get(i).getName().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    myitem.add(copy.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        mCourseMyAdapter.notifyDataSetChanged();
    }

    public void onResume() {
        //// 모든 메뉴 파괴하고 onCreateOptionsMenu를 다시 부른다.
        super.onResume();
        invalidateOptionsMenu();
    }

    //메뉴 만들어 주는곳
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.battle_in_menu,menu);

        return true;
    }




    // 메뉴 동작 설정하는 곳
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            // 뒤로가기 기능
            case android.R.id.home:
                this.onBackPressed();
                break;
            case R.id.search:
                search_edit.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);
                search_edit.requestFocus();
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE); // 키보드 이벤트를 위한 선언 , 버튼 누르면 키보드 내려가게해줌
                imm.showSoftInput(search_edit, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    String cham_name[]={"garen", "galio","gangplank", "gragas"
            ,"graves"
            ,"gnar"
            ,"nami"
            ,"nasus"
            ,"nautilus"
            ,"nocturne"
            ,"nunu"
            ,"nidalee"
            ,"neeko"
            ,"darius"
            ,"diana"
            ,"draven"
            ,"ryze"
            ,"rakan"
            ,"rammus"
            ,"lux"
            ,"rumble"
            ,"renekton"
            ,"leona"
            ,"reksai"
            ,"rengar"
            ,"lucian"
            ,"lulu"
            ,"leblanc"
            ,"leesin"
            ,"riven"
            ,"lissandra"
            ,"lillia"
            ,"masteryi"
            ,"maokai"
            ,"malzahar"
            ,"malphite"
            ,"mordekaiser"
            ,"morgana"
            ,"drmundo"
            ,"missfortune"
            ,"bard"
            ,"varus"
            ,"vi"
            ,"veigar"
            ,"vayne"
            ,"velkoz"
            ,"volibear"
            ,"braum"
            ,"brand"
            ,"vladimir"
            ,"blitzcrank"
            ,"viktor"
            ,"poppy"
            ,"sion"
            ,"sylas"
            ,"shaco"
            ,"senna"
            ,"sejuani"
            ,"sett"
            ,"sona"
            ,"soraka"
            ,"shen"
            ,"shyvana"
            ,"swain"
            ,"skarner"
            ,"sivir"
            ,"xinzhao"
            ,"syndra"
            ,"singed"
            ,"thresh"
            ,"ahri"
            ,"amumu"
            ,"aurelionsol"
            ,"ivern"
            ,"azir"
            ,"akali"
            ,"aatrox"
            ,"aphelios"
            ,"alistar"
            ,"annie"
            ,"anivia"
            ,"ashe"
            ,"yasuo"
            ,"ekko"
            ,"elise"
            ,"monkeyking"
            ,"ornn"
            ,"orianna"
            ,"olaf"
            ,"yone"
            ,"yorick"
            ,"udyr"
            ,"urgot"
            ,"warwick"
            ,"yuumi"
            ,"irelia"
            ,"evelynn"
            ,"ezreal"
            ,"illaoi"
            ,"jarvaniv"
            ,"xayah"
            ,"zyra"
            ,"zac"
            ,"janna"
            ,"jax"
            ,"zed"
            ,"xerath"
            ,"jayce"
            ,"zoe"
            ,"ziggs"
            ,"jhin"
            ,"zilean"
            ,"jinx"
            ,"chogath"
            ,"karma"
            ,"camille"
            ,"kassadin"
            ,"karthus"
            ,"cassiopeia"
            ,"kaisa"
            ,"khazix"
            ,"katarina"
            ,"kalista"
            ,"kennen"
            ,"caitlyn"
            ,"kayn"
            ,"kayle"
            ,"kogmaw"
            ,"corki"
            ,"quinn"
            ,"kled"
            ,"qiyana"
            ,"kindred"
            ,"taric"
            ,"talon"
            ,"taliyah"
            ,"tahmkench"
            ,"trundle"
            ,"tristana"
            ,"tryndamere"
            ,"twistedfate"
            ,"twitch"
            ,"teemo"
            ,"pyke"
            ,"pantheon"
            ,"fiddlesticks"
            ,"fiora"
            ,"fizz"
            ,"heimerdinger"
            ,"hecarim"};
}