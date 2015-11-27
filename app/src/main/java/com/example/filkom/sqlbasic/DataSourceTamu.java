package com.example.filkom.sqlbasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imam on 11/25/2015.
 */
public class DataSourceTamu {
    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;
    private String[] allColumns = { TamuHelper.COL_ID,
            TamuHelper.COL_NAMA };


    public DataSourceTamu(Context context){
        dbHelper = new TamuHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Tamu tambahTamu(String nama){
        ContentValues values = new ContentValues();
        values.put(TamuHelper.COL_NAMA, nama);
        long insertID = database.insert(TamuHelper.DATABASE_TABLE,null,values);
        Cursor cursor = database.query(TamuHelper.DATABASE_TABLE,
                allColumns, TamuHelper.COL_ID + " = " + insertID, null,
                null, null, null);

        cursor.moveToFirst();
        Tamu tamubaru = cursorToTamu(cursor);
        cursor.close();
        return tamubaru;
    }

    public List<Tamu> semuaTamu() {
        List<Tamu> tamus = new ArrayList<Tamu>();
        Cursor cursor = database.query(TamuHelper.DATABASE_TABLE,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tamu tamu = cursorToTamu(cursor);
            tamus.add(tamu);
            cursor.moveToNext();
        }
        cursor.close();
        return tamus;
    }

    public void hapusTamu(Tamu tamu) {
        long id = tamu.getId();
        database.delete(TamuHelper.DATABASE_TABLE, TamuHelper.COL_ID
                + " = " + id, null);
    }

    public List<Tamu> updateTamu(Tamu tamu,String namaBaru){
        ContentValues values = new ContentValues();
        values.put(TamuHelper.COL_NAMA,namaBaru);
        long id = tamu.getId();
        database.update(TamuHelper.DATABASE_TABLE, values, TamuHelper.COL_ID+"="+id, null );
        return semuaTamu();
    }

    private Tamu cursorToTamu(Cursor cursor) {
        Tamu tamu = new Tamu();
        tamu.setId(cursor.getLong(0));
        tamu.setNama(cursor.getString(1));
        return tamu;
    }

}
