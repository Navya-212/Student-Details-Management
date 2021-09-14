package com.example.s2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Currency;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME= "studentsManager";
    private static final String TABLE_STUDENTS= "students";
    private static final String REG_NO= "reg_no";
    private static final String NAME= "name";
    private static final String BRANCH= "branch";
    private static final String MARKS= "marks";

    public DataBaseHandler(Context context){
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_STUDENTS + "(" + REG_NO + "TEXT PRIMARY KEY, " + NAME + "TEXT,"
                + BRANCH + "TEXT," + MARKS + "INTEGER" + ")" ;
        //create table tablename(regno text primary key,name text,branch text, marks integer);
        db.execSQL(query); // to execute above query

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

        void addStudent(Student student) {
            SQLiteDatabase db=this.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(REG_NO, student.getRegNo());
            values.put(NAME, student.getName());
            values.put(BRANCH, student.getBranch());
            values.put(MARKS, student.getMarks());

            db.insert(TABLE_STUDENTS, null, values);
            db.close();
        }

        Student getStudent(String regNo){
        SQLiteDatabase db= this.getReadableDatabase();

            Cursor cursor= db.query(TABLE_STUDENTS, new String[]{
                    REG_NO, NAME, BRANCH, MARKS
            }, REG_NO +"=?", new String[]{ regNo }, null,null,null,null);
            if(cursor !=  null){
                cursor.moveToFirst();
            }

            Student student =new Student(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
            return student;
        }
}
