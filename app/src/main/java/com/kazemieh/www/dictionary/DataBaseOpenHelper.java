package com.kazemieh.www.dictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    String dbpath = "/data/data/com.kazemieh.www.dictionary/databases/";
    public static String dbname = "dic";
    public static Context context;

    public DataBaseOpenHelper(@Nullable Context context) {
        super(context, dbname, null, 1);
        this.context = context;
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkdb = null;
        String path = dbpath + dbname;
        try {
            checkdb = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){

        }
        if (checkdb != null) {
            checkdb.close();
        }
        return checkdb != null ? true : false;
    }

    public void createDataBase() {
        boolean checkdb = checkDataBase();
        if (!checkdb) {
            File dicfile = new File(dbpath);
            dicfile.mkdir();
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.close();
            }
        }
    }

    public void copyDataBase() throws IOException {
        InputStream inputStream = context.getAssets().open(dbname);
        OutputStream outputStream = new FileOutputStream(dbpath + dbname);
        byte[] buffer = new byte[1024];
        int lenght;
        while ((lenght = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, lenght);
        }
        inputStream.close();
        outputStream.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDataBase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
