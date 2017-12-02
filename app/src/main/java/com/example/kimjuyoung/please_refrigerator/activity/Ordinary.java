package com.example.kimjuyoung.please_refrigerator.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.kimjuyoung.please_refrigerator.R;

/**
 * Created by 이재연 on 2017-12-02.
 */

public class Ordinary extends Fragment{
    public Ordinary()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.storage, container, false);
        return layout;
    }
}
