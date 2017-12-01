package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.kimjuyoung.please_refrigerator.R;

import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_meat) LinearLayout meatList;
    @BindView(R.id.list_vegetable) LinearLayout vegetableList;
    @BindView(R.id.list_etc) LinearLayout etcList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);                 // using ButterKnife

        initList();
    }

    /**
     * List 생성 메소드
     */
    private void initList() {
        //버튼 추가
        Vector<Button> btn_meats = new Vector<>(0);
        int meat = 5; //음식종류가 '육류'인 튜플 수
        for (int i = 0; i < meat; i++) {// 원하는 수만큼 버튼 생성
            Button m = new Button(this);
            btn_meats.addElement(m);
            btn_meats.elementAt(i).setText("Beef");
            btn_meats.elementAt(i).setBackgroundResource(R.drawable.img_meat);
            meatList.addView(btn_meats.elementAt(i));
        }

        Vector<Button> btn_vegetables = new Vector<>(0);
        int vegetable = 3;//음식종류가 '야채'인 튜플 수
        for (int j = 0; j < vegetable; j++) {// 원하는 수만큼 버튼 생성
            Button v = new Button(this);
            btn_vegetables.addElement(v);
            btn_vegetables.elementAt(j).setText("");
            btn_vegetables.elementAt(j).setBackgroundResource(R.drawable.img_vegetable);
            vegetableList.addView(btn_vegetables.elementAt(j));
        }

        Vector<Button> btn_etcs = new Vector<>(0);
        int etc = 1;//음식종류가 '기타'인 튜플 수
        for (int k = 0; k < etc; k++) {// 원하는 수만큼 버튼 생성
            Button e = new Button(this);
            btn_etcs.addElement(e);
            btn_etcs.elementAt(k).setText("img_etc");
            btn_etcs.elementAt(k).setBackgroundResource(R.drawable.img_etc);
            etcList.addView(btn_etcs.elementAt(k));
        }
    }

    @OnClick(R.id.btn_add)
    public void Input(View v){//+버튼의 동작
        Intent input = new Intent(MainActivity.this, InputData.class);
        startActivity(input);
        finish();
    }
}
