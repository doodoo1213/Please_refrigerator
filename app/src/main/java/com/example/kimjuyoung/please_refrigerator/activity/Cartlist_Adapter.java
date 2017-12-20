package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.kimjuyoung.please_refrigerator.R;

import java.util.ArrayList;

/**
 * Created by Kim juyoung on 2017-12-16.
 */

public class Cartlist_Adapter extends BaseAdapter{
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Cartlist_item> cartlist_view = new ArrayList<Cartlist_item>() ;

    // ListViewAdapter의 생성자
    public Cartlist_Adapter() {

    }
    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return cartlist_view.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.????
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cartlist_item, parent, false);
        }



        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final TextView textNameView = (TextView) convertView.findViewById(R.id.cart_itemname) ;
        final TextView textAmountView = (TextView) convertView.findViewById(R.id.cart_itemnumbers) ;
        Switch sw = (Switch) convertView.findViewById(R.id.checklist);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    textAmountView.setTextColor(Color.parseColor("#BDBDBD"));
                    textNameView.setTextColor(Color.parseColor("#BDBDBD"));

                }else{
                    textAmountView.setTextColor(Color.parseColor("#000000"));
                    textNameView.setTextColor(Color.parseColor("#000000"));

                }
            }
        });
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Cartlist_item cartlistviewpos = cartlist_view.get(pos);

        // 아이템 내 각 위젯에 데이터 반영
        textAmountView.setText(cartlistviewpos.getAmount().toString()); //int를 string으로
        textNameView.setText(cartlistviewpos.getName());
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return cartlist_view.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addcartItem(String Name, Integer Amount) {
        Cartlist_item item1 = new Cartlist_item();

        item1.setName(Name);
        item1.setAmount(Amount);

        cartlist_view.add(item1);
    }
}
