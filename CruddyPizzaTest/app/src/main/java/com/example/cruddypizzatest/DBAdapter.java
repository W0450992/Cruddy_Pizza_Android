package com.example.cruddypizzatest;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CUSTINFO = "info";
    //public static final String KEY_PHONE = "phone";
    //public static final String KEY_ADDRESS = "address";
    public static final String KEY_DATE = "date";
    //public static final String KEY_TIME = "time";
    public static final String KEY_SIZE = "size";
    public static final String KEY_GOOD1 = "cheese";
    public static final String KEY_GOOD2 = "pepperoni";
    public static final String KEY_GOOD3 = "olives";
    public static final String KEY_GOOD4 = "sausage";
    public static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "PizzaDB";
    private static final String DATABASE_TABLE = "orders";
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_CREATE =
            "create table orders(_id integer primary key autoincrement,"
                    + "info text not null,"
                    + "date text not null,size integer not null,"
                    + "cheese integer not null,pepperoni integer not null,olives integer not null, sausage integer not null);";
    private final DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    List<Pizza> orderList = new ArrayList<>();

    public DBAdapter(Context ctx) {
        DBHelper = new DatabaseHelper(ctx);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }//end method onCreate

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrade database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS orders");
            onCreate(db);
        }//end method onUpgrade
    }

    //open the database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //close the database
    public void close() {
        DBHelper.close();
    }
    //insert a contact into the database
    public long insertOrder(String custinfo, int size, int cheese, int pepp, int olive, int sausa)
    {

        LocalDate date1 = LocalDate.now();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CUSTINFO, custinfo);
        initialValues.put(KEY_SIZE, size);
        initialValues.put(KEY_GOOD1, cheese);
        initialValues.put(KEY_GOOD2, pepp);
        initialValues.put(KEY_GOOD3, olive);
        initialValues.put(KEY_GOOD4, sausa);
        initialValues.put(KEY_DATE, String.valueOf(date1));
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    //delete a particular contact
    public boolean deleteOrder(long rowId)
    {

        return db.delete(DATABASE_TABLE,KEY_ROWID + "=" + rowId,null) >0;
    }
    //retrieve all the contacts
    public Cursor getAllOrders()
    {
        return db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_CUSTINFO,
                KEY_SIZE, KEY_GOOD1, KEY_GOOD2, KEY_GOOD3, KEY_GOOD4, KEY_DATE},null,null,null,null,null);
    }

    public List<Pizza> getAllOrders1()
    {
        int counter= 0;
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ROWID,KEY_CUSTINFO,
            KEY_SIZE, KEY_GOOD1, KEY_GOOD2, KEY_GOOD3, KEY_GOOD4, KEY_DATE},
                null,null,null,null,null);
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(KEY_ROWID);
            int index2 = cursor.getColumnIndex(KEY_CUSTINFO);
            int index3 = cursor.getColumnIndex(KEY_SIZE);
            int index4 = cursor.getColumnIndex(KEY_GOOD1);
            int index5 = cursor.getColumnIndex(KEY_GOOD2);
            int index6 = cursor.getColumnIndex(KEY_GOOD3);
            int index7 = cursor.getColumnIndex(KEY_GOOD4);
            int index8 = cursor.getColumnIndex(KEY_DATE);
            int rowid = cursor.getInt(index1);
            String custInfo = cursor.getString(index2);
            int size = cursor.getInt(index3);
            int cheese = cursor.getInt(index4);
            int pepp = cursor.getInt(index5);
            int olive = cursor.getInt(index6);
            int sausa = cursor.getInt(index7);
            String date = cursor.getString(index8);
            Pizza order = new Pizza(rowid, custInfo,size,cheese,pepp,olive, sausa,date);
            counter++;
            orderList.add(order);
        }
        return orderList;

    }
    //retrieve a single contact
    public Cursor getOrder(long rowId) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,KEY_CUSTINFO,
                KEY_SIZE, KEY_GOOD1, KEY_GOOD2, KEY_GOOD3, KEY_GOOD4, KEY_DATE},KEY_ROWID + "=" + rowId,null,null,null,null,null);
        if(mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //updates a contact
    public boolean updateOrder(int rowId, String custinfo, int size, int cheese, int pepp, int olive, int sausa)
    {
        LocalDate date = LocalDate.now();
        ContentValues cval = new ContentValues();
        //cval.put(KEY_ROWID, rowId);
        cval.put(KEY_CUSTINFO, custinfo);
        cval.put(KEY_SIZE, size);
        cval.put(KEY_GOOD1, cheese);
        cval.put(KEY_GOOD2, pepp);
        cval.put(KEY_GOOD3, olive);
        cval.put(KEY_GOOD4, sausa);
        cval.put(KEY_DATE, String.valueOf(date));
        int i = db.update(DATABASE_TABLE, cval, KEY_ROWID + "=" + rowId,null);
        return i > 0;
    }

    public boolean isInDB(int row){
        String Query = "Select * from " + DATABASE_TABLE + " where " + KEY_ROWID + "=" + row;
        Cursor cursor = db.rawQuery(Query, null);
        cursor.moveToFirst();

        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }


}//end class DBAdapter















