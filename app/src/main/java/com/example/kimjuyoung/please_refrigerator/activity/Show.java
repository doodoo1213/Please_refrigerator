package com.example.kimjuyoung.please_refrigerator.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.kimjuyoung.please_refrigerator.R;

import java.util.ArrayList;
import java.util.Vector;

import com.example.kimjuyoung.please_refrigerator.models.Select;
import com.example.kimjuyoung.please_refrigerator.models.Select_info;

public class Show extends Fragment implements View.OnClickListener{
    ArrayList<Food_info> meat_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> vege_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> etc_info = new ArrayList<Food_info>(0);

    LinearLayout meatList;
    LinearLayout vegetableList;
    LinearLayout etcList;

    ArrayList<Select_info> refrigerated_list = new ArrayList<>();

    int category;

    public void setCategory(int category){
        this.category = category;
    }
    public Show()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        refrigerated_list = new Select().get("냉장", "고기");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View layout = (View) inflater.inflate(R.layout.storage, container, false);
        meatList = (LinearLayout) layout.findViewById(R.id.list_meat);
        vegetableList = (LinearLayout) layout.findViewById(R.id.list_vegetable);
        etcList = (LinearLayout) layout.findViewById(R.id.list_etc);
        initList();
        return layout;
    }
    private void input_data(){

       // category0_meat_name = category0_meat.getName_list();

        for(int i=0; i<refrigerated_list.size();i++){
            //meat_info.add(new Food_info(refrigerated_list.getClass(), ""));
        }

            vege_info.add(new Food_info("토마토", "2015.01.01"));
            vege_info.add(new Food_info("양상추", "2017.12.06"));

        if(category == 1) {
            etc_info.add(new Food_info("계란", "2015.01.01"));
            etc_info.add(new Food_info("우유", "2015.01.02"));
            etc_info.add(new Food_info("주스", "2015.01.02"));
            etc_info.add(new Food_info("치즈", "2017.12.10"));
            etc_info.add(new Food_info("딸기잼", "2018.01.02"));
        }
        if(category == 2) {
            etc_info.add(new Food_info("egg4", "2015.01.02"));
            etc_info.add(new Food_info("egg5", "2015.01.02"));
        }
    }
    private void initList() {
        input_data();

        //버튼 추가
        Vector<Button> btn_meats = new Vector<>(0);
        for (int i = 0; i < meat_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_meats.addElement(new Button(getActivity()));
            btn_meats.elementAt(i).setText(meat_info.get(i).name);
            btn_meats.elementAt(i).setBackgroundResource(R.drawable.img_meat);
            btn_meats.elementAt(i).setTextSize(20);
            btn_meats.elementAt(i).setTypeface(Typeface.DEFAULT_BOLD);
            btn_meats.elementAt(i).setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
            meatList.addView(btn_meats.elementAt(i));
            btn_meats.elementAt(i).setId(100+i);
            btn_meats.elementAt(i).setOnClickListener(this);
        }

        Vector<Button> btn_vegetables = new Vector<>(0);
        for (int i = 0; i < vege_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_vegetables.addElement(new Button(getActivity()));
            btn_vegetables.elementAt(i).setText(vege_info.get(i).name);
            btn_vegetables.elementAt(i).setBackgroundResource(R.drawable.img_vegetable);
            btn_vegetables.elementAt(i).setTextSize(20);
            btn_vegetables.elementAt(i).setTypeface(Typeface.DEFAULT_BOLD);
            btn_vegetables.elementAt(i).setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
            vegetableList.addView(btn_vegetables.elementAt(i));
            btn_vegetables.elementAt(i).setId(200+i);
            btn_vegetables.elementAt(i).setOnClickListener(this);
        }

        Vector<Button> btn_etcs = new Vector<>(0);
        for (int i = 0; i < etc_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_etcs.addElement(new Button(getActivity()));
            btn_etcs.elementAt(i).setText(etc_info.get(i).name);
            btn_etcs.elementAt(i).setBackgroundResource(R.drawable.img_etc);
            btn_etcs.elementAt(i).setTextSize(20);
            btn_etcs.elementAt(i).setTypeface(Typeface.DEFAULT_BOLD);
            btn_etcs.elementAt(i).setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
            etcList.addView(btn_etcs.elementAt(i));
            btn_etcs.elementAt(i).setId(300+i);
            btn_etcs.elementAt(i).setOnClickListener(this);
        }
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

            final Context context = getView().getContext();
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
