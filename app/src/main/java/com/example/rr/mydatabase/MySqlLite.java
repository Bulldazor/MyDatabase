package com.example.rr.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by R.R on 4/3/2017.
 */
// Date Base
public class MySqlLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1; // version name of my database
    private static final String DATABASE_NAME="mydb.db"; // my database  name
    private static final String TABLE_NAME="mytable"; //table name
    private static final String COLUMN1="ID";
    private static final String COLUMN2="WORK";


    public MySqlLite(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);


            }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String qurey;
        qurey="(CREATE TABLE  "+TABLE_NAME+" (ID INTEGER PRIMARY KEY,WORK TEXT)";
        db.execSQL(qurey);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXITS "+TABLE_NAME); // If Previous db found then drop it
        onCreate(db);

    }
    //Insert Data into DATABASE
    public boolean addToTable(String ID,String WORK){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(COLUMN1,ID);
        contentValues.put(COLUMN2,WORK);

        long chk = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(chk==-1) // data inserted or not? it will return -1 when not inserted
            return false;
        else
            return true;

    }

    public Cursor displayData(){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor result;
        result=sqLiteDatabase.rawQuery("SELECT *FROM  "+TABLE_NAME,null);
        return result;

    }
}
