package com.example.kimjuyoung.please_refrigerator.models;

/**
 * Created by Kim juyoung on 2017-12-06.
 */

public class Select_info {
    String name;
    String life;
    Integer amount;
    String memo;

    Select_info(String item_name, String item_life, int item_amount, String item_memo){
        name = item_name;
        life = item_life;
        amount = item_amount;
        memo = item_memo;
    }

    public String getName(){ return name; }

    public String getLife(){return life;}

    public Integer getAmount(){return amount;}

    public String getMemo(){return memo;}

}
