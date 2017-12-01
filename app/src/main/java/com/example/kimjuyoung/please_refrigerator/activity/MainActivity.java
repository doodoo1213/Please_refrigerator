package com.example.kimjuyoung.please_refrigerator.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.kimjuyoung.please_refrigerator.R;

import java.util.ArrayList;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class Food_info{ //음식정보 저장용
    String name;
    String exp;

    Food_info(String name_in,String exp_in){
        name = name_in;
        exp = exp_in;
    }
}

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    ArrayList<Food_info> meat_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> vege_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> etc_info = new ArrayList<Food_info>(0);

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
    //input data
    private void input_data(){
        meat_info.add(new Food_info("Beef", "2015.01.01"));
        meat_info.add(new Food_info("Meat", "2015.01.02"));

        vege_info.add(new Food_info("onion", "2015.01.01"));

        etc_info.add(new Food_info("egg", "2015.01.01"));
        etc_info.add(new Food_info("egg2", "2015.01.02"));
        etc_info.add(new Food_info("egg3", "2015.01.02"));
        etc_info.add(new Food_info("egg4", "2015.01.02"));
        etc_info.add(new Food_info("egg5", "2015.01.02"));
    }


    /**
     * List 생성 메소드
     */
    private void initList() {
        input_data();
        //버튼 추가
        Vector<Button> btn_meats = new Vector<>(0);
        for (int i = 0; i < meat_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_meats.addElement(new Button(this));
            btn_meats.elementAt(i).setText(meat_info.get(i).name);
            btn_meats.elementAt(i).setBackgroundResource(R.drawable.img_meat);
            meatList.addView(btn_meats.elementAt(i));
            btn_meats.elementAt(i).setId(100+i);
            btn_meats.elementAt(i).setOnClickListener(this);
        }

        Vector<Button> btn_vegetables = new Vector<>(0);
        for (int i = 0; i < vege_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_vegetables.addElement(new Button(this));
            btn_vegetables.elementAt(i).setText(vege_info.get(i).name);
            btn_vegetables.elementAt(i).setBackgroundResource(R.drawable.img_vegetable);
            vegetableList.addView(btn_vegetables.elementAt(i));
            btn_vegetables.elementAt(i).setId(200+i);
            btn_vegetables.elementAt(i).setOnClickListener(this);
        }

        Vector<Button> btn_etcs = new Vector<>(0);
        for (int i = 0; i < etc_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_etcs.addElement(new Button(this));
            btn_etcs.elementAt(i).setText(etc_info.get(i).name);
            btn_etcs.elementAt(i).setBackgroundResource(R.drawable.img_etc);
            etcList.addView(btn_etcs.elementAt(i));
            btn_etcs.elementAt(i).setId(300+i);
            btn_etcs.elementAt(i).setOnClickListener(this);
        }
    }

    @OnClick(R.id.btn_add)
    public void Input(View v){//+버튼의 동작
        Intent input = new Intent(MainActivity.this, InputData.class);
        startActivity(input);
        finish();
    }
    @SuppressLint("ResourceType")
    public void onClick(View v){

        int index;
        index = v.getId()%100; //버튼의 분류

        switch(v.getId()/100){ //category의 분류
            case 1 : Show_info(meat_info.get(index));break;
            case 2 : Show_info(vege_info.get(index));break;
            case 3 : Show_info(etc_info.get(index));break;
        }
    }

    private void Show_info(Food_info  food){

        final Context context = this;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // 제목셋팅
        alertDialogBuilder.setTitle("정보확인");

        alertDialogBuilder
                .setMessage("이름 : " + food.name + "\n유통기한 : " + food.exp)
                .setCancelable(false)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
