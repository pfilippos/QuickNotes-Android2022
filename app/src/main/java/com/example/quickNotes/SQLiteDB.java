package com.example.quickNotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notesDataBase";
    private static final String DATABASE_TABLE = "notesDbTable";


    private static final String KEY_id = "id";
    private static final String KEY_notetext = "notetext";
    private static final String KEY_notetitle = "notetitle";
    private static final String KEY_time = "time";
    private static final String KEY_date = "date";

    SQLiteDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //Craeting the database table
         String currentque = "CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE + "("+ KEY_id+" INT PRIMARY KEY,"+
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

    public long NoteaddFunc(Note note){
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

    public long NoteupdFunc(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_notetitle,note.getTitle());
        contentValues.put(KEY_notetext,note.getText());
        contentValues.put(KEY_time,note.getTime());
        contentValues.put(KEY_date,note.getDate());

        //Update the row
        long ID = db.update(DATABASE_TABLE,contentValues,KEY_notetitle+" = ?",new String[]{note.getTitle()});
        Log.d("Updated", "\" "+ID +" \"");

        return ID;
    }

    public long NotedelFunc(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_notetitle,note.getTitle());
        contentValues.put(KEY_notetext,note.getText());
        contentValues.put(KEY_time,note.getTime());
        contentValues.put(KEY_date,note.getDate());

        //Delete the row
        long ID = db.delete(DATABASE_TABLE,KEY_notetitle+" = ?",new String[]{note.getTitle()});
        Log.d("Deleted", "\" "+ID +" \"");

        return ID;
    }

    public Note NotegetFunc(long ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,new String[]{KEY_id,KEY_notetitle,KEY_notetext,KEY_date,KEY_time},KEY_id+"=?",new String[]{String.valueOf(ID)},null,null,null);  //db pointer

        if(cursor !=null){
            cursor.moveToFirst();
        }

        Note note = new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4)); //get those columns basically

        return note;
    }

    public List<Note> getNotesList(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> noteslist = new ArrayList<>();
        String que = "SELECT * FROM "+DATABASE_TABLE;
        Cursor cursor = db.rawQuery(que,null);

        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));
                noteslist.add(note);
            }while (cursor.moveToNext());
        }

        return noteslist;
    }

    public void ClearAllNotes(String Table_Name){
        Log.d("patithike","tha ginei clear");
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+Table_Name;
        db.execSQL(clearDBQuery);
    }

}
