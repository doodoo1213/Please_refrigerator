package com.example.kimjuyoung.please_refrigerator.models;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by SELab on 2017-11-29.
 */

public class dump extends AppCompatActivity{

    ArrayList<String> name = new ArrayList<String>(0);
    ArrayList<String> date = new ArrayList<String>(0);

    public dump(String space){
        String DB_name= "asd";
        name.add(DB_name);
    }
    public ArrayList<String> getName_list(){
        return name;
    }


}
