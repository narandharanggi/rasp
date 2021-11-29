package com.cun.rasp.Database;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.cun.rasp.model.BahanPakan;
import com.cun.rasp.model.BobotSapi;
import com.cun.rasp.model.DetailPakan;
import com.cun.rasp.model.DetailSapi;
import com.cun.rasp.model.LemakSusu;
import com.cun.rasp.model.ProduksiSusu;
import com.cun.rasp.model.Sapi;
import com.cun.rasp.model.perBB;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelperBackup1 extends SQLiteOpenHelper 
{
    private static final String LOG = "DatabseHelper";
    private static String TAG = "DatabaseHelperBackup1"; // Tag just for the LogCat window
//destination path (location) of our database on device 
private static String DB_PATH = "";  
//private static String DB_NAME ="(students).sqlite";// Database name 
private static String DB_NAME ="data.sqlite";
private SQLiteDatabase mDataBase;  
private final Context mContext; 

public DatabaseHelperBackup1(Context context)  
{ 
    super(context, DB_NAME, null, 1);// 1? its Database Version 
    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/"; 
    Log.i(TAG, DB_PATH);
    this.mContext = context; 
}    

public void createDataBase() 
{ 
    //If database not exists copy it from the assets 

    boolean mDataBaseExist = checkDataBase(); 
    if(!mDataBaseExist) 
    { 
        this.getReadableDatabase(); 
        this.close(); 
        try  
        { 
            //Copy the database from assests 
            copyDataBase(); 
            Log.e(TAG, "createDatabase database created"); 
        }  
        catch (IOException mIOException)  
        { 
             Log.i(TAG, "createDataBase "+mIOException+"");
        } 
    } 
} 
    //Check that the database exists here: /data/data/your package/databases/Da Name 
    private boolean checkDataBase() 
    { 
        File dbFile = new File(DB_PATH + DB_NAME);
        Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists(); 
    } 

    //Copy the database from assets 
    private void copyDataBase() throws IOException 
    {  try  
    { 
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME; 
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024]; 
        int mLength; 
        while ((mLength = mInput.read(mBuffer))>0) 
        { 
            mOutput.write(mBuffer, 0, mLength); 
        } 
        mOutput.flush(); 
        mOutput.close(); 
        mInput.close(); 
    }  
    catch (IOException mIOException)  
    { Log.i(TAG,"copyDataBase "+ mIOException+"");

    } 
    } 

    //Open the database, so we can query it 
    public boolean openDataBase() throws SQLException 
    { 
        String mPath = DB_PATH + DB_NAME; 
        //Log.v("mPath", mPath); 
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY); 
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS); 
        return mDataBase != null; 
    } 

    @Override 
    public synchronized void close()  
    { 
        if(mDataBase != null) 
            mDataBase.close(); 
        super.close(); 
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
    
    
    //insert database
    public long insertBahanPakan(String nama_pakan, Double bk ,Double tdn, Double pk, Double ca, Double p,int harga) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(BahanPakan.COLUMN_NAMA_PAKAN, nama_pakan);
        values.put(BahanPakan.COLUMN_HARGA, bk);
        values.put(BahanPakan.COLUMN_TDN, tdn);
        values.put(BahanPakan.COLUMN_PK, pk);
        values.put(BahanPakan.COLUMN_CA, ca);
        values.put(BahanPakan.COLUMN_P, p);
        values.put(BahanPakan.COLUMN_HARGA, harga);

        // insert row
        long id = db.insert(BahanPakan.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertBobotSapi(Double bobot, Double tdn, Double pk, Double ca, Double p) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(BobotSapi.COLUMN_BOBOT, bobot);
        values.put(BobotSapi.COLUMN_TDN, tdn);
        values.put(BobotSapi.COLUMN_PK, pk);
        values.put(BobotSapi.COLUMN_CA, ca);
        values.put(BobotSapi.COLUMN_P, p);

        // insert row
        long id = db.insert(BobotSapi.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertLemakSusu(Double persen_lemak, Double tdn, Double pk, Double ca, Double p) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(LemakSusu.COLUMN_PERSEN_LEMAK, persen_lemak);
        values.put(LemakSusu.COLUMN_TDN, tdn);
        values.put(LemakSusu.COLUMN_PK, pk);
        values.put(LemakSusu.COLUMN_CA, ca);
        values.put(LemakSusu.COLUMN_P, p);

        // insert row
        long id = db.insert(LemakSusu.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertPerBB(String keterangan, Double tdn, Double pk) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(perBB.COLUMN_KETERANGAN, keterangan);
        values.put(perBB.COLUMN_TDN, tdn);
        values.put(perBB.COLUMN_PK, pk);

        // insert row
        long id = db.insert(perBB.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertProduksiSusu(int produksi_susu) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(ProduksiSusu.COLUMN_PRODUKSI_SUSU, produksi_susu);


        // insert row
        long id = db.insert(ProduksiSusu.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertDetailPakan(int sapi, int bahan_pakan) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DetailPakan.COLUMN_SAPI, sapi);
        values.put(DetailPakan.COLUMN_BAHAN_PAKAN, bahan_pakan);


        // insert row
        long id = db.insert(DetailPakan.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertSapi(int bobot, int produksi_susu, Double bk) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Sapi.COLUMN_BOBOT, bobot);
        values.put(Sapi.COLUMN_PRODUKSI_SUSU, produksi_susu);
        values.put(Sapi.COLUMN_BK, bk);


        // insert row
        long id = db.insert(Sapi.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertDetailSapi(int sapi, int lemak_susu, int perBB) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DetailSapi.COLUMN_SAPI, sapi);
        values.put(DetailSapi.COLUMN_LEMAK_SUSU, lemak_susu);
        values.put(DetailSapi.COLUMN_perBB, perBB);


        // insert row
        long id = db.insert(DetailSapi.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    //Fetch Single Data
    @SuppressLint("Range")
    public BahanPakan getBahanPakan(long bahan_pakan_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + BahanPakan.TABLE_NAME + " WHERE "
                + BahanPakan.COLUMN_ID + " = " + bahan_pakan_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        BahanPakan td = new BahanPakan();
        td.setId(c.getInt(c.getColumnIndex(BahanPakan.COLUMN_ID)));
        td.setBk((c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_BK))));
        td.setNamaPakan(c.getString(c.getColumnIndex(BahanPakan.COLUMN_NAMA_PAKAN)));
        td.setCa(c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_CA)));
        td.setHarga(c.getInt(c.getColumnIndex(BahanPakan.COLUMN_HARGA)));
        td.setP(c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_P)));
        td.setPk(c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_PK)));
        td.setTdn(c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_TDN)));

        db.close();
        return td;
    }

    @SuppressLint("Range")
    public BobotSapi getBobotSapi(long bobot_sapi_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + BobotSapi.TABLE_NAME + " WHERE "
                + BobotSapi.COLUMN_ID + " = " + bobot_sapi_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        BobotSapi td = new BobotSapi();
        td.setId(c.getInt(c.getColumnIndex(BobotSapi.COLUMN_ID)));
        td.setBobot((c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_BOBOT))));

        td.setCa(c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_CA)));

        td.setP(c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_P)));
        td.setPk(c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_PK)));
        td.setTdn(c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_TDN)));

        db.close();
        return td;
    }

    @SuppressLint("Range")
    public DetailPakan getDetailPakan(long detail_pakan_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + DetailPakan.TABLE_NAME + " WHERE "
                + DetailPakan.COLUMN_ID + " = " + detail_pakan_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Log.e(LOG, String.valueOf(c.moveToFirst()));

        if (c != null)
            c.moveToFirst();

      

        DetailPakan td = new DetailPakan();
        //   if( cursor != null && cursor.moveToFirst() ){
        //     num = cursor.getString(cursor.getColumnIndex("ContactNumber"));
        //     cursor.close(); 
        // }
        td.setId(c.getInt(c.getColumnIndex(DetailPakan.COLUMN_ID)));
        td.setSapi((c.getInt(c.getColumnIndex(DetailPakan.COLUMN_SAPI))));

        td.setBahanPakan(c.getInt(c.getColumnIndex(DetailPakan.COLUMN_BAHAN_PAKAN)));

        db.close();
        return td;
    }

    @SuppressLint("Range")
    public DetailSapi getDetailSapi(long detail_sapi_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + DetailSapi.TABLE_NAME + " WHERE "
                + DetailSapi.COLUMN_ID + " = " + detail_sapi_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        DetailSapi td = new DetailSapi();
        td.setId(c.getInt(c.getColumnIndex(DetailSapi.COLUMN_ID)));
        td.setSapi((c.getInt(c.getColumnIndex(DetailSapi.COLUMN_SAPI))));
        td.setLemakSusu(c.getInt(c.getColumnIndex(DetailSapi.COLUMN_LEMAK_SUSU)));
        td.setperBB(c.getInt(c.getColumnIndex(DetailSapi.COLUMN_perBB)));

        db.close();
        return td;
    }

    @SuppressLint("Range")
    public LemakSusu getLemakSusu(long lemak_susu_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + LemakSusu.TABLE_NAME + " WHERE "
                + LemakSusu.COLUMN_ID + " = " + lemak_susu_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        LemakSusu td = new LemakSusu();
        td.setId(c.getInt(c.getColumnIndex(LemakSusu.COLUMN_ID)));
        td.setPersenLemak((c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_PERSEN_LEMAK))));
        td.setTdn(c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_TDN)));
        td.setPk(c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_PK)));
        td.setCa(c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_CA)));
        td.setP(c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_P)));

        db.close();
        return td;
    }

    @SuppressLint("Range")
    public perBB getperBB(long perBB_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + perBB.TABLE_NAME + " WHERE "
                + perBB.COLUMN_ID + " = " + perBB_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        perBB td = new perBB();
        td.setId(c.getInt(c.getColumnIndex(perBB.COLUMN_ID)));
        td.setKeterangan((c.getString(c.getColumnIndex(perBB.COLUMN_KETERANGAN))));
        td.setTdn(c.getDouble(c.getColumnIndex(perBB.COLUMN_TDN)));
        td.setPk(c.getDouble(c.getColumnIndex(perBB.COLUMN_PK)));


        db.close();
        return td;
    }

    @SuppressLint("Range")
    public ProduksiSusu getProduksiSusu(long produksi_susu_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + ProduksiSusu.TABLE_NAME + " WHERE "
                + ProduksiSusu.COLUMN_ID + " = " + produksi_susu_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        ProduksiSusu td = new ProduksiSusu();


        if(c != null && c.moveToFirst()) {
            //your code
            td.setId(c.getInt(c.getColumnIndex(ProduksiSusu.COLUMN_ID)));
            td.setProduksiSusu((c.getInt(c.getColumnIndex(ProduksiSusu.COLUMN_PRODUKSI_SUSU))));
            }

        // db.close();
        db.close();
        return td;
    }

    @SuppressLint("Range")
    public Sapi getSapi(long sapi_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Sapi.TABLE_NAME + " WHERE "
                + Sapi.COLUMN_ID + " = " + sapi_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Sapi td = new Sapi();
        td.setId(c.getInt(c.getColumnIndex(Sapi.COLUMN_ID)));
        td.setProduksiSusu((c.getInt(c.getColumnIndex(Sapi.COLUMN_PRODUKSI_SUSU))));
        td.setBobot((c.getInt(c.getColumnIndex(Sapi.COLUMN_BOBOT))));
        td.setBk((c.getDouble(c.getColumnIndex(Sapi.COLUMN_BK))));

        db.close();
        return td;
    }

    //get all data

