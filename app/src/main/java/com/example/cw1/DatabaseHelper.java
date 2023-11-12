package com.example.cw1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contact_details";
    private static final String ID_COLUMN_NAME = "person_id";
    private static final String NOH_COLUMN_NAME = "noh";
    private static final String LOCATION_COLUMN_NAME = "location";
    private static final String DOTH_COLUMN_NAME = "doth";
    private static final String PA_COLUMN_NAME = "pa";
    private static final String LTH_COLUMN_NAME = "lth";
    private static final String LOD_COLUMN_NAME = "lod";
    private static final String D_COLUMN_NAME = "d";
    private SQLiteDatabase database;

    private static final String DATABASE_CREATE_QUERY = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
            DATABASE_NAME,ID_COLUMN_NAME,NOH_COLUMN_NAME,LOCATION_COLUMN_NAME,DOTH_COLUMN_NAME,PA_COLUMN_NAME,LTH_COLUMN_NAME,
            LOD_COLUMN_NAME,D_COLUMN_NAME);

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        Log.w(this.getClass().getName(), DATABASE_NAME + " database upgrade to version"
                + newVersion + " - old data lost");
        onCreate(db);
    }

    public long insertDetails(String noh, String location, String doth, String pa, String lth, String lod, String d){
        ContentValues rowValues = new ContentValues();
        rowValues.put(NOH_COLUMN_NAME, noh);
        rowValues.put(LOCATION_COLUMN_NAME, location);
        rowValues.put(DOTH_COLUMN_NAME, doth);
        rowValues.put(PA_COLUMN_NAME, pa);
        rowValues.put(LTH_COLUMN_NAME, lth);
        rowValues.put(LOD_COLUMN_NAME, lod);
        rowValues.put(D_COLUMN_NAME, d);
        return database.insertOrThrow(DATABASE_NAME, null,rowValues);
    }

    public String getDetails(){
        Cursor results = database.query("contact_details",
                new String[]{"person_id","noh","location","doth","pa","lth","lod","d"},
                null, null, null , null, null,
                null);
        String resultText = " ";
        results.moveToFirst();
        while (!results.isAfterLast()){
            int id = results.getInt(0);
            String noh = results.getString(1);
            String location = results.getString(2);
            String doth = results.getString(3);
            String pa = results.getString(4);
            String lth = results.getString(5);
            String lod = results.getString(6);
            String d = results.getString(7);

            resultText += id + " " + noh + " " + location + " " + doth + " " + pa + " " + lth + " " + lod + " " + d + "\n";
            results.moveToNext();
        }
        return resultText;
    }



}
