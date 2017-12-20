package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.R;
import com.example.kimjuyoung.please_refrigerator.models.Select;
import com.example.kimjuyoung.please_refrigerator.models.Select_info;


import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Kim juyoung on 2017-12-04.
 */

public class ListActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    ArrayList<Select_info> storage_list = new ArrayList<>();
    List_Adapter adapter;
    ListView listview ;

    String refrigerator = LoginActivity.login_ref;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_recipe);
        setTitle(refrigerator);
        ButterKnife.bind(this);

        // Adapter 생성
        adapter = new List_Adapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        input();

    }
    //list_ㅣtem추가
    public void input() {

        Select Info = new Select();
        storage_list = Info.get();

        for(int i = 0 ; i < storage_list.size() ; i ++){
            switch (storage_list.get(i).getSpace()){
                case "냉장":
                    adapter.addItem(ContextCompat.getDrawable(this,R.drawable.refrigerated), storage_list.get(i).getName(),storage_list.get(i).getLife());
                    break;
                case "냉동":
                    adapter.addItem(ContextCompat.getDrawable(this,R.drawable.frozen), storage_list.get(i).getName(),storage_list.get(i).getLife());
                    break;
                case "상온":
                    adapter.addItem(ContextCompat.getDrawable(this,R.drawable.etc), storage_list.get(i).getName(),storage_list.get(i).getLife());
            }
        }
       /* // 첫 번째 아이템 추가.R.drawable.frozen)
        adapter.addItem(ContextCompat.getDrawable(this, , "aaa", "aaa");
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.refrigerated), "bbb", "bb");
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.frozen), "ccc", "ccc");*/
    }

    @OnClick(R.id.recipe)
    public void recipe_button(View v){ //floating button 동작
        String ingredient = "" ;
        SparseBooleanArray checkedItems = listview.getCheckedItemPositions();//체크정보
        for(int i = 0 ; i < storage_list.size();i++){
            if(checkedItems.get(i)) {
                ingredient = ingredient + "+" + adapter.getItem(i).getName();
            }
        }
        Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cook.miznet.daum.net/search/search.do?q="+ingredient));
        url.setPackage("com.android.chrome");   // 브라우저가 여러개 인 경우 콕 찍어서 크롬을 지정할 경우
        startActivity(url);
    }

    @OnClick(R.id.return_home)
    public void return_home(View v){
        Intent home = new Intent(ListActivity.this, activity_homeview.class);
        startActivity(home);
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}