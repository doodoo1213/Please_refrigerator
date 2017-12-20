package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.R;
import com.example.kimjuyoung.please_refrigerator.models.Update;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.OnClick;

/**
 * Created by Kim juyoung on 2017-12-12.
 */

public class UpdateActivity extends AppCompatActivity {

    CheckBox refrigerated, frozen, etc;
    Spinner type;
    EditText date, amount, memo;
    TextView name;
    //static int old_type;//육류/야채/기타, 1/2/3
    public static String old_name;
    static String old_date;
    static int old_amount;
    static String old_memo;
    static int old_category;//냉장/냉동/상온 , 1/2/3

    String space = "";
    String update_date;
    int num;
    String text = "";

    int count;

    String refrigerator = LoginActivity.login_ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setTitle(refrigerator);

        refrigerated = (CheckBox) findViewById(R.id.space1);
        frozen = (CheckBox) findViewById(R.id.space2);
        etc = (CheckBox) findViewById(R.id.space3);
        type = (Spinner) findViewById(R.id.type);
        name = (TextView) findViewById(R.id.name);
        date = (EditText) findViewById(R.id.editdate);
        amount = (EditText) findViewById(R.id.editamount);
        memo = (EditText) findViewById(R.id.editmemo);

        //넘겨받은 데이터는 미리 입력시켜둠
        name.setText(old_name);//이름
        date.setText(old_date);//날짜
        amount.setText(String.valueOf(old_amount));//양
        memo.setText(old_memo);//메모

        //저장소 체크박스
        if (old_category == 1) {
            refrigerated.setChecked(true);
            space = "냉장";
        }
        if (old_category == 2) {
            frozen.setChecked(true);
            space = "냉동";
        }
        if (old_category == 3) {
            etc.setChecked(true);
            space = "상온";
        }
        //타입 스피너 아직은 미완성
        //type.setGravity(2);

        refrigerated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checked(view);
            }
        });
        frozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checked(view);
            }
        });
        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checked(view);
            }
        });

    }

    public String Checked(View v){
        count=0;
        if (refrigerated.isChecked()){
            space = "냉장";
            count++;
        }
        if (frozen.isChecked()){
            space = "냉동";
            count++;
        }
        if (etc.isChecked()){
            space = "상온";
            count++;
        }
        return space;
    }

    @OnClick(R.id.update)
    public void btnupdate(View v){
        if(count==1) {
            update_date = date.getText().toString();
            num = Integer.parseInt(amount.getText().toString());
            text = memo.getText().toString();
            Update update = new Update();
            update.update_item(space, update_date, num, text);
            Intent update_return = new Intent(UpdateActivity.this, MainActivity.class);
            startActivity(update_return);
            finish();
        }
        else{
            Toast.makeText(UpdateActivity.this, "저장공간을 하나만 선택해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.returnhome)
    public void btnhome(View v){
        Intent returnhome = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(returnhome);
        finish();
    }
}
