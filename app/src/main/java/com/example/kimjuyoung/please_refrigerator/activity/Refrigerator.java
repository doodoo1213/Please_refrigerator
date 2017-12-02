package com.example.kimjuyoung.please_refrigerator.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.kimjuyoung.please_refrigerator.R;

import java.util.ArrayList;

import butterknife.BindView;


public class Refrigerator extends Fragment{
    ArrayList<Food_info> meat_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> vege_info = new ArrayList<Food_info>(0);
    ArrayList<Food_info> etc_info = new ArrayList<Food_info>(0);

    @BindView(R.id.list_meat) LinearLayout meatList;
    @BindView(R.id.list_vegetable) LinearLayout vegetableList;
    @BindView(R.id.list_etc) LinearLayout etcList;

    public Refrigerator()
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
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.storage, container, false);
        return layout;
    }

}