@SuppressLint("Range")
public List<Sapi> getAllSapis() {
    List<Sapi> Sapis = new ArrayList<Sapi>();
    String selectQuery = "SELECT  * FROM " + Sapi.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            Sapi td = new Sapi();
            td.setId(c.getInt(c.getColumnIndex(Sapi.COLUMN_ID)));
            td.setProduksiSusu((c.getInt(c.getColumnIndex(Sapi.COLUMN_PRODUKSI_SUSU))));
            td.setBobot((c.getInt(c.getColumnIndex(Sapi.COLUMN_BOBOT))));
            td.setBk((c.getDouble(c.getColumnIndex(Sapi.COLUMN_BK))));

            // adding to Sapi list
            Sapis.add(td);
        } while (c.moveToNext());
    }

    db.close();
    return Sapis;
}

@SuppressLint("Range")
public List<ProduksiSusu> getAllProduksiSusus() {
    List<ProduksiSusu> ProduksiSusus = new ArrayList<ProduksiSusu>();
    String selectQuery = "SELECT  * FROM " + ProduksiSusu.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();

//    Cursor c = qb.delete(null,null,null);
    
    Cursor c = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            ProduksiSusu td = new ProduksiSusu();
            td.setId(c.getInt(c.getColumnIndex(ProduksiSusu.COLUMN_ID)));
            td.setProduksiSusu((c.getInt(c.getColumnIndex(ProduksiSusu.COLUMN_PRODUKSI_SUSU))));

            // adding to ProduksiSusu list
            ProduksiSusus.add(td);
        } while (c.moveToNext());
    }

    db.close();
    return ProduksiSusus;
}

