package com.example.kaew.listofdream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaew on 3/12/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dream_db",
                                TABLE_NAME = "dream_table",
                                KEY_ID = "id",
                                KEY_ACHIEVE = "achieve",
                                KEY_DREAM = "dream",
                                KEY_COMMENT = "comment";

    public DatabaseHandler(Context context)
    {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME;
        query += "(";
        query += (KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query += (KEY_ACHIEVE + " INTEGER, ");
        query += (KEY_DREAM + " TEXT, ");
        query += (KEY_COMMENT + " TEXT ");
        query += ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertDream(DreamEntity entity) throws SQLException
    {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACHIEVE, entity.getAchieve());
        values.put(KEY_DREAM, entity.getDream());
        values.put(KEY_COMMENT, entity.getComment());

        long rowAffected = db.insert(TABLE_NAME,null, values);
        return rowAffected;
    }

    public long updateDream(DreamEntity entity) throws SQLException
    {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACHIEVE, entity.getAchieve());
        values.put(KEY_DREAM, entity.getDream());
        values.put(KEY_COMMENT, entity.getComment());

        long rowAffected = db.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{ String.valueOf(entity.getID())});
        return rowAffected;
    }

    public long deleteDream(DreamEntity entity) throws SQLException
    {
        SQLiteDatabase db = super.getWritableDatabase();

        long rowAffected = db.delete(TABLE_NAME,KEY_ID + "=?", new String[]{ String.valueOf(entity.getID())});
        return rowAffected;
    }

    private List<DreamEntity> selectDream(DreamEntity entityCondition) throws SQLException
    {
        SQLiteDatabase db = super.getReadableDatabase();
        List<DreamEntity> lstDreamEntity = new ArrayList<DreamEntity>();

        String query = ("SELECT * FROM " + TABLE_NAME);
        if(entityCondition!=null){
            query +=  (" WHERE " + KEY_ID + "=" + entityCondition.getID());
        }

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do{
                boolean achieve = (cursor.getInt(1) == 1);
                DreamEntity entity = new DreamEntity(cursor.getInt(0), achieve, cursor.getString(2), cursor.getString(3));

                lstDreamEntity.add(entity);
            }while(cursor.moveToNext());
        }
        return lstDreamEntity;
    }

    public DreamEntity selectOneDream(DreamEntity entityCondition) throws SQLException {
        return selectDream(entityCondition).get(0);
    }

    public List<DreamEntity> selectAllDream() throws SQLException {
        return selectDream(null);
    }
}
