package com.example.user.groceryapp;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import java.util.LinkedHashMap;
public class DBConnection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "grocery.db";
    Context con;
    public DBConnection(Context context){
        super(context, DATABASE_NAME, null, 1);
        con = context;
        //context.deleteDatabase(DATABASE_NAME);
    }

    public void reset(){
        con.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table addproduct(productname text primary key,price text)");
        db.execSQL("create table addcoupon(uniqueid text primary key,coupon_no text,product_list text,total_amount text,discount text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS addproduct");
        db.execSQL("DROP TABLE IF EXISTS addcoupon");
        onCreate(db);
    }

    public ArrayList<ShopData> getShopList(double budget){
        ArrayList<String> dup = new ArrayList<String>();
        ArrayList<ShopData> list = new ArrayList<ShopData>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select product_list,total_amount,discount from addcoupon",null);
        while(res.moveToNext()) {
            String plist = res.getString(res.getColumnIndex("product_list"));
            String amt = res.getString(res.getColumnIndex("total_amount"));
            String discount = res.getString(res.getColumnIndex("discount"));
            double amount = Double.parseDouble(amt.substring(1, amt.length()));
            double dis = Double.parseDouble(discount.substring(1, discount.length()));
            if (amount <= budget && !dup.contains(plist)) {
                dup.add(plist);
                ShopData sd = new ShopData();
                sd.setProductList(plist);
                sd.setTotal(amount);
                sd.setDiscount(dis);
                list.add(sd);
            }
        }
        java.util.Collections.sort(list,new ShopData());
        return list;
    }

    public boolean updatePrice(String price,String products){
        String arr[] = products.split(",");
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0;i<arr.length;i++){
            String query = "UPDATE addproduct SET price = '"+price+"' WHERE productname = '"+arr[i]+"'";
            db.execSQL(query);
        }
        return true;
    }

    public boolean deleteCoupon(String uniqueid){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Delete from addcoupon WHERE uniqueid = '"+uniqueid+"'";
        db.execSQL(query);
        return true;
    }

    public boolean addProduct(String pname,String price){
        boolean flag = isProductExists(pname);
        if(!flag){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("productname",pname);
            contentValues.put("price",price);
            db.insert("addproduct", null, contentValues);
        }
        return flag;
    }

    public boolean isProductExists(String pname) {
        boolean flag = false;
        pname = pname.toLowerCase();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select productname from addproduct",null);
        while(res.moveToNext()) {
            String name = res.getString(res.getColumnIndex("productname"));
            name = name.toLowerCase();
            if (name.equals(pname)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public ArrayList<String> getProductList() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select productname from addproduct",null);
        while(res.moveToNext()) {
            String name = res.getString(res.getColumnIndex("productname"));
            list.add(name);
        }
        return list;
    }

    public ArrayList<String> getCoupons(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select uniqueid,coupon_no,product_list,total_amount,discount from addcoupon",null);
        while(res.moveToNext()) {
            String unique = res.getString(res.getColumnIndex("uniqueid"));
            String coupon = res.getString(res.getColumnIndex("coupon_no"));
            String plist = res.getString(res.getColumnIndex("product_list"));
            String amt = res.getString(res.getColumnIndex("total_amount"));
            String discount = res.getString(res.getColumnIndex("discount"));
            list.add(unique+"#"+coupon+"#"+plist+"#"+amt+"#"+discount);
        }
        return list;
    }

    public LinkedHashMap<String,String> checkCoupon(){
        LinkedHashMap<String,String> hm = new LinkedHashMap<String,String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select coupon_no,product_list from addcoupon",null);
        while(res.moveToNext()) {
            String coupon = res.getString(res.getColumnIndex("coupon_no"));
            String plist = res.getString(res.getColumnIndex("product_list"));
            hm.put(coupon,plist);
        }
        return hm;
    }

    public boolean addCoupon(String coupon,String plist,String discount){
        boolean flag = isCouponExists(coupon);
        if(!flag){
            String unique_id = getUniqueID();
            double amount = getTotalAmount(plist);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueid",unique_id);
            contentValues.put("coupon_no",coupon);
            contentValues.put("product_list",plist);
            contentValues.put("total_amount","$"+amount);
            contentValues.put("discount",discount);
            db.insert("addcoupon", null, contentValues);
        }
        return flag;
    }

    public boolean isCouponExists(String coupon) {
        boolean flag = false;
        coupon = coupon.toLowerCase();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select coupon_no from addcoupon",null);
        while(res.moveToNext()) {
            String name = res.getString(res.getColumnIndex("coupon_no"));
            name = name.toLowerCase();
            if (name.equals(coupon)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public String getUniqueID(){
        int id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select uniqueid from addcoupon",null);
        while(res.moveToNext()) {
            id = id + 1;
        }
        id = id + 1;
        return id+"";
    }

    public double getTotalAmount(String plist){
        double amount = 0;
        String arr[] = plist.split(",");
        for(int i=0;i<arr.length;i++){
            amount = amount + getPrice(arr[i]);
        }
        return amount;
    }

    public double getPrice(String product){
        double price = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select productname,price from addproduct",null);
        while(res.moveToNext()) {
            String name = res.getString(res.getColumnIndex("productname"));
            String value = res.getString(res.getColumnIndex("price"));
            if (name.equals(product)) {
                value = value.substring(1, value.length());
                price = Double.parseDouble(value.trim());
                break;
            }
        }
        return price;
    }
}
