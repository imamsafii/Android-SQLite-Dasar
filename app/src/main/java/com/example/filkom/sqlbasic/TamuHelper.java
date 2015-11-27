package com.example.filkom.sqlbasic;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by imam on 11/25/2015.
 */
public class TamuHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DataTamu";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE = "Tamus";
    public static final String COL_ID = "_id";
    public static final String COL_NAMA = "nama";
    public static  final String CREATE_TABLE = "CREATE TABLE "+DATABASE_TABLE+"("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_NAMA+" VARCHAR(64));";

    public TamuHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}

