package com.example.kimjuyoung.please_refrigerator.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.kimjuyoung.please_refrigerator.R;

import java.util.ArrayList;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 이재연 on 2017-12-02.
 */

public class Ordinary extends Fragment{

    ArrayList<Food_info> meat_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> vege_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> etc_info = new ArrayList<Food_info>(0);

    @BindView(R.id.list_meat) LinearLayout meatList;
    @BindView(R.id.list_vegetable) LinearLayout vegetableList;
    @BindView(R.id.list_etc) LinearLayout etcList;

    public Ordinary()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.storage,null);
        //initList();

        return view;
    }

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
    private void initList() {
        input_data();
        //버튼 추가
        Vector<Button> btn_meats = new Vector<>(0);
        for (int i = 0; i < meat_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_meats.addElement(new Button(this.getActivity()));
            btn_meats.elementAt(i).setText(meat_info.get(i).name);
            btn_meats.elementAt(i).setBackgroundResource(R.drawable.img_meat);
            meatList.addView(btn_meats.elementAt(i));
            btn_meats.elementAt(i).setId(100+i);
         //   btn_meats.elementAt(i).setOnClickListener(this);
        }

        Vector<Button> btn_vegetables = new Vector<>(0);
        for (int i = 0; i < vege_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_vegetables.addElement(new Button(this.getActivity()));
            btn_vegetables.elementAt(i).setText(vege_info.get(i).name);
            btn_vegetables.elementAt(i).setBackgroundResource(R.drawable.img_vegetable);
            vegetableList.addView(btn_vegetables.elementAt(i));
            btn_vegetables.elementAt(i).setId(200+i);
           // btn_vegetables.elementAt(i).setOnClickListener(this);
        }

        Vector<Button> btn_etcs = new Vector<>(0);
        for (int i = 0; i < etc_info.size(); i++) {// 원하는 수만큼 버튼 생성
            btn_etcs.addElement(new Button(this.getActivity()));
            btn_etcs.elementAt(i).setText(etc_info.get(i).name);
            btn_etcs.elementAt(i).setBackgroundResource(R.drawable.img_etc);
            etcList.addView(btn_etcs.elementAt(i));
            btn_etcs.elementAt(i).setId(300+i);
           // btn_etcs.elementAt(i).setOnClickListener(this);
        }
    }
}
