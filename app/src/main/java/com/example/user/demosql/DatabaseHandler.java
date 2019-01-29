package com.example.user.demosql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static String KEY_TABLE = "Employee";
    private static String KEY_ID = "id";
    private static String KEY_USERNAME = "Username";
    private static String KEY_MOBILE = "Mobile";
    private static String KEY_ADDRESS = "Address";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String Create = "CREATE TABLE "+KEY_TABLE+"("+KEY_ID+" INTEGER PRIMARY KEY ,"+KEY_USERNAME+" TEXT,"
                +KEY_MOBILE+" TEXT,"+KEY_ADDRESS+" TEXT"+")";
        db.execSQL(Create);

    }

    public int insertdata(EmployeeModel employeeModel)
    {
        int i = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME,employeeModel.getUsername());
        contentValues.put(KEY_MOBILE,employeeModel.getMobile());
        contentValues.put(KEY_ADDRESS,employeeModel.getAddress());
        sqLiteDatabase.insert(KEY_TABLE,null,contentValues);
        i = 1;
        return i;
    }

    public ArrayList<EmployeeModel> getAllDetails()
    {
        String fetchall = "select * from "+KEY_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor result  = sqLiteDatabase.rawQuery(fetchall,null);
        ArrayList<EmployeeModel> list = new ArrayList<EmployeeModel>();

        if (result.moveToFirst())
        {
            do {
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId(result.getString(0));
                employeeModel.setUsername(result.getString(1));
                employeeModel.setMobile(result.getString(2));
                employeeModel.setAddress(result.getString(3));
                list.add(employeeModel);

            }while (result.moveToNext());

        }
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
