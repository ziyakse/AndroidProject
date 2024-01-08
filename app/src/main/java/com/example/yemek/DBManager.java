package com.example.yemek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DBManager {
    private SQLiteDatabase sqlDB;
    static final String DBName="Students";
    static final String TableName="Logins";
    static final String ColUserName="UserName";
    static final String ColComment="Comment";
    static final String ColID="ID";
    static final  int DBVersion=4;
    static final String CreatTable="Create Table " +TableName+ "(ID integer PRIMARY KEY AUTOINCREMENT," + ColUserName+ " text,"+ColComment + " text)";

    static class DatabaseHelperUser extends SQLiteOpenHelper{
Context context;
        DatabaseHelperUser (Context context){
            super(context,DBName,null,DBVersion);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreatTable);
            Toast.makeText(context,"Table is created", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF EXISTS " + TableName);
                onCreate(db);

        }
    }

    public DBManager(Context context){
        DatabaseHelperUser db=new DatabaseHelperUser(context);
        sqlDB=db.getWritableDatabase();
    }

    public long Insert(ContentValues values){
        long ID= sqlDB.insert(TableName,"",values);
        return ID;
    }

    public Cursor query(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder){

        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();
        qb.setTables(TableName);

        Cursor cursor=qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }
}
