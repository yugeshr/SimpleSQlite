package ralli.yugesh.com.simplesqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yugesh on 2/28/2018.
 */

class DataHandler extends SQLiteOpenHelper{
    SQLiteDatabase sqLiteDatabase;

    public DataHandler(Context applicationContext) {
        super(applicationContext,"StudentDB",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table details(name text not null,number text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists details");
    }

    public void insertData(String name, String number){
        SQLiteDatabase obj = getWritableDatabase();
        obj.execSQL("insert into details values('"+name+"','"+number+"')");
    }

    public Cursor send(){
        SQLiteDatabase obj2 = getReadableDatabase();
        Cursor cr = obj2.rawQuery("select * from details",null);
        return cr;
    }
}
