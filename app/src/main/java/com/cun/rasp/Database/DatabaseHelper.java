package com.cun.rasp.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteAssetHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "data.sqlite";
    private static String DB_PATH="data/data/com.cun.rasp/databases/";
    private static final String DB_NAME="data.sqlite";
    public DatabaseHelperExisting myDbHelper;
    private SQLiteDatabase dbExist;
    private Void createDB;

//    private static final SQLiteDatabase db = DatabaseHelperExisting.
//    private final static String DATABASE_PATH ="/data/data/com.yourpackagename/databases/";
    public SQLiteDatabase openDatabase() throws SQLException {
        String myPath = DB_PATH + DATABASE_NAME;
        SQLiteDatabase myDataBase = SQLiteDatabase.openOrCreateDatabase(myPath, null, null);
        return myDataBase;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // myDbHelper = new DatabaseHelperExisting(context);
//        String myPath = DB_PATH+DATABASE_NAME;
        // dbExist = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        dbExist = openDatabase();
        // Log.e("LocAndroid heheheh", String.valueOf(dbExist));

    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // db.execSQL("PRAGMA foreign_keys=ON;");
//        // // creating required tables
//        // db.execSQL(ProduksiSusu.CREATE_TABLE_PRODUKSI_SUSU);
//        // db.execSQL(LemakSusu.CREATE_TABLE_LEMAK_SUSU);
//        // db.execSQL(perBB.CREATE_TABLE_PERBB);
//        // db.execSQL(BahanPakan.CREATE_TABLE_BAHAN_PAKAN);
//        // db.execSQL(BobotSapi.CREATE_TABLE_BOBOT_SAPI);
//        // db.execSQL(Sapi.CREATE_TABLE_SAPI);
//        // db.execSQL(DetailPakan.CREATE_TABLE_DETAIL_PAKAN);
//        // db.execSQL(DetailSapi.CREATE_TABLE_DETAIL_SAPI);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // // on upgrade drop older tables
//        // db.execSQL("DROP TABLE IF EXISTS " + Sapi.TABLE_NAME);
//        // db.execSQL("DROP TABLE IF EXISTS " + BahanPakan.TABLE_NAME);
//        // db.execSQL("DROP TABLE IF EXISTS " + BobotSapi.TABLE_NAME);
//        // db.execSQL("DROP TABLE IF EXISTS " + DetailPakan.TABLE_NAME);
//        // db.execSQL("DROP TABLE IF EXISTS " + DetailSapi.TABLE_NAME);
//        // db.execSQL("DROP TABLE IF EXISTS " + LemakSusu.TABLE_NAME);
//        // db.execSQL("DROP TABLE IF EXISTS " + perBB.TABLE_NAME);
//        // db.execSQL("DROP TABLE IF EXISTS " + ProduksiSusu.TABLE_NAME);
//
//        // // create new tables
//        // onCreate(db);
//    }

    //insert database
    public long insertBahanPakan(String nama_pakan, Double bk ,Double tdn, Double pk, Double ca, Double p,int harga) {
        // get writable database as we want to write data
        SQLiteDatabase db = openDatabase();

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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

        closeDB();
        return td;
    }

    @SuppressLint("Range")
    public BobotSapi getBobotSapi(long bobot_sapi_id) {
        SQLiteDatabase db = dbExist;

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

        closeDB();
        return td;
    }

    @SuppressLint("Range")
    public DetailPakan getDetailPakan(long detail_pakan_id) {
        SQLiteDatabase db = dbExist;

        String selectQuery = "SELECT  * FROM " + DetailPakan.TABLE_NAME + " WHERE "
                + DetailPakan.COLUMN_ID + " = " + detail_pakan_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        DetailPakan td = new DetailPakan();
        td.setId(c.getInt(c.getColumnIndex(DetailPakan.COLUMN_ID)));
        td.setSapi((c.getInt(c.getColumnIndex(DetailPakan.COLUMN_SAPI))));

        td.setBahanPakan(c.getInt(c.getColumnIndex(DetailPakan.COLUMN_BAHAN_PAKAN)));

        closeDB();
        return td;
    }

    @SuppressLint("Range")
    public DetailSapi getDetailSapi(long detail_sapi_id) {
        SQLiteDatabase db = dbExist;

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

        closeDB();
        return td;
    }

    @SuppressLint("Range")
    public LemakSusu getLemakSusu(long lemak_susu_id) {
        SQLiteDatabase db = dbExist;

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

        closeDB();
        return td;
    }

    @SuppressLint("Range")
    public perBB getperBB(long perBB_id) {
        SQLiteDatabase db = dbExist;

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


        closeDB();
        return td;
    }

    @SuppressLint("Range")
    public ProduksiSusu getProduksiSusu(long produksi_susu_id) {
        SQLiteDatabase db = dbExist;

        String selectQuery = "SELECT  * FROM " + ProduksiSusu.TABLE_NAME + " WHERE "
                + ProduksiSusu.COLUMN_ID + " = " + produksi_susu_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        ProduksiSusu td = new ProduksiSusu();
        td.setId(c.getInt(c.getColumnIndex(ProduksiSusu.COLUMN_ID)));
        td.setProduksiSusu((c.getInt(c.getColumnIndex(ProduksiSusu.COLUMN_PRODUKSI_SUSU))));

        closeDB();
        return td;
    }

    @SuppressLint("Range")
    public Sapi getSapi(long sapi_id) {
        SQLiteDatabase db = dbExist;

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

        closeDB();
        return td;
    }

    //get all data

@SuppressLint("Range")
public List<Sapi> getAllSapis() {
    List<Sapi> Sapis = new ArrayList<Sapi>();
    String selectQuery = "SELECT  * FROM " + Sapi.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = dbExist;
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

    closeDB();
    return Sapis;
}

@SuppressLint("Range")
public List<ProduksiSusu> getAllProduksiSusus() {
    List<ProduksiSusu> ProduksiSusus = new ArrayList<ProduksiSusu>();
    String selectQuery = "SELECT  * FROM " + ProduksiSusu.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = dbExist;
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

    closeDB();
    return ProduksiSusus;
}

@SuppressLint("Range")
public List<perBB> getAllperBBs() {
    List<perBB> perBBs = new ArrayList<perBB>();
    String selectQuery = "SELECT  * FROM " + perBB.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = dbExist;
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

    closeDB();
    return perBBs;
}

@SuppressLint("Range")
public List<LemakSusu> getAllLemakSusus() {
    List<LemakSusu> LemakSusus = new ArrayList<LemakSusu>();
    String selectQuery = "SELECT  * FROM " + LemakSusu.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = dbExist;
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

    closeDB();
    return LemakSusus;
}
    
@SuppressLint("Range")
public List<DetailSapi> getAllDetailSapis() {
    List<DetailSapi> DetailSapis = new ArrayList<DetailSapi>();
    String selectQuery = "SELECT  * FROM " + DetailSapi.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = dbExist;
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

    closeDB();
    return DetailSapis;
}


@SuppressLint("Range")
public List<DetailPakan> getAllDetailPakans() {
    List<DetailPakan> DetailPakans = new ArrayList<DetailPakan>();
    String selectQuery = "SELECT  * FROM " + DetailPakan.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = dbExist;
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

    closeDB();
    return DetailPakans;
}

@SuppressLint("Range")
public List<BobotSapi> getAllBobotSapis() {
    List<BobotSapi> BobotSapis = new ArrayList<BobotSapi>();
    String selectQuery = "SELECT  * FROM " + BobotSapi.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = dbExist;
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

    closeDB();
    return BobotSapis;
}

@SuppressLint("Range")
public List<BahanPakan> getAllBahanPakans() {
    List<BahanPakan> BahanPakans = new ArrayList<BahanPakan>();
    String selectQuery = "SELECT  * FROM " + BahanPakan.TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = dbExist;
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

    closeDB();
    return BahanPakans;
}


//updateee
    public int updateBahanPakan(BahanPakan bahanpakan) {
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

        ContentValues values = new ContentValues();


        // values.put(DetailPakan.COLUMN_ID, detailpakan.getId());
        values.put(DetailPakan.COLUMN_SAPI, detailpakan.getSapi());
        values.put(DetailPakan.COLUMN_BAHAN_PAKAN, detailpakan.getBahanPakan());

        // updating row
    return db.update(DetailPakan.TABLE_NAME, values, DetailPakan.COLUMN_ID + " = ?",
                new String[] { String.valueOf(detailpakan.getId()) });

    

    
        
    }

    public int updateDetailSapi(DetailSapi detailsapi) {
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;

        ContentValues values = new ContentValues();
        values.put(ProduksiSusu.COLUMN_ID, produksisusu.getId());
        values.put(ProduksiSusu.COLUMN_PRODUKSI_SUSU, produksisusu.getProduksiSusu());

        // updating row
    return db.update(ProduksiSusu.TABLE_NAME, values, ProduksiSusu.COLUMN_ID + " = ?",
                new String[] { String.valueOf(produksisusu.getId()) });

    

    
        
    }

    public int updateSapi(Sapi sapi) {
        SQLiteDatabase db = dbExist;

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
        SQLiteDatabase db = dbExist;
        db.delete(Sapi.TABLE_NAME, Sapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(Sapi_id) });
        closeDB();
        
    }
   
    public void deleteProduksiSusu(long ProduksiSusu_id) {
        SQLiteDatabase db = dbExist;
        db.delete(ProduksiSusu.TABLE_NAME, ProduksiSusu.COLUMN_ID + " = ?",
                new String[] { String.valueOf(ProduksiSusu_id) });
        closeDB();
        
    }
    
    public void deleteperBB(long perBB_id) {
        SQLiteDatabase db = dbExist;
        db.delete(perBB.TABLE_NAME, perBB.COLUMN_ID + " = ?",
                new String[] { String.valueOf(perBB_id) });
        closeDB();
        
    }
  
    public void deleteLemakSusu(long LemakSusu_id) {
        SQLiteDatabase db = dbExist;
        db.delete(LemakSusu.TABLE_NAME, LemakSusu.COLUMN_ID + " = ?",
                new String[] { String.valueOf(LemakSusu_id) });
        closeDB();
        
    }
  
    public void deleteDetailSapi(long DetailSapi_id) {
        SQLiteDatabase db = dbExist;
        db.delete(DetailSapi.TABLE_NAME, DetailSapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(DetailSapi_id) });
        closeDB();
        
    }
 
    public void deleteDetailPakan(long DetailPakan_id) {
        SQLiteDatabase db = dbExist;
        db.delete(DetailPakan.TABLE_NAME, DetailPakan.COLUMN_ID + " = ?",
                new String[] { String.valueOf(DetailPakan_id) });
        closeDB();
        
    }
  
    public void deleteBobotSapi(long BobotSapi_id) {
        SQLiteDatabase db = dbExist;
        db.delete(BobotSapi.TABLE_NAME, BobotSapi.COLUMN_ID + " = ?",
                new String[] { String.valueOf(BobotSapi_id) });
        closeDB();
        
    }

    public void deleteBahanPakan(long BahanPakan_id) {
        SQLiteDatabase db = dbExist;
        db.delete(BahanPakan.TABLE_NAME, BahanPakan.COLUMN_ID + " = ?",
                new String[] { String.valueOf(BahanPakan_id) });
        closeDB();
        
    }
    public void closeDB() {
        SQLiteDatabase db = dbExist;
        if (db != null && db.isOpen())
            db.close();
    }

    public int getBahanPakanCount() {
        String countQuery = "SELECT  * FROM " + BahanPakan.TABLE_NAME;
        SQLiteDatabase db = dbExist;
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getBobotSapiCount() {
        String countQuery = "SELECT  * FROM " + BobotSapi.TABLE_NAME;
        SQLiteDatabase db = dbExist;
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getDetailPakanCount() {
        String countQuery = "SELECT  * FROM " + DetailPakan.TABLE_NAME;
        SQLiteDatabase db = dbExist;
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
   
    public int getDetailSapiCount() {
        String countQuery = "SELECT  * FROM " + DetailSapi.TABLE_NAME;
        SQLiteDatabase db = dbExist;
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getLemakSusuCount() {
        String countQuery = "SELECT  * FROM " + LemakSusu.TABLE_NAME;
        SQLiteDatabase db = dbExist;
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getperBBCount() {
        String countQuery = "SELECT  * FROM " + perBB.TABLE_NAME;
        SQLiteDatabase db = dbExist;
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
  
    public int getProduksiSusuCount() {
        String countQuery = "SELECT  * FROM " + ProduksiSusu.TABLE_NAME;
        SQLiteDatabase db = dbExist;
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
    
    public int getSapiCount() {
        String countQuery = "SELECT  * FROM " + Sapi.TABLE_NAME;
        SQLiteDatabase db = dbExist;
        Cursor cursor = db.rawQuery(countQuery, null);
     
        int count = cursor.getCount();
        cursor.close();
     
     
        // return count
        return count;
    }
}
