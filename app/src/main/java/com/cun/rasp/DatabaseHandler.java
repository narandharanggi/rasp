package com.cun.rasp;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHandler.class.getSimpleName();
    public static int flag;
    static String DB_NAME = "data.sqlite";
    private final Context myContext;
    String outFileName = "";
    private String DB_PATH;
    private SQLiteDatabase db;

    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        String appDataPath = context.getApplicationInfo().dataDir;
        DB_PATH = appDataPath + "/databases/";
        System.out.println(DB_PATH);
        outFileName = DB_PATH + DB_NAME;
        File file = new File(DB_PATH);
        Log.e(TAG, "Databasehelper: " + file.exists());
        if(!file.exists()){
            file.mkdir();
        }
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase();
        if(dbExist){

        } else {
            this.getReadableDatabase();
            try{
                copyDataBase();
            } catch (IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            checkDB = SQLiteDatabase.openDatabase(outFileName, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLException e){
            try{
                copyDataBase();
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{
        Log.i("Database", "New Database is being copied to device");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        InputStream myInput = null;
        try{
            myInput = myContext.getAssets().open(DB_NAME);
            myOutput = new FileOutputStream(DB_PATH+DB_NAME);

            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("Database",
                    "New Database is being copied to device");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openDataBase() throws SQLException{
        String myPath = DB_PATH+DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.e(TAG, "openDataBase: Open "+db.isOpen());
    }

    public synchronized void close(){
        if(db != null){
            db.close();
            super.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
