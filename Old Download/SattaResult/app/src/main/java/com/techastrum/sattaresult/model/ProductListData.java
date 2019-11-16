package com.techastrum.sattaresult.model;

/**
 * Created by Tanuj yadav on 17/03/2018.
 */

public class ProductListData {
    private String Name,Number;

    public ProductListData(String name, String number) {
        Name = name;
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
