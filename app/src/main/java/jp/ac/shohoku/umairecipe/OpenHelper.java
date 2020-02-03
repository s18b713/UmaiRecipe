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
        saveData(db, "うどん", "うどん\n油揚げ\nねぎ\n水\nしょうゆ\nほんだし\n酒\n砂糖\n塩\n", "https://park.ajinomoto.co.jp/recipe/card/705935/");
        saveData(db, "そば", "そば\nねぎ\n生姜\n水\nほんだし\nみりん\nしょうゆ\n塩\n", "https://park.ajinomoto.co.jp/recipe/card/305071/");
        saveData(db, "そうめん", "そうめん\nきゅうり\nロースハム\nみょうが\nトマト\nゆで卵\nほんだし\n塩\n氷水\nオリーブオイル\n", "https://park.ajinomoto.co.jp/recipe/card/706626/");
        saveData(db, "牛丼", "牛薄切り肉\n玉ねぎ\nご飯\n紅生姜\n水\nほんだし\nしょうゆ\nみりん\n", "https://park.ajinomoto.co.jp/recipe/card/704014/");
        saveData(db, "すき焼き", "牛肉(すき焼き用)\nしいたけ\n春菊\n長ねぎ\n絹ごし豆腐\n白滝\n水\nしょうゆ\n酒\nほんだし\n塩\n卵\nサラダ油\n", "https://park.ajinomoto.co.jp/recipe/card/707297/");
        saveData(db, "肉じゃが", "じゃがいも\nにんじん\n玉ねぎ\nサラダ油\n水\nほんだし\n砂糖\nしょうゆ\nみりん\n塩\n", "https://park.ajinomoto.co.jp/recipe/card/709425/");
        saveData(db, "生姜焼き", "豚肉\n薄力粉\n酒\nしょうゆ\nしょうが(すりおろし)\n砂糖\nキャベツ\nサラダ油\n", "https://park.ajinomoto.co.jp/recipe/card/706344/");
        saveData(db, "とんかつ", "豚ロース\n卵\nにんじん\n塩\nコショウ\n薄力粉\nパン粉\nサラダ油\n", "https://park.ajinomoto.co.jp/recipe/card/703392/");
        saveData(db, "焼きそば", "焼きそば用麺\n豚バラ薄切り肉\n玉ねぎ\nにんじん\nもやし\nキャベツ\n塩\nコショウ\n酒\nとんかつソース\nウスターソース\n青のり\n紅生姜\n", "https://park.ajinomoto.co.jp/recipe/card/701165/");
        saveData(db, "炊き込みご飯","米\n水\nゆでたけのこ\n油揚げ\nしょうゆ\nほんだし\n塩\n", "https://park.ajinomoto.co.jp/recipe/card/709200/");
        saveData(db, "ハンバーグ", "ひき肉\nコンソメ\nコショウ\n玉ねぎ\nパン粉\n牛乳\n卵\nもやし\nケチャップ\n中濃ソース\n酒\nサラダ油\n", "https://park.ajinomoto.co.jp/recipe/card/707446/");
        saveData(db, "オムライス", "鶏むね肉\n玉ねぎ\nご飯\nコンソメ\nトマトケチャップ\n卵\nコショウ\n塩\n油\nバター\n", "https://park.ajinomoto.co.jp/recipe/card/706078/");
        saveData(db, "シチュー", "鶏もも肉\n塩\nじゃがいも\n玉ねぎ\nにんじん\nブロッコリー\n薄力粉\n牛乳\n水\nコンソメ\nローリエ\n塩\nコショウ\n白ワイン\nバター\n油\n", "https://park.ajinomoto.co.jp/recipe/card/706369/");
        saveData(db, "カレー", "豚肉\nにんじん\nじゃがいも\n玉ねぎ\nにんにく\n水\nカレールウ\nオイスターソース\nご飯\nサラダ油\n", "https://park.ajinomoto.co.jp/recipe/card/710131/");
        saveData(db, "ピラフ", "米\n水\nコンソメ\n玉ねぎ\nむきえび\nミックスベジタブル\nパプリカ\n酒\n塩\nコショウ\nバター\n", "https://park.ajinomoto.co.jp/recipe/card/707173/");
        saveData(db, "ドリア", "ご飯\n鶏もも肉\nしめじ\n玉ねぎ\n水\n薄力粉\n牛乳\nほんだし\n薄口しょうゆ\n塩\nコショウ\nチーズ\nオリーブオイル\nバター\n", "https://park.ajinomoto.co.jp/recipe/card/708250/");
        saveData(db, "グラタン", "マカロニ\n鶏もも肉\n玉ねぎ\nマッシュルーム\n薄力粉\nコンソメ\n牛乳\n水\n塩\n砂糖\nコショウ\nローリエ\n生クリーム\nチーズ\nバター\nサラダ油\n", "https://park.ajinomoto.co.jp/recipe/card/705647/");
        saveData(db, "コロッケ", "合いびき肉\nじゃがいも\n玉ねぎ\nバター\nコンソメ\n牛乳\n塩\nコショウ\n卵\n薄力粉\nパン粉\nサラダ油\nキャベツ\nウスターソース\n", "https://park.ajinomoto.co.jp/recipe/card/702704/");
        saveData(db, "ロールキャベツ", "合いびき肉\n玉ねぎ\nキャベツ\n生パン粉\n卵\n塩\nコショウ\nコンソメ\n水\n塩\nほんじお\nコショウ\nローリエ\nサラダ油\n", "https://park.ajinomoto.co.jp/recipe/card/705646/");
        saveData(db, "エビフライ", "えび\n卵\n塩\nコショウ\n薄力粉\nパン粉\nサラダ油\nタルタルソース\n", "https://park.ajinomoto.co.jp/recipe/card/703399/");
        saveData(db, "ラタトゥイユ","ズッキーニ\nなす\nセロリ\nコンソメ\n玉ねぎ\nパプリカ\nホールトマト缶\nにんにく\n白ワイン\nローリエ\n塩\nコショウ\nオリーブオイル\n", "https://park.ajinomoto.co.jp/recipe/card/705931/");
        saveData(db, "ムニエル", "生鮭\n油\nじゃがいも\nブロッコリー\nレモン(輪切り)\nパセリ(みじん切り)\n薄力粉\nコーン油\nバター\nコンソメ\n塩\nパプリカ(粉)\n", "https://park.ajinomoto.co.jp/recipe/card/701157/");
        saveData(db, "ラーメン", "中華麺\nもやし\nねぎ\nメンマ\nチャーシュー\n酒\nしょうゆ\n鶏がらスープ\n塩\nごま油\nコショウ\n水\n", "https://park.ajinomoto.co.jp/recipe/card/701012/");
        saveData(db, "餃子", "餃子の皮\n豚ひき肉\nキャベツ\nねぎ(みじん切り)\nしょうが(すりおろし)\nにんにく(すりおろし)\nオイスターソース\n鶏がらスープ\nサラダ油\n", "https://park.ajinomoto.co.jp/recipe/card/707175/");
        saveData(db, "チャーハン", "ご飯\n卵\n小ねぎ\n鶏がらスープ\nオイスターソース\nコショウ\nコーン油\n", "https://park.ajinomoto.co.jp/recipe/card/702669/");
        saveData(db, "エビチリ", "むきエビ\n片栗粉\nにんにく(みじん切り)\nねぎ(みじん切り)\n鶏がらスープ\nケチャップ\n水\n砂糖\n塩\n豆板醤\n酒\nサラダ油\nこま油\n", "https://park.ajinomoto.co.jp/recipe/card/706051/");
        saveData(db, "青椒肉絲", "豚もも薄切り肉\n片栗粉\nゆでたけのこ\nピーマン\n赤ピーマン\n酒\nオイスターソース\nコショウ\nサラダ油\nごま油\n", "https://park.ajinomoto.co.jp/recipe/card/706052/");
        saveData(db, "回鍋肉", "豚バラ肉\nキャベツ\n鶏がらスープ\n玉ねぎ\nにんにく\n唐辛子\nごま油\nサラダ油\n酒\n甜麺醤\nしょうゆ\n", "https://park.ajinomoto.co.jp/recipe/card/706048/");
        saveData(db, "麻婆豆腐", "木綿豆腐\n豚ひき肉\nねぎ(みじん切り)\nしょうが(みじん切り)\nにんにく(みじん切り)\nサラダ油\n豆板醤\n酒\nしょうゆ\n砂糖\n鶏がらスープ\n片栗粉\n甜麺醤\n水\n", "https://park.ajinomoto.co.jp/recipe/card/706036/");
        saveData(db, "天津飯", "かに\n卵\n酒\n塩\nコショウ\nしょうが(みじん切り)\n水\nしょうゆ\n砂糖\n鶏がらスープ\n片栗粉\nご飯\nコーン油\n", "https://park.ajinomoto.co.jp/recipe/card/701167/");
        saveData(db, "春巻き","春巻きの皮\n豚もも薄切り肉\nしいたけ\nゆでたけのこ\nピーマン\nにんじん\n春雨\n水\n片栗粉\n薄力粉\nオイスターソース\nしょうゆ\n", "https://park.ajinomoto.co.jp/recipe/card/709716/");
        saveData(db, "焼きビーフン", "ビーフン\n豚バラ薄切り肉\n玉ねぎ\nゆでたけのこ\nピーマン\nにんじん\n干ししいたけ\nしょうゆ\n酒\n塩\nコショウ\n水\n鶏がらスープ\n", "https://park.ajinomoto.co.jp/recipe/card/708206/");
        saveData(db, "棒棒鶏", "鶏むね肉\n水\n酒\n青ねぎ\nしょうがの皮\n塩\nきゅうり\nトマト\n練り白ごま\n砂糖\n酢\n醤油\n鶏肉の蒸し汁\n", "https://park.ajinomoto.co.jp/recipe/card/706050/");
        saveData(db, "八宝菜", "豚バラ薄切り肉\nえび\n白菜\nしめじ\nうずら卵\nブロッコリー\n鶏がらスープ\n水\n酒\n塩\n胡椒\n片栗粉\nサラダ油\nごま油\n", "https://park.ajinomoto.co.jp/recipe/card/706049/");



        Log.d("debug", "savedefData");
    }

}
