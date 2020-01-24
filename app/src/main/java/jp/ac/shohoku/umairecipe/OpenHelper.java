package jp.ac.shohoku.umairecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * SQLiteの基本設定
 */
public class OpenHelper extends SQLiteOpenHelper {
    // データベースのバージョン
    private static final int DATABASE_VERSION = 3;

    // データベース情報を変数に格納
    // DB名
    private static final String DATABASE_NAME = "UmaiRecipeDB.db";
    // その他
    private static final String TABLE_NAME = "umaidb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_TITLE = "menu";
    private static final String COLUMN_NAME_SUBTITLE = "mat";



    //テーブル作成のSQL文
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME +
                    " (" + _ID + " INTEGER PRIMARY KEY,"
                    +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_SUBTITLE + " TEXT," +
                    COLUMN_NAME_SUBTITLE + " TEXT)";

    //テーブル削除のSQL文
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //テーブルの作成
        // ファイルがなければ作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        //ここにデフォルトのメニューを入れる
        saveData(db, "menu1", "mat1", "url");
        saveData(db, "menu2", "mat2", "url");


        Log.d("debug", "onCreate(SQLiteDatabase db)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //アプデの判別。古いverは削除して新規作成
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void saveData(SQLiteDatabase db, String menu, String mat, String url) {
        saveData(db, menu, mat, url, 0);
    }

    public void saveData(SQLiteDatabase db, String menu, String mat, String url, int like) {
        ContentValues values = new ContentValues();
        values.put("manu", menu);
        values.put("mat", mat);
        values.put("url", url);
        values.put("like", like);
        db.insert("umaidb", null, values);
    }
}