@SuppressLint("Range")
public List<perBB> getAllperBBs() {
    List<perBB> perBBs = new ArrayList<perBB>();
    String selectQuery = "SELECT  * FROM " + perBB.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            perBB td = new perBB();
            td.setId(c.getInt(c.getColumnIndex(perBB.COLUMN_ID)));
            td.setKeterangan((c.getString(c.getColumnIndex(perBB.COLUMN_KETERANGAN))));
            td.setTdn(c.getDouble(c.getColumnIndex(perBB.COLUMN_TDN)));
            td.setPk(c.getDouble(c.getColumnIndex(perBB.COLUMN_PK)));

            // adding to perBB list
            perBBs.add(td);
        } while (c.moveToNext());
    }

    db.close();
    return perBBs;
}

@SuppressLint("Range")
public List<LemakSusu> getAllLemakSusus() {
    List<LemakSusu> LemakSusus = new ArrayList<LemakSusu>();
    String selectQuery = "SELECT  * FROM " + LemakSusu.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            LemakSusu td = new LemakSusu();
            td.setId(c.getInt(c.getColumnIndex(LemakSusu.COLUMN_ID)));
            td.setPersenLemak((c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_PERSEN_LEMAK))));
            td.setTdn(c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_TDN)));
            td.setPk(c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_PK)));
            td.setCa(c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_CA)));
            td.setP(c.getDouble(c.getColumnIndex(LemakSusu.COLUMN_P)));

            // adding to LemakSusu list
            LemakSusus.add(td);
        } while (c.moveToNext());
    }

    db.close();
    return LemakSusus;
}
    
