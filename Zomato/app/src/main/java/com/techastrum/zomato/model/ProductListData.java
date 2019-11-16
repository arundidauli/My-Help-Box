package com.techastrum.zomato.model;

/**
 * Created by Arun on 05/10/2019.
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
