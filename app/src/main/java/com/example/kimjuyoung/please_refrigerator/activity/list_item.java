package com.example.kimjuyoung.please_refrigerator.activity;

import android.graphics.drawable.Drawable;

/**
 * Created by 이재연 on 2017-12-04.
 */

public class list_item { //리스트 뷰 하나가 갖고 있을 data
    private String name ;
    private String date ;
    private Drawable iconDrawable ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setName(String title) {
        name = title ;
    }
    public void setDate(String desc) {
        date = desc ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getName() {
        return this.name;
    }
    public String getDate() {
        return this.date ;
    }
}