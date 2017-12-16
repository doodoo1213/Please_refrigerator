package com.example.kimjuyoung.please_refrigerator.activity;

/**
 * Created by Kim juyoung on 2017-12-16.
 */

public class Cartlist_item {

    private String name ;
    private Integer amount ;

    public void setName(String title) {
        name = title ;
    }
    public void setAmount(Integer desc) {
        amount = desc ;
    }

    public String getName() { return this.name;  }
    public Integer getAmount() { return this.amount; }
}
