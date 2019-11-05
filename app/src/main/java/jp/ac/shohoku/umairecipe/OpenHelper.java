package jp.ac.shohoku.umairecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * SQLiteの基本設定
 */
public class OpenHelper extends SQLiteOpenHelper {
    // データベースのバージョン
    private static final int DATABASE_VERSION = 3;

    // データベース情報を変数に格納
    private static final String DATABASE_NAME = "UmaiRecipeDB.db";
    private static final String TABLE_NAME = "menudb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_SUBTITLE = "material";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME +
                "(" + _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_TITLE + " TEXT," +
                COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String  SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    OpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //テーブルの作成
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        //デフォルトで記入される
        saveData(db, "munu1", "mat1-1 mat1-2 mat1-3");
        saveData(db, "munu2", "mat2-1 mat2-2 mat2-3");
        saveData(db, "munu3", "mat3-1 mat3-2 mat3-3");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //アプデの判別。古いverは削除して新規作成
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void  onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveData(SQLiteDatabase db, String title, String material){
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("material", material);

        db.insert("menudb", null, values);
    }

}
