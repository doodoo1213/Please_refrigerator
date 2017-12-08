package com.example.kimjuyoung.please_refrigerator.activity;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.kimjuyoung.please_refrigerator.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);                 // using ButterKnife

        vp = (ViewPager)findViewById(R.id.vp);
        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.Tab_list);
        tabLayout.setupWithViewPager(vp);
        tabLayout.getTabAt(0).setIcon(R.drawable.refrigerated);
        tabLayout.getTabAt(1).setIcon(R.drawable.frozen);
        tabLayout.getTabAt(2).setIcon(R.drawable.etc);

    }

    private class pagerAdapter extends FragmentStatePagerAdapter
    {

        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    Show a = new Show();
                    a.setCategory(0);
                    return a;
                case 1:
                    Show b = new Show();
                    b.setCategory(1);
                    return b;
                case 2:
                    Show c = new Show();
                    c.setCategory(2);
                    return c;
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }
    @OnClick(R.id.input)
    public void Input(View v){//+버튼의 동작
        Intent input = new Intent(MainActivity.this, InputData.class);
        startActivity(input);
        finish();
    }

    @OnClick(R.id.home)
    public void Home(View v){
        Intent home = new Intent(MainActivity.this, activity_homeview.class);
        startActivity(home);
        finish();
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}



