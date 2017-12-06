package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.example.kimjuyoung.please_refrigerator.R;

/**
 * Created by 이재연 on 2017-12-04.
 */

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox) findViewById(R.id.sel) ;

        return cb.isChecked() ;
    }

    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.sel) ;
        setChecked(cb.isChecked() ? false : true) ;
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox) findViewById(R.id.sel) ;

        if (cb.isChecked() != checked) {
            cb.setChecked(checked) ;
        }
    }
}