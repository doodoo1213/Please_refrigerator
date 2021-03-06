package com.example.kimjuyoung.please_refrigerator.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.example.kimjuyoung.please_refrigerator.models.Delete;

public class Show extends Fragment implements View.OnClickListener{
    ArrayList<Food_info> meat_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> vege_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> etc_info = new ArrayList<Food_info>(0);

    LinearLayout meatList;
    LinearLayout vegetableList;
    LinearLayout etcList;

    ArrayList<Select_info> storage_list = new ArrayList<>();
    Delete delete = new Delete();

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
        storage_list = new Select().get();
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
        if(category == 0) {
            for (int i = 0; i < storage_list.size(); i++) {
                if(storage_list.get(i).getSpace().equals("냉장")) {
                    switch (storage_list.get(i).getType()) {
                        case "고기":
                            meat_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                        case "채소":
                            vege_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                        case "기타":
                            etc_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                    }
                }
            }
        }

        if(category == 1) {
            for (int i = 0; i < storage_list.size(); i++) {
                if(storage_list.get(i).getSpace().equals("냉동")) {
                    switch (storage_list.get(i).getType()) {
                        case "고기":
                            meat_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                        case "채소":
                            vege_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                        case "기타":
                            etc_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                    }
                }
            }
        }

        if(category == 2) {
            for (int i = 0; i < storage_list.size(); i++) {
                if(storage_list.get(i).getSpace().equals("상온")) {
                    switch (storage_list.get(i).getType()) {
                        case "고기":
                            meat_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                        case "채소":
                            vege_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                        case "기타":
                            etc_info.add(new Food_info(storage_list.get(i).getName(), storage_list.get(i).getLife(), storage_list.get(i).getAmount(), storage_list.get(i).getMemo()));
                            break;
                    }
                }
            }
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
            case 1 : Show_info(meat_info.get(index),1);break;
            case 2 : Show_info(vege_info.get(index),2);break;
            case 3 : Show_info(etc_info.get(index),3);break;
        }
    }
        private void Show_info(final Food_info  food,int type){

            final Context context = getView().getContext();
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // 제목셋팅
            alertDialogBuilder.setTitle("정보확인");

            alertDialogBuilder
                    .setMessage("이름 : " + food.name + "\n유통기한 : " + food.exp + "\n수량 : " + food.amount + "\n메모 : " + food.memo)
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    // 다이얼로그를 취소한다
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("수정",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent update = new Intent(getActivity(), UpdateActivity.class);
                                        UpdateActivity.old_name = food.name;
                                        UpdateActivity.old_amount = food.amount;
                                        UpdateActivity.old_category = category;
                                        UpdateActivity.old_memo = food.memo;
                                        UpdateActivity.old_date = food.exp;
                                        startActivity(update);
                                    }
                            })
                    .setNeutralButton("삭제",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    if(category==0) {
                                        delete.delete_item("냉장", food.name, food.exp);
                                    }
                                    else if(category==1){
                                        delete.delete_item("냉동", food.name, food.exp);
                                    }
                                    else if(category==2){
                                        delete.delete_item("상온", food.name, food.exp);
                                    }
                                    Intent refresh = new Intent(getContext(), MainActivity.class);
                                    startActivity(refresh);
                                }
                            })
            ;
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
