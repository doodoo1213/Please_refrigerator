package com.example.kimjuyoung.please_refrigerator.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.R;


import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Kim juyoung on 2017-12-04.
 */

public class ListActivity extends AppCompatActivity {

    List_Adapter adapter;
    ListView listview ;
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_recipe);
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
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.frozen), "aaa", "aaa");
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.refrigerated), "bbb", "bb");
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.frozen), "ccc", "ccc");
    }
    @OnClick(R.id.recipe)
    public void recipe_button(View v){
        String a = new String();
        SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
        if(checkedItems.get(0)==true)
            a = "1";
        if(checkedItems.get(1)==true)
            a = a + "2";
        if(checkedItems.get(2)==true)
            a= a+"3";
        Toast.makeText(this,a,Toast.LENGTH_LONG).show();
    }
}
