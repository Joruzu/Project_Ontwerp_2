package com.example.supermario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "guide.db";
    public static final String GROET_TABLE_NAME = "groet";
    public static final String GROET_COLUMN_ID = "id";
    public static final String GROET_COLUMN_NED = "Nederlands";
    public static final String GROET_COLUMN_SAR = "Sarnami";
    public static final String GROET_COLUMN_SPEECH = "Speech";

    //Create database in constructor function
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    //Create empty table on creation of app
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE groet "+
                    "(id INTEGER PRIMARY KEY NOT NULL, Nederlands TEXT NOT NULL, Sarnami TEXT NOT NULL, Speech INT)");  //Change speech int to other variable depending
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS groet");
        onCreate(db);
    }

    public void insertGroet() throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        File file = new File("/home/dinesh/AndroidStudioProjects/SuperMario/app/src/main/java/com/example/supermario/tables/");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String var1=" ", var2=" ";
        int d = 1;


        while (var1 != null || var2 != null){
            for (int q=0; q<2; q++){
                if (q%2==0)
                    var1 = br.readLine();
                else
                    var2 = br.readLine();
            }
            if (var1==null || var2 ==null)
                break;
            System.out.println(var1 + "------" + var2 );

            contentValues.put(GROET_COLUMN_ID, d);
            contentValues.put(GROET_COLUMN_NED, var1);
            contentValues.put(GROET_COLUMN_SAR, var2);
            contentValues.put(GROET_COLUMN_SPEECH, 0);
            db.insert(GROET_TABLE_NAME, null, contentValues);
            d++;
        }
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from groet where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, GROET_TABLE_NAME);
    }

    public ArrayList<String> getAll() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(GROET_COLUMN_NED)));
            res.moveToNext();
        }
        return array_list;
    }
}