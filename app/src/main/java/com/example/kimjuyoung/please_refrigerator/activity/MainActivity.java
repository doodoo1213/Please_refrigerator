package com.example.kimjuyoung.please_refrigerator.activity;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.example.kimjuyoung.please_refrigerator.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
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
        tabLayout.addTab(tabLayout.newTab().setText("냉장"));
        tabLayout.setupWithViewPager(vp);

    }

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        private String[] tabTitles = new String[]{"냉장고", "냉동고", "상온"};

        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new Refrigerator();
                case 1:
                    return new Freezer();
                case 2:
                    return new Ordinary();
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
    @OnClick(R.id.btn_add)
    public void Input(View v){//+버튼의 동작
        Intent input = new Intent(MainActivity.this, InputData.class);
        startActivity(input);
        finish();
    }
}



