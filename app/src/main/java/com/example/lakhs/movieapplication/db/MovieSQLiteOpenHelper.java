package com.example.lakhs.movieapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lakhs.movieapplication.models.Movie;

/**
 * Created by lakhs on 3/25/2016.
 */
public class MovieSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String RATE = "rate";
    private static final String DESC = "desc";
    private static final String DIRECTOR = "director";
    private static final String DBNAME= "movie";
    private static final String IMAGE= "image";
    private static final String FAVMOVIE= "favmovie";
    // SingleMovieContext c=new SingleMovieContext();
    public MovieSQLiteOpenHelper(Context context) {
        super(context, DBNAME, null, 1);

    }

    @Override
        public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + FAVMOVIE + " (id INTEGER PRIMARY key AUTOINCREMENT, title TEXT, year TEXT, rate TEXT, desc TEXT, image TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  "+FAVMOVIE);
        onCreate(db);

    }
    public boolean insertData (int id, String title, String year,String rate, String desc,String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,title);
        contentValues.put(YEAR,year);
        contentValues.put(RATE,rate);
        contentValues.put(DESC,desc);
        contentValues.put(ID, id);
        contentValues.put(IMAGE, image);
        long b = db.insert(FAVMOVIE,null,contentValues);
        if (b==-1)
            return false;
        else
            return true;
    }
    public Integer deleteFav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(FAVMOVIE, "id = ? ", new String[] {id});

    }
    public  int i;
    public String[][]  getFavmovie(){
        SQLiteDatabase db = this.getWritableDatabase();
            String[][] array = new String[6][20];
            Movie y = new Movie();
            String s = "1";
            i=0;
            Cursor cursor = db.rawQuery("select * from favmovie",null);
            //int i=0;
          while (cursor.moveToNext()){
              array [0][i]=(cursor.getString(0));
              array [1][i]=(cursor.getString(1));
              array [2][i]=(cursor.getString(2));
              array [3][i]=(cursor.getString(3));
              array [4][i]=(cursor.getString(4));
              array [5][i]=(cursor.getString(5));

              i++;
          }
            cursor.close();
            db.close();
            System.out.println("ასდსდასდასდასდასდასდას: " +array[1][0]);
            return array;


        }



}
