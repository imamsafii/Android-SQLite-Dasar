package com.example.filkom.sqlbasic;

/**
 * Created by imam on 11/25/2015.
 */
public class Tamu {
    private long id;
    private String nama;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    @Override
    public String toString() {
        return nama;
    }
}
