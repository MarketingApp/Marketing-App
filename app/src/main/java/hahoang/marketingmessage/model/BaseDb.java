package hahoang.marketingmessage.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by Hoang Ha on 7/4/2017.
 */

public abstract class BaseDb {
    public static String INTEGER_PRIMARY_KEY_TYPE = " integer primary key autoincrement";
    public static String TEXT_TYPE = " text ";
    public static String INTEGER_TYPE = " integer  ";
    protected Context mContext;
    protected SQLiteDatabase mSqLiteDatabase;
    protected int VERSION = 1;
    protected String TABLE_NAME;
    protected String CREATE_TABLE;
    protected String DROP_TABLE;
    protected String[] columns;
    protected MySqlOpenHelper mySqlOpenHelper;

    protected void clear() {
        mSqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    abstract void insert(Object object);

    abstract void insert(ArrayList<Object> arr);

    public void delete(int id) {
        mSqLiteDatabase.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
    }

    abstract Object getObject(int id);

    abstract ArrayList<Group> getAll();

    abstract void init();

    public class MySqlOpenHelper extends SQLiteOpenHelper {

        public MySqlOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}