@SuppressLint("Range")
public List<DetailSapi> getAllDetailSapis() {
    List<DetailSapi> DetailSapis = new ArrayList<DetailSapi>();
    String selectQuery = "SELECT  * FROM " + DetailSapi.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            DetailSapi td = new DetailSapi();
            td.setId(c.getInt(c.getColumnIndex(DetailSapi.COLUMN_ID)));
            td.setSapi((c.getInt(c.getColumnIndex(DetailSapi.COLUMN_SAPI))));
            td.setLemakSusu(c.getInt(c.getColumnIndex(DetailSapi.COLUMN_LEMAK_SUSU)));
            td.setperBB(c.getInt(c.getColumnIndex(DetailSapi.COLUMN_perBB)));

            // adding to DetailSapi list
            DetailSapis.add(td);
        } while (c.moveToNext());
    }

    db.close();
    return DetailSapis;
}


@SuppressLint("Range")
public List<DetailPakan> getAllDetailPakans() {
    List<DetailPakan> DetailPakans = new ArrayList<DetailPakan>();
    String selectQuery = "SELECT  * FROM " + DetailPakan.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            DetailPakan td = new DetailPakan();
            td.setId(c.getInt(c.getColumnIndex(DetailPakan.COLUMN_ID)));
            td.setSapi((c.getInt(c.getColumnIndex(DetailPakan.COLUMN_SAPI))));
            td.setBahanPakan(c.getInt(c.getColumnIndex(DetailPakan.COLUMN_BAHAN_PAKAN)));

            // adding to DetailPakan list
            DetailPakans.add(td);
        } while (c.moveToNext());
    }

    db.close();
    return DetailPakans;
}

@SuppressLint("Range")
public List<BobotSapi> getAllBobotSapis() {
    List<BobotSapi> BobotSapis = new ArrayList<BobotSapi>();
    String selectQuery = "SELECT  * FROM " + BobotSapi.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            BobotSapi td = new BobotSapi();
            td.setId(c.getInt(c.getColumnIndex(BobotSapi.COLUMN_ID)));
            td.setBobot((c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_BOBOT))));
    
            td.setCa(c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_CA)));
    
            td.setP(c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_P)));
            td.setPk(c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_PK)));
            td.setTdn(c.getDouble(c.getColumnIndex(BobotSapi.COLUMN_TDN)));

            // adding to BobotSapi list
            BobotSapis.add(td);
        } while (c.moveToNext());
    }

    db.close();
    return BobotSapis;
}

