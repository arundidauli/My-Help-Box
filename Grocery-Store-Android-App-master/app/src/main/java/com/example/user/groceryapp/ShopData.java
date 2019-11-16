package com.example.user.groceryapp;
import java.util.Comparator;
public class ShopData  implements Comparator<ShopData>{
    String plist;
    double discount,total;
    public void setProductList(String plist){
        this.plist = plist;
    }
    public String getProductList(){
        return plist;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }
    public double getDiscount(){
        return discount;
    }

    public void setTotal(double total){
        this.total = total;
    }
    public double getTotal(){
        return total;
    }

    public int compare(ShopData p1,ShopData p2){
        double s1 = p1.getDiscount();
        double s2 = p2.getDiscount();
        if (s1 == s2)
            return 0;
        else if (s1 > s2)
            return 1;
        else
            return -1;
    }
}
