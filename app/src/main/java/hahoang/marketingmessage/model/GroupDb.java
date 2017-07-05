package hahoang.marketingmessage.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Hoang Ha on 7/4/2017.
 */

public class GroupDb extends BaseDb {
    public static String DATABASE_NAME = "GroupDb";
    private static GroupDb groupDb;
    private ContentValues contentValues;

    private GroupDb(Context context) {
        mContext = context;
        init();
        mySqlOpenHelper = new MySqlOpenHelper(mContext, DATABASE_NAME, null, VERSION);
        mSqLiteDatabase = mySqlOpenHelper.getWritableDatabase();
    }

    public synchronized static GroupDb getInstance(Context context) {
        if (groupDb == null)
            groupDb = new GroupDb(context);
        return groupDb;
    }
    @Override
    public void insert(Object object) {
        Group group = (Group) object;
        contentValues = new ContentValues();
        contentValues.put(GroupColumn.ID, group.getId());
        contentValues.put(GroupColumn.NAME, group.getName());
        contentValues.put(GroupColumn.CREATED_AT, group.getCreated_at());
        contentValues.put(GroupColumn.UPDATE_AT, group.getUpdate_at());
        contentValues.put(GroupColumn.STATUS, group.getStatus());
        contentValues.put(GroupColumn.TEXT, group.getText());
        contentValues.put(GroupColumn.USER_ID, group.getUser_id());
        mSqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void insert(ArrayList<Object> arr) {
        for (Object a : arr
                ) {
            insert(a);
        }
    }

    @Override
    public Group getObject(int id) {
        Group group = new Group();
        Cursor cursor = mSqLiteDatabase.query(TABLE_NAME, columns, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            group.setId(cursor.getInt(cursor.getColumnIndex(GroupColumn.ID)));
            group.setName(cursor.getString(cursor.getColumnIndex(GroupColumn.NAME)));
            group.setCreated_at(cursor.getInt(cursor.getColumnIndex(GroupColumn.CREATED_AT)));
            group.setUpdate_at(cursor.getInt(cursor.getColumnIndex(GroupColumn.UPDATE_AT)));
            group.setStatus(cursor.getInt(cursor.getColumnIndex(GroupColumn.STATUS)));
            group.setText(cursor.getString(cursor.getColumnIndex(GroupColumn.TEXT)));
            group.setUser_id(cursor.getInt(cursor.getColumnIndex(GroupColumn.USER_ID)));
        }

        return group;
    }

    @Override
   public ArrayList<Group> getAll() {
        ArrayList<Group> groups = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            Group group;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                group = new Group();
                group.setId(cursor.getInt(cursor.getColumnIndex(GroupColumn.ID)));
                group.setName(cursor.getString(cursor.getColumnIndex(GroupColumn.NAME)));
                group.setCreated_at(cursor.getInt(cursor.getColumnIndex(GroupColumn.CREATED_AT)));
                group.setUpdate_at(cursor.getInt(cursor.getColumnIndex(GroupColumn.UPDATE_AT)));
                group.setStatus(cursor.getInt(cursor.getColumnIndex(GroupColumn.STATUS)));
                group.setText(cursor.getString(cursor.getColumnIndex(GroupColumn.TEXT)));
                group.setUser_id(cursor.getInt(cursor.getColumnIndex(GroupColumn.USER_ID)));
                groups.add(group);
                cursor.moveToNext();
            }
        }
        return groups;
    }

    @Override
    void init() {
        TABLE_NAME = "group_table";
        CREATE_TABLE = " create table " + TABLE_NAME + "( "
                + GroupColumn.ID + INTEGER_TYPE + ","
                + GroupColumn.NAME + TEXT_TYPE + ","
                + GroupColumn.CREATED_AT + INTEGER_TYPE + ","
                + GroupColumn.UPDATE_AT + INTEGER_TYPE + ","
                + GroupColumn.STATUS + INTEGER_TYPE + ","
                + GroupColumn.TEXT + TEXT_TYPE + ","
                + GroupColumn.USER_ID + INTEGER_TYPE + ")";
        DROP_TABLE = "drop table if exists " + TABLE_NAME;
        columns = new String[]{
                GroupColumn.ID,
                GroupColumn.NAME,
                GroupColumn.CREATED_AT,
                GroupColumn.UPDATE_AT,
                GroupColumn.STATUS,
                GroupColumn.TEXT,
                GroupColumn.USER_ID
        };
    }
}
