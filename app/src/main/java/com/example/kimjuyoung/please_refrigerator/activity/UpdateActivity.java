package com.example.kimjuyoung.please_refrigerator.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.kimjuyoung.please_refrigerator.R;

import java.text.SimpleDateFormat;

/**
 * Created by Kim juyoung on 2017-12-12.
 */

public class UpdateActivity extends AppCompatActivity {

    CheckBox space; //check돼있는 상태 받아와서 그대로 체크 돼 있도록
    Spinner type;
    EditText name, date, amount, memo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        type = (Spinner)findViewById(R.id.spinner);
        name = (EditText)findViewById(R.id.editText);
        date = (EditText)findViewById(R.id.editText2);
        amount = (EditText)findViewById(R.id.editText3);
        memo = (EditText)findViewById(R.id.editText4);

    /*    name.setText("오징어");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date.setText(dateFormat.format("2018/2/4"));
        amount.setText("5");
        memo.setText("memo");*/
    }


}
