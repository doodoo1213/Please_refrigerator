package com.example.kimjuyoung.please_refrigerator.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.kimjuyoung.please_refrigerator.R;

import java.util.ArrayList;


/**
 * Created by Kim juyoung on 2017-12-04.
 */

public class ListActivity extends AppCompatActivity {

    List_Adapter adapter;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_recipe);
        ListView listview ;

        // Adapter 생성
        adapter = new List_Adapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        input();

    }

    public void input(){
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.frozen),"aaa", "aaa") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.refrigerated), "bbb", "bb") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.frozen),"ccc", "ccc") ;

    }
}
