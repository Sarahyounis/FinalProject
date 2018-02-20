package com.example.sarah.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sarah on 1/24/2018.
 */

public class DBHandling extends SQLiteOpenHelper{

    //database name
    private static DBHandling sInstance;
    public static final String DATABASE_NAME="gallery.db";
    public static final int DATABASE_VERSION=10;
    //data table name
    public static final String TABLE_PHOTO="photo";
    //column names
    public static final String COL_ID="id";
    public static final String COL_NAME="name";
    public static final String COL_PATH="path";
    public static final String COL_NOTE="note";

    public DBHandling(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized DBHandling getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHandling(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE_TABLE" + TABLE_PHOTO + "" +
                "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_PATH + " TEXT, "
                + COL_NOTE + " TEXT, "
                + ");";
        Log.d("QUERY", query);

        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO);
            db.execSQL(query);
        } catch (Exception e) {
            Log.d("Couldn't add table", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PHOTO);
        onCreate(db);

    }
    public void addCategory(Gallery_Downloads photo){
        //create ContentValue containning values to be inserted/updated in database in this case only on column
        ContentValues values = new ContentValues();

        values.put(COL_NAME, photo.getImv());
        values.put(COL_ID, photo.getId());
        values.put(COL_PATH, photo.getFilePath());
        values.put(COL_NOTE, photo.getStrNote());

        //create SQLiteDatabase object to enable writing/reading in database
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_PHOTO, null, values);
       photo.setId(id);//update the user ID according to the auto incremented id in the DB

        //logging for debugging purposes
        Log.d("ID ", "Category id: "+id+" added to DB");

        //close connection to database
        db.close();
    }

    public ArrayList<Gallery_Downloads> getData(){
        String[] r = new String[4];
        int[] col = new int[4];
        String query = "SELECT * FROM "+ TABLE_PHOTO ;//+ " WHERE " + COL_USERNAME+"='"+username+"'";

        ArrayList<Gallery_Downloads> pht= new ArrayList<Gallery_Downloads>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        col[0]=c.getColumnIndex(COL_ID);
        col[1]=c.getColumnIndex(COL_NAME);
        col[2]=c.getColumnIndex(COL_PATH);
        col[3]=c.getColumnIndex(COL_NOTE);

        while(!c.isAfterLast()){
            for(int i=0;i<col.length;i++){
                r[i]=c.getString(col[i]);
            }
           pht.add(new Gallery_Downloads(Long.parseLong(r[0]),r[2],r[3]));

            c.moveToNext();
        }
        return pht;
    }

}
