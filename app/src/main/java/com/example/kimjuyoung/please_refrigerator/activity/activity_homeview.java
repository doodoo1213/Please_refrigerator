package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kimjuyoung.please_refrigerator.R;

/**
 * Created by pc187 on 2017-12-04.
 */

public class activity_homeview extends AppCompatActivity {


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.homeview);

        Button a = findViewById(R.id.refri_icon);      //냉장고 화면이동
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button b = findViewById(R.id.recipe_icon);     //레시피선택 목록이동
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Intent intent = new Intent(getApplicationContext(), activity_homeview.class);
                startActivity(intent);
                finish();*/
            }
        });

        Button c = findViewById(R.id.cart_icon);     //쇼핑카트 이동
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}