package com.example.kimjuyoung.please_refrigerator.activity;

/**
 * Created by 이재연 on 2017-12-02.
 */

public class Food_info {
    String name;
    String exp;
    int amount;
    String memo;

    Food_info(String name_in,String exp_in, int amount_in, String memo_in){
        name = name_in;
        exp = exp_in;
        amount = amount_in;
        memo = memo_in;
    }
}
