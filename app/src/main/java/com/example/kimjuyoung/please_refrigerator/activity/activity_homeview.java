package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.kimjuyoung.please_refrigerator.R;

/**
 * Created by pc187 on 2017-12-04.
 */

public class activity_homeview extends AppCompatActivity {

    String refrigerator = LoginActivity.login_ref;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.homeview);
        setTitle(refrigerator);

        ImageButton a = (ImageButton)findViewById(R.id.refri_icon);      //냉장고 화면이동
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton b = (ImageButton) findViewById(R.id.recipe_icon);     //쇼핑카트 목록이동
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton c = (ImageButton) findViewById(R.id.cart_icon);     //레시피 선택 이동
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void logout(View v){
        Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(logout);
        finish();
    }
}