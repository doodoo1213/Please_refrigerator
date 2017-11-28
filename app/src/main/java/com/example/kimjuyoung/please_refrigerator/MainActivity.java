package com.example.kimjuyoung.please_refrigerator;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 추가
       Vector <Button> m_button = new Vector <Button> (0);
        LinearLayout m_list = (LinearLayout)findViewById(R.id.m_button_list);
        int meat=5; //음식종류가 '육류'인 튜플 수
        for(int i=0;i<meat;i++){// 원하는 수만큼 버튼 생성
            Button m =  new Button(this);
            m_button.addElement(m);
            m_button.elementAt(i).setText("Beef");
            m_button.elementAt(i).setBackgroundResource(R.drawable.meat);
            m_list.addView(m_button.elementAt(i));
        }
        Vector <Button> v_button = new Vector <Button> (0);
        LinearLayout v_list = (LinearLayout)findViewById(R.id.v_button_list);
        int vegetable=3;//음식종류가 '야채'인 튜플 수
        for(int j=0;j<vegetable;j++){// 원하는 수만큼 버튼 생성
            Button v =  new Button(this);
            v_button.addElement(v);
            v_button.elementAt(j).setText("Vegetable");
            v_button.elementAt(j).setBackgroundResource(R.drawable.vegetable);
            v_list.addView(v_button.elementAt(j));
        }
       Vector <Button> e_button = new Vector <Button> (0);
        LinearLayout e_list = (LinearLayout)findViewById(R.id.e_button_list);
        int etc=1;//음식종류가 '기타'인 튜플 수
        for(int k=0;k<etc;k++){// 원하는 수만큼 버튼 생성
            Button e =  new Button(this);
            e_button.addElement(e);
            e_button.elementAt(k).setText("etc");
            e_button.elementAt(k).setBackgroundResource(R.drawable.etc);
            e_list.addView(e_button.elementAt(k));
        }

    }

    public void Input(View v){//+버튼의 동작
        Intent input = new Intent(MainActivity.this, InputData.class);
        startActivity(input);
        finish();
    }
}
