package com.example.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteSimpleDataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notesDataBase";
    private static final String DATABASE_TABLE = "notesDbTable";


    private static final String KEY_id = "id";
    private static final String KEY_notetext = "notetext";
    private static final String KEY_notetitle = "notetitle";
    private static final String KEY_time = "time";
    private static final String KEY_date = "date";

    NoteSimpleDataBase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //Craeting the database table
         String currentque = "CREATE TABLE "+ DATABASE_TABLE + "("+ KEY_id+" INT PRIMARY KEY,"+
                 KEY_notetitle + " TEXT,"+
                 KEY_notetext + " TEXT,"+
                 KEY_date + " TEXT,"+
                 KEY_time + " TEXT"+")";

         db.execSQL(currentque);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldver, int newver) {

        if(oldver >= newver){
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
        onCreate(db);

    }

    public long NoteaddFunc(AnoteClass note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_notetitle,note.getTitle());
        contentValues.put(KEY_notetext,note.getText());
        contentValues.put(KEY_time,note.getTime());
        contentValues.put(KEY_date,note.getDate());

        long ID = db.insert(DATABASE_TABLE,null,contentValues);
        Log.d("Inserted","ID ->"+ID);

        return ID;
    }

    public AnoteClass NotegetFunc(long ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,new String[]{KEY_id,KEY_notetitle,KEY_notetext,KEY_date,KEY_time},KEY_id+"=?",new String[]{String.valueOf(ID)},null,null,null);  //db pointer

        if(cursor !=null){
            cursor.moveToFirst();
        }

        AnoteClass anoteClass = new AnoteClass(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4)); //get those columns basically

        return anoteClass;
    }

    public List<AnoteClass> getNotesList(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<AnoteClass> noteslist = new ArrayList<>();
        String que = "SELECT * FROM "+DATABASE_TABLE;
        Cursor cursor = db.rawQuery(que,null);

        if(cursor.moveToFirst()){
            do{
                AnoteClass anoteClass = new AnoteClass();
                anoteClass.setID(cursor.getLong(0));
                anoteClass.setTitle(cursor.getString(1));
                anoteClass.setText(cursor.getString(2));
                anoteClass.setDate(cursor.getString(3));
                anoteClass.setTime(cursor.getString(4));
                noteslist.add(anoteClass);
            }while (cursor.moveToNext());
        }

        return noteslist;
    }

}