@SuppressLint("Range")
public List<BahanPakan> getAllBahanPakans() {
    List<BahanPakan> BahanPakans = new ArrayList<BahanPakan>();
    String selectQuery = "SELECT  * FROM " + BahanPakan.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            BahanPakan td = new BahanPakan();
            td.setId(c.getInt(c.getColumnIndex(BahanPakan.COLUMN_ID)));
            td.setBk((c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_BK))));
            td.setNamaPakan(c.getString(c.getColumnIndex(BahanPakan.COLUMN_NAMA_PAKAN)));
            td.setCa(c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_CA)));
            td.setHarga(c.getInt(c.getColumnIndex(BahanPakan.COLUMN_HARGA)));
            td.setP(c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_P)));
            td.setPk(c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_PK)));
            td.setTdn(c.getDouble(c.getColumnIndex(BahanPakan.COLUMN_TDN)));
            // adding to BahanPakan list
            BahanPakans.add(td);
        } while (c.moveToNext());
    }

    db.close();
    return BahanPakans;
}


//updateee
    public int updateBahanPakan(BahanPakan bahanpakan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
    
        // values.put(BahanPakan.COLUMN_ID, bahanpakan.getId());
        values.put(BahanPakan.COLUMN_BK, bahanpakan.getBk());
        values.put(BahanPakan.COLUMN_NAMA_PAKAN, bahanpakan.getNamaPakan());
        values.put(BahanPakan.COLUMN_CA, bahanpakan.getCa());
        values.put(BahanPakan.COLUMN_HARGA, bahanpakan.getHarga());
        values.put(BahanPakan.COLUMN_P, bahanpakan.getP());
        values.put(BahanPakan.COLUMN_PK, bahanpakan.getPk());
        values.put(BahanPakan.COLUMN_TDN, bahanpakan.getTdn());

        // updating row
    return db.update(BahanPakan.TABLE_NAME, values, BahanPakan.COLUMN_ID + " = ?",
                new String[] { String.valueOf(bahanpakan.getId()) });

    

    
        
    }
 
    public int updateBobotSapi(BobotSapi bobotsapi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // values.put(BobotSapi.COLUMN_ID, bobotsapi.getId());
        values.put(BobotSapi.COLUMN_BOBOT, bobotsapi.getBobot());
        values.put(BobotSapi.COLUMN_CA, bobotsapi.getCa());
        values.put(BobotSapi.COLUMN_P, bobotsapi.getP());
        values.put(BobotSapi.COLUMN_PK, bobotsapi.getPk());
        values.put(BobotSapi.COLUMN_TDN, bobotsapi.getTdn());


        // updating row
    return db.update(BobotSapi.TABLE_NAME, values, BobotSapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(bobotsapi.getId()) });

    

    
        
    }
 
    public int updateDetailPakan(DetailPakan detailpakan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        // values.put(DetailPakan.COLUMN_ID, detailpakan.getId());
        values.put(DetailPakan.COLUMN_SAPI, detailpakan.getSapi());
        values.put(DetailPakan.COLUMN_BAHAN_PAKAN, detailpakan.getBahanPakan());

        // updating row
    return db.update(DetailPakan.TABLE_NAME, values, DetailPakan.COLUMN_ID + " = ?",
                new String[] { String.valueOf(detailpakan.getId()) });

    

    
        
    }

    public int updateDetailSapi(DetailSapi detailsapi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // values.put(DetailSapi.COLUMN_ID, getId());
        values.put(DetailSapi.COLUMN_SAPI, detailsapi.getSapi());
        values.put(DetailSapi.COLUMN_LEMAK_SUSU, detailsapi.getLemakSusu());
        values.put(DetailSapi.COLUMN_perBB, detailsapi.getperBB());

        // updating row
    return db.update(DetailSapi.TABLE_NAME, values, DetailSapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(detailsapi.getId()) });

    

    
        
    }

    public int updateLemakSusu(LemakSusu lemaksusu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        // values.put(LemakSusu.COLUMN_ID, lemaksusu.setId());
            values.put(LemakSusu.COLUMN_PERSEN_LEMAK, lemaksusu.getPersenLemak());
            values.put(LemakSusu.COLUMN_TDN, lemaksusu.getTdn());
            values.put(LemakSusu.COLUMN_PK, lemaksusu.getPk());
            values.put(LemakSusu.COLUMN_CA, lemaksusu.getCa());
            values.put(LemakSusu.COLUMN_P, lemaksusu.getP());

        // updating row
        return db.update(LemakSusu.TABLE_NAME, values, LemakSusu.COLUMN_ID + " = ?",
                new String[] { String.valueOf(lemaksusu.getId()) });

    }
    
    public int updateperBB(perBB perbb) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
            // values.put(perBB.COLUMN_ID, perbb.getId());
            values.put(perBB.COLUMN_KETERANGAN, perbb.getKeterangan());
            values.put(perBB.COLUMN_TDN, perbb.getTdn());
            values.put(perBB.COLUMN_PK, perbb.getPk());

        // updating row
    return db.update(perBB.TABLE_NAME, values, perBB.COLUMN_ID + " = ?",
                new String[] { String.valueOf(perbb.getId()) });

    

    
    }
    
    public int updateProduksiSusu(ProduksiSusu produksisusu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProduksiSusu.COLUMN_ID, produksisusu.getId());
        values.put(ProduksiSusu.COLUMN_PRODUKSI_SUSU, produksisusu.getProduksiSusu());

        // updating row
    return db.update(ProduksiSusu.TABLE_NAME, values, ProduksiSusu.COLUMN_ID + " = ?",
                new String[] { String.valueOf(produksisusu.getId()) });

    

    
        
    }

    public int updateSapi(Sapi sapi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Sapi.COLUMN_ID, sapi.getId());
        values.put(Sapi.COLUMN_PRODUKSI_SUSU, sapi.getProduksiSusu());
        values.put(Sapi.COLUMN_BOBOT, sapi.getBobot());
        values.put(Sapi.COLUMN_BK, sapi.getBk());

        // updating row
    return db.update(Sapi.TABLE_NAME, values, Sapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(sapi.getId()) });

    

    
        
    }

    /// delete
    public void deleteSapi(long Sapi_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Sapi.TABLE_NAME, Sapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(Sapi_id) });
        db.close();
        
    }
   
    public void deleteProduksiSusu(long ProduksiSusu_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(ProduksiSusu.TABLE_NAME, ProduksiSusu.COLUMN_ID + " = ?",
                new String[] { String.valueOf(ProduksiSusu_id) });
        db.close();
        
    }
    
    public void deleteperBB(long perBB_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(perBB.TABLE_NAME, perBB.COLUMN_ID + " = ?",
                new String[] { String.valueOf(perBB_id) });
        db.close();
        
    }
  
    public void deleteLemakSusu(long LemakSusu_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LemakSusu.TABLE_NAME, LemakSusu.COLUMN_ID + " = ?",
                new String[] { String.valueOf(LemakSusu_id) });
        db.close();
        
    }
  
    public void deleteDetailSapi(long DetailSapi_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DetailSapi.TABLE_NAME, DetailSapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(DetailSapi_id) });
        db.close();
        
    }
 
    public void deleteDetailPakan(long DetailPakan_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DetailPakan.TABLE_NAME, DetailPakan.COLUMN_ID + " = ?",
                new String[] { String.valueOf(DetailPakan_id) });
        db.close();
        
    }
  
    public void deleteBobotSapi(long BobotSapi_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BobotSapi.TABLE_NAME, BobotSapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(BobotSapi_id) });
        db.close();
        
    }

    public void deleteBahanPakan(long BahanPakan_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BahanPakan.TABLE_NAME, BahanPakan.COLUMN_ID + " = ?",
                new String[] { String.valueOf(BahanPakan_id) });
        db.close();
        
    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public int getBahanPakanCount() {
        String countQuery = "SELECT  * FROM " + BahanPakan.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getBobotSapiCount() {
        String countQuery = "SELECT  * FROM " + BobotSapi.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getDetailPakanCount() {
        String countQuery = "SELECT  * FROM " + DetailPakan.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
   
    public int getDetailSapiCount() {
        String countQuery = "SELECT  * FROM " + DetailSapi.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getLemakSusuCount() {
        String countQuery = "SELECT  * FROM " + LemakSusu.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getperBBCount() {
        String countQuery = "SELECT  * FROM " + perBB.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
  
    public int getProduksiSusuCount() {
        String countQuery = "SELECT  * FROM " + ProduksiSusu.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getSapiCount() {
        String countQuery = "SELECT  * FROM " + Sapi.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }

} 