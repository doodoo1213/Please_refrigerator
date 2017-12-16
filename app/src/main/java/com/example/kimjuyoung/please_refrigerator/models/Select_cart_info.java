package com.example.kimjuyoung.please_refrigerator.models;

/**
 * Created by Kim juyoung on 2017-12-16.
 */

public class Select_cart_info {
    String name;
    Integer amount;

    public Select_cart_info(String item_name, int item_amount ){
        name = item_name;
        amount = item_amount;
    }
    public String getName(){
        return name;
    }
    public Integer getAmount(){
        return amount;
    }
}
