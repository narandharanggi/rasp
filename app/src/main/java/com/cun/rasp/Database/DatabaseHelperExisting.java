package com.cun.rasp.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelperExisting extends SQLiteOpenHelper {

  private static String DB_PATH="data/data/com.cun.rasp/databases/";
  private static final String DB_NAME="data.sqlite";
  private SQLiteDatabase myDatabase;
  private final Context myContext;

  public DatabaseHelperExisting(Context context) {
      super(context, DB_NAME, null, 1);
      // TODO Auto-generated constructor stub
      this.myContext = context;
  }


    public void createDataBase() throws IOException{
  
      boolean dbExist = checkDataBase();
  
      SQLiteDatabase db_read = null;
  
      if(dbExist){
          //Do nothing
  
      }else{
  
          db_read = this.getReadableDatabase();
          db_read.close();
  
          try {
              copyDataBase();
          } catch (Exception e) {
              // TODO: handle exception
              throw new Error("Error copying database (createDataBase)");
          }
      }
  
  }
  
  public boolean checkDataBase() {
      File dbFile = new File(DB_PATH+DB_NAME);
      return dbFile.exists();
  
  }
  
  public void copyDataBase() throws IOException{
  
      //Open your local db as the input stream
      InputStream myInput = myContext.getAssets().open(DB_NAME);
      // Path to the just created empty db. Destination folder (where we created the DB empty)
      String outFileName = DB_PATH+DB_NAME;
      //Open the empty db as the output stream. //We opened it BBDD empty as OutputStream
      OutputStream myOutPut = new FileOutputStream(outFileName);
  
      Log.e("LocAndroid", "copydatabase");
  
  
      //transfer bytes from the inputfile to the outputfile
      byte[] buffer = new byte[1024];
      int lenght;
  
  
      while((lenght = myInput.read(buffer))!=-1){
          if(lenght > 0){
  
              myOutPut.write(buffer, 0, lenght);
  
  
          }
  
      }
      //Close the streams
      myOutPut.flush();
      myOutPut.close();
      myInput.close();
  
  }
  
  public SQLiteDatabase openDataBase() throws SQLException{

      //Open the database
      String myPath = DB_PATH+DB_NAME;
      myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
      return myDatabase;
  }
  
  
  public synchronized void close(){
  
      if(myDatabase != null){
          myDatabase.close();
      }
      super.close();
  }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}