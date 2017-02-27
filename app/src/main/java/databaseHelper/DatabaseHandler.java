package databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by som on 26/02/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "messengerDb9";
    private static final String TABLE_SENDER = "senderName1";

    private static final String KEY_ID = "id";
    private static final String KEY_SENDER_NUMBER = "senderNumber";


    private static final String TABLE_SENDER_MSG = "msg3";

    private static final String KEY_ID2 = "id";
    private static final String KEY_SENDER_NUMBER2 = "senderNumber";
    private static final String KEY_SENDER_MSG="msg3";





    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //table1 .
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_SENDER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SENDER_NUMBER + " TEXT"+")";
        db.execSQL(CREATE_MOVIES_TABLE);
        Log.v("messagedb senderdb  created",": ok");


        //table 2.

        String CREATE_MOVIES_TABLE2 = "CREATE TABLE " + TABLE_SENDER_MSG + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY," + KEY_SENDER_NUMBER2 + " TEXT,"+ KEY_SENDER_MSG + " TEXT"+")";
        db.execSQL(CREATE_MOVIES_TABLE2);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENDER);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_SENDER_MSG);
        onCreate(db);
        // Create tables again








    }





    //to add the messages sender name .......
    public  void addMessage(Model message) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SENDER_NUMBER, message.getSenderNumber()); //sender name....

        // Inserting Row
        db.insert(TABLE_SENDER, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }



    //to add the messages sender name .......2
    public  void addMessage2(Model2 message) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SENDER_NUMBER, message.getSenderNumber()); //sender name....
        values.put(KEY_SENDER_MSG,message.getMsg());
        // Inserting Row
        db.insert(TABLE_SENDER_MSG, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }












    //code to add the single Message.....
    public  Model getMessage(Model model) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SENDER, new String[] { KEY_ID,
                        KEY_SENDER_NUMBER}, KEY_SENDER_NUMBER + "=?",
                new String[] { String.valueOf(model.getSenderNumber()) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

       // Model movieData = new Model(Integer.parseInt(cursor.getString(0)));
        Model model1=new Model(Integer.parseInt(cursor.getString(0)),cursor.getString(1));

        // return movie

        return model1;
    }






    //code to add the single Message.....2
    public  Model2 getMessage2(Model2 model) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SENDER_MSG, new String[] { KEY_ID,
                        KEY_SENDER_NUMBER,KEY_SENDER_MSG}, KEY_SENDER_NUMBER + "=?",
                new String[] { String.valueOf(model.getSenderNumber()) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        // Model movieData = new Model(Integer.parseInt(cursor.getString(0)));
        Model2 model1=new Model2(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

        // return movie

        return model1;
    }






















    // code to get all message in a list view
    public List<Model> getAllMessage() {
        List<Model> messageList = new ArrayList<Model>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SENDER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setSenderName(cursor.getString(1));
                // Adding contact to list
                messageList.add(model);
            } while (cursor.moveToNext());
        }

        // return movie list
        return messageList;
    }






    // code to get all message in a list view2
    public List<Model2> getAllMessage2() {
        List<Model2> messageList = new ArrayList<Model2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SENDER_MSG;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model2 model = new Model2();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setSenderName(cursor.getString(1));
                model.setMsg(cursor.getString(2));
                // Adding contact to list
                messageList.add(model);
            } while (cursor.moveToNext());
        }

        // return movie list
        return messageList;
    }
















    // code to update the single Movie
    public int updateMessage(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, model.getId()); //movie id
        values.put(KEY_SENDER_NUMBER,model.getSenderNumber()); // number........

        // updating row
        return db.update(TABLE_SENDER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(model.getId()) });
    }




    // code to update the single Movie2
    public int updateMessage(Model2 model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, model.getId()); //movie id
        values.put(KEY_SENDER_NUMBER,model.getSenderNumber()); // number........
        values.put(KEY_SENDER_MSG,model.getMsg()); // Message........

        // updating row
        return db.update(TABLE_SENDER_MSG, values, KEY_ID + " = ?",
                new String[] { String.valueOf(model.getId()) });
    }


















    // Deleting single Movie
    public void deleteMessage(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SENDER, KEY_SENDER_NUMBER + " = ?", new String[] { String.valueOf(model.getSenderNumber()
        ) });
        // db.execSQL("delete from "+ TABLE_MOVIES);
        db.close();
    }



    // Deleting single Movie2
    public void deleteMessage(Model2 model) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SENDER_MSG, KEY_SENDER_NUMBER + " = ?", new String[] { String.valueOf(model.getSenderNumber()
        ) });
        // db.execSQL("delete from "+ TABLE_MOVIES);
        db.close();
    }















       // Getting contacts Count
    public int getMessageCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SENDER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }



    // Getting contacts Count2
    public int getMessageCount2() {
        String countQuery = "SELECT  * FROM " + TABLE_SENDER_MSG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }







}
