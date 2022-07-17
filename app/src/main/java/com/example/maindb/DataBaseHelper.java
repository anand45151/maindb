package com.example.maindb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String SNAME = "CUSTOMRE_NAME";
    public static final String AGE = "CUSTOMRE_AGE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable = "CREATE TABLE " + CUSTOMER_TABLE + "(" + SNAME + " TEXT," + AGE + ")";
        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SNAME,customerModel.getName());
        contentValues.put(AGE,customerModel.getAge());
       long insert= db.insert(CUSTOMER_TABLE,null,contentValues);
        if(insert==-1){
            return false;
        }else {
            return true;

        }

         }
         public boolean deleteOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FOM "+ CUSTOMER_TABLE + "WHERE " + SNAME + "=" + customerModel.getName();
             Cursor cursor = db.rawQuery(queryString, null);
             if(cursor.moveToFirst()){
                 return true;
             }
             else {
                 return false;
             }

         }
         public List<CustomerModel> getEveryone(){
        List<CustomerModel> returnlist = new ArrayList<>();
        String queryString = "select * from " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(queryString,null);
             if(cursor.moveToFirst()){
                 do{
                     String username = cursor.getString(0);
                     String age = cursor.getString(1);
                     CustomerModel newCustomer = new CustomerModel(username,age);
                     returnlist.add(newCustomer);
                 }while (cursor.moveToNext());
             }
             else {

             }
cursor.close();
             db.close();
             return returnlist;
         }
}
