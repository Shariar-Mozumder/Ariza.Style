package com.example.shariarspc.ariza_app.Cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME="CartProducts.db";
    public static final String TABLENAME="CartTable";
    public static final String PRODUCT_ID="_id";
    public static final String PRODUCT_NAME="name";
    public static final String PRODUCT_PRICE="price";
    public static final String PRODUCT_IMAGE="image";
    public static final String PRODUCT_QUANTITY="quantity";
    public static final int VERSION=4;
    public static final String CREATE_TABLE="CREATE TABLE "+TABLENAME+"("+PRODUCT_ID+" TEXT PRIMARY KEY,"+PRODUCT_NAME+" TEXT NOT NULL,"+PRODUCT_PRICE+" TEXT NOT NULL,"+PRODUCT_IMAGE+" TEXT,"+PRODUCT_QUANTITY+" INTEGER);";
    public static final String SELECT_ALL="SELECT * FROM ";
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLENAME;

    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertProduct(String name, String id,String price,String image,int quantity){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        Log.d("insert123", "insertProduct: "+image);

        ContentValues values=new ContentValues();
        values.put(PRODUCT_NAME,name);
        values.put(PRODUCT_ID,id);
        values.put(PRODUCT_PRICE,price);
        values.put(PRODUCT_IMAGE,image);
        values.put(PRODUCT_QUANTITY,quantity);

        sqLiteDatabase.insert(TABLENAME,null,values);

        return true;
    }

    public int insertQuantity(String pID,int quantity){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues quantityValue=new ContentValues();
        quantityValue.put(PRODUCT_QUANTITY,quantity);

        sqLiteDatabase.update(TABLENAME,quantityValue,PRODUCT_ID + " = ?",new String[]{pID});

        return quantity;
    }

    public Cursor getCartProduct(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(SELECT_ALL+TABLENAME,null);

        return cursor;
    }

    public void delete(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLENAME, PRODUCT_ID + " = ?", new String[]{id});


    }

    public Cursor getTotalPrice(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT "+PRODUCT_PRICE+" FROM "+TABLENAME,null);

        return cursor;
    }
}
