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

    CheckBox type_refrigerated, type_frozen, type_etc;
    Spinner type;
    EditText name, date, amount, memo;
    static int old_type;//육류/야채/기타, 1/2/3
    static String old_name;
    static String old_date;
    static int old_amount;
    static String old_memo;
    static int old_category;//냉장/냉동/상온 , 1/2/3

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        type_refrigerated = (CheckBox) findViewById(R.id.checkBox1);
        type_frozen = (CheckBox) findViewById(R.id.checkBox2);
        type_etc = (CheckBox) findViewById(R.id.checkBox3);
        type = (Spinner) findViewById(R.id.spinner);
        name = (EditText) findViewById(R.id.editText);
        date = (EditText) findViewById(R.id.editText2);
        amount = (EditText) findViewById(R.id.editText3);
        memo = (EditText) findViewById(R.id.editText4);

        //넘겨받은 데이터는 미리 입력시켜둠
        name.setText(old_name);//이름
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date.setText(dateFormat.format(old_date));//날짜
        amount.setText(old_amount);//양
        memo.setText(old_memo);//메모
        //저장소 체크박스
        if (old_category == 1) {
            type_refrigerated.setChecked(true);
        }
        if (old_category == 2) {
            type_frozen.setChecked(true);
        }
        if (old_category == 3) {
            type_etc.setChecked(true);
        }
        //타입 스피너 아직은 미완성
        type.setGravity(2);
    }
}
