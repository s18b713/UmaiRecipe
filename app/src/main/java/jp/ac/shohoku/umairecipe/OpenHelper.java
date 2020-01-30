package jp.ac.shohoku.umairecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;



/**
 * SQLiteの基本設定
 */
public class OpenHelper extends SQLiteOpenHelper {
    // データベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データベース情報を変数に格納
    // DB名
    private static final String DATABASE_NAME = "UmaiRecipeDB.db";
    // その他
    private static final String TABLE_NAME = "umaidb";
    private static final String _ID = "_id";
    private static final String COLUMN_MENU = "menu";
    private static final String COLUMN_MAT = "mat";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_LIKE = "fav";

    private static final String W_TABLE_NAME ="umaiweek";
    private static final String W_ID = "w_id";
    private static final String SHERE_ID = "umaiid";

    //テーブル作成のSQL文
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME +
                    " (" + _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_MENU + " TEXT," +
                    COLUMN_MAT + " TEXT," +
                    COLUMN_URL + " TEXT," +
                    COLUMN_LIKE + " INTEGER)";

    private static final String W_SQL_CREATE_ENTRIES =
            "CREATE TABLE " + W_TABLE_NAME +
                    " (" + W_ID + " INTEGER PRIMARY KEY," +
                    SHERE_ID + " INTEGER)";

    //テーブル削除のSQL文
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String W_SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + W_TABLE_NAME;

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("debug", "OpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //テーブルの作成
        // ファイルがなければ作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        savedefoData(db);


        db.execSQL(
                W_SQL_CREATE_ENTRIES
        );
        saveWData(db);

        Log.d("debug", "onCreate(SQLiteDatabase db)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //アプデの判別。古いverは削除して新規作成
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        db.execSQL(
                W_SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveData(SQLiteDatabase db, String menu, String mat) {
        saveData(db, menu, mat, "-");
    }

    public void saveData(SQLiteDatabase db, String menu, String mat, String url) {
        saveData(db, menu, mat, url, 0);
    }

    public void saveData(SQLiteDatabase db, String menu, String mat, String url, int fav) {
        ContentValues values = new ContentValues();
        values.put("menu", menu);
        values.put("mat", mat);
        values.put("url", url);
        values.put("fav", fav);
        db.insert("umaidb", null, values);
        Log.d("debug", "saveData");
    }


    public void saveWData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        ArrayList<Integer> list = new ArrayList<Integer>(); // 曜日ごとにメニューを指定するためのリスト

        int umaiid;
        Log.d("debug","**********Cursor");
        Cursor cursor = db.query(
                "umaidb",
                new String[] { "_id"},
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        //メニューがあるだけリストに数字を入れる
        for (int i = 1; i <= cursor.getCount(); i++){
            list.add(i);
        }
        //リストをシャッフルする
        Collections.shuffle(list);
        //結果の最初から7つをumaiweekに格納する
        for (int i = 0; i < 7; i++){
            umaiid = list.get(i);
            values.put("umaiid", umaiid);
            db.insert("umaiweek", null, values);
        }
        cursor.close();
        Log.d("debug", "saveWData");
    }

    //ここに初期データを入れる
    private void savedefoData(SQLiteDatabase db) {
        saveData(db, "menu1", "mat1", "url");
        saveData(db, "menu2", "mat2", "url");
        saveData(db, "menu3", "mat3", "url");
        saveData(db, "menu4", "mat4", "url");
        saveData(db, "menu5", "mat5", "url");
        saveData(db, "menu6", "mat6", "url");
        saveData(db, "menu7", "mat7", "url");
        saveData(db, "menu8", "mat8", "url");
        saveData(db, "menu9", "mat9", "url");
    }

}
