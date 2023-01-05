package com.sparrow.doctor.Local_database;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.sparrow.doctor.Model.User;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "doctor.db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "Login";
    // below variable is for our id column.  46
    private static final String ID_COL = "id";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME + " TEXT,"
                + PASSWORD + " TEXT"+

        ")";
/*
        String query1 = "CREATE TABLE " + REPORT_TABLE_NAME + " ("
                + REPORT_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REPORT_USER_ID_COL + " TEXT,"
                + REPORT_LAT_COL + " TEXT,"
                + REPORT_LOGI_COL + " TEXT,"
                + REPORT_IMEI_COL + " TEXT,"
                + REPORT_DATE_COL + " TEXT,"
                + REPORT_OPENKM_COL + " TEXT,"
                + REPORT_OPENKM_PHOTO_COL + " TEXT,"
                + REPORT_OPENING_LAT_COL + " TEXT,"
                + REPORT_OPENING_LOGI_COL + " TEXT,"
                + REPORT_CLOSEKM_COL + " TEXT,"
                + REPORT_CLOSEKM_PHOTO_COL + " TEXT,"
                + REPORT_CLOSEING_LAT_COL + " TEXT,"
                + REPORT_CLOSEING_LOGI_COL + " TEXT,"
                + REPORT_RREMARK_COL + " TEXT"+
                ")";
        String query2 = "CREATE TABLE " + NEXT_VISIT_TABLE_NAME + " ("
                + NEXTVISIT_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NEXTVISIT_PHOTO_COL + " TEXT,"
                + NEXTVISIT_USER_ID_COL + " TEXT,"
                + NEXTVISIT_DATE_COL + " TEXT,"
                + NEXTVISIT_TIME_COL + " TEXT,"
                + NEXTVISIT_REMARK_COL + " TEXT,"
                + NEXTVISIT_LAT_COL + " TEXT,"
                + NEXTVISIT_LOGI_COL + " TEXT,"
                + NEXTVISIT_VISIT_ID_COL + " TEXT,"
                + NEXTVISIT_NEXT_DATE_COL + " TEXT,"
                + NEXTVISIT_NEXT_TIME_COL + " TEXT,"
                + NEXTVISIT_IMEI_COL + " TEXT"+
                ")";
*/
        db.execSQL(query);
     /*   db.execSQL(query1);
        db.execSQL(query2);*/
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(PASSWORD, user.getPassword());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                ID_COL,
                USER_NAME,
                PASSWORD
        };
        // sorting orders
        String sortOrder =
                USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(PASSWORD, user.getPassword());
        // updating row
        db.update(TABLE_NAME, values, ID_COL + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_NAME, ID_COL + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String username) {
        // array of columns to fetch
        String[] columns = {
                ID_COL
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = USER_NAME + " = ?";
        // selection argument
        String[] selectionArgs = {username};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String username, String password) {
        // array of columns to fetch
        String[] columns = {
                ID_COL
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = USER_NAME + " = ?" + " AND " + PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {username, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }



    public void AddLoginDetails(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, username);
        values.put(PASSWORD, password);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
