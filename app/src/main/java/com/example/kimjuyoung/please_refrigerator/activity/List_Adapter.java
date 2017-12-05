package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimjuyoung.please_refrigerator.R;

import java.util.ArrayList;

/**
 * Created by 이재연 on 2017-12-04.
 */

public class List_Adapter extends BaseAdapter{

    private ArrayList<list_item> listViewItemList = new ArrayList<list_item>(0) ;

    public List_Adapter() {
    }


    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.storage) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.item_name) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.item_date) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        list_item listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getName());
        descTextView.setText(listViewItem.getDate());

        return convertView;
    }

    public void addItem(Drawable icon, String name, String date) {
        list_item item = new list_item();

        item.setIcon(icon);
        item.setName(name);
        item.setDate(date);

        listViewItemList.add(item);
    }

}
