package com.example.kimjuyoung.please_refrigerator;

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
        ImageButton add_button;
        add_button = (ImageButton)findViewById(R.id.ADDBTN);
        add_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){//+버튼의 동작
                Toast.makeText(getApplicationContext(),"Alarm",Toast.LENGTH_LONG).show();
            }
        });
        //버튼 추가
       Vector <Button> m_button = new Vector <Button> (0);
        LinearLayout m_list = (LinearLayout)findViewById(R.id.m_button_list);
        int meat=5;
        for(int i=0;i<meat;i++){// 원하는 수만큼 버튼 생성
            Button m =  new Button(this);
            m_button.addElement(m);
            m_button.elementAt(i).setText("Beef");
            m_button.elementAt(i).setBackgroundResource(R.drawable.meat);
            m_list.addView(m_button.elementAt(i));
        }
        Vector <Button> v_button = new Vector <Button> (0);
        LinearLayout v_list = (LinearLayout)findViewById(R.id.v_button_list);
        int vegetable=3;
        for(int j=0;j<vegetable;j++){// 원하는 수만큼 버튼 생성
            Button v =  new Button(this);
            v_button.addElement(v);
            v_button.elementAt(j).setText("Vegetable");
            v_button.elementAt(j).setBackgroundResource(R.drawable.vegetable);
            v_list.addView(v_button.elementAt(j));
        }
       Vector <Button> e_button = new Vector <Button> (0);
        LinearLayout e_list = (LinearLayout)findViewById(R.id.e_button_list);
        int etc=1;
        for(int k=0;k<etc;k++){// 원하는 수만큼 버튼 생성
            Button e =  new Button(this);
            e_button.addElement(e);
            e_button.elementAt(k).setText("etc");
            e_button.elementAt(k).setBackgroundResource(R.drawable.etc);
            e_list.addView(e_button.elementAt(k));
        }

    }
}
