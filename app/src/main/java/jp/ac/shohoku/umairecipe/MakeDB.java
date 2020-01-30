package jp.ac.shohoku.umairecipe;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;


public class MakeDB {
    private OpenHelper helper;
    private SQLiteDatabase db;

    public  MakeDB(Activity activity) {
        if (helper  == null){
            helper = new OpenHelper(activity.getApplicationContext());
        }
        if (db == null){
            db = helper.getWritableDatabase();
        }

    }

    //read
    public void readAllData(Activity activity, TextView textView, TextView textView2){
        if(helper == null){
            helper = new OpenHelper(activity.getApplicationContext());
        }
        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "umaidb",
                new String[] {"_id", "menu", "mat", "url", "fav"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append("    ");
            sbuilder.append(cursor.getString(1));
            sbuilder.append(": ");
            sbuilder.append(cursor.getString(2));
            sbuilder.append("    url=");
            sbuilder.append(cursor.getString(3));
            sbuilder.append("    ");
            if (cursor.getInt(4) == 1){
                sbuilder.append("fav");
            }else {
                sbuilder.append("unfav");
            }
            sbuilder.append("\n");
            cursor.moveToNext();
        }
        cursor.close();

        Cursor cursor2 = db.query(
                "umaiweek",
                new String[] {"w_id", "umaiid"},
                null,
                null,
                null,
                null,
                null
        );

        cursor2.moveToFirst();

        StringBuilder sbuilder2 = new StringBuilder();

        for (int i = 0; i < cursor2.getCount(); i++) {
            sbuilder.append(cursor2.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor2.getString(1));
            sbuilder.append("\n");
            cursor2.moveToNext();
        }
        cursor2.close();


        Log.d("debug","**********"+sbuilder.toString());
        textView.setText(sbuilder.toString());
        textView2.setText(sbuilder2.toString());
    }

    public void readMenuData(Activity activity, TextView[] textViews){
        if(helper == null){
            helper = new OpenHelper(activity.getApplicationContext());
        }
        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "umaidb",
                new String[] { "_id", "menu"},
                null,
                null,
                null,
                null,
                null
        );
        Cursor cursorweek = db.query(
                "umaiweek",
                new String[]{"umaiid"},
                null,
                null,
                null,
                null,
                null
        );

        String string;
        int umaiid;
        int id;

        cursorweek.moveToFirst();
        for (int i = 0; i < cursorweek.getCount(); i++){
            umaiid = cursorweek.getInt(0);
            cursor.moveToFirst();
            for (int j = 0; j < cursor.getCount(); j++) {
                id = cursor.getInt(0);
                if (umaiid == id) {
                    string = cursor.getString(1);
                    textViews[i].setText(string);
                }
                cursor.moveToNext();
            }
            cursorweek.moveToNext();
        }
        cursor.close();
        cursorweek.close();
        Log.d("debug","**********weekmenus");
    }

    public void readMatData(Activity activity, TextView[] textViews){
        if(helper == null){
            helper = new OpenHelper(activity.getApplicationContext());
        }
        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "umaidb",
                new String[] { "_id", "mat"},
                null,
                null,
                null,
                null,
                null
        );
        Cursor cursorweek = db.query(
                "umaiweek",
                new String[]{"umaiid"},
                null,
                null,
                null,
                null,
                null
        );

        String string;
        int umaiid;
        int id;

        cursorweek.moveToFirst();
        for (int i = 0; i < cursorweek.getCount(); i++){
            umaiid = cursorweek.getInt(0);
            cursor.moveToFirst();
            for (int j = 0; j < cursor.getCount(); j++) {
                id = cursor.getInt(0);
                if (umaiid == id) {
                    string = cursor.getString(1);
                    textViews[i].setText(string);
                }
                cursor.moveToNext();
            }
            cursorweek.moveToNext();
        }
        cursor.close();
        cursorweek.close();
        Log.d("debug","**********weekmats");
    }

    public void readOneData(Activity activity, int umaiid, TextView menuTextView, TextView matTextView){
        if(helper == null){
            helper = new OpenHelper(activity.getApplicationContext());
        }
        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "umaidb",
                new String[] {"_id", "menu","mat"},
                null,
                null,
                null,
                null,
                null
        );
        Cursor cursor2 = db.query(
                "umaiweek",
                new String[] {"w_id", "umaiid"},
                null,
                null,
                null,
                null,
                null
        );
        int readumaiid = 0;
        cursor.moveToFirst();
        cursor2.moveToFirst();
        //umaiweekとumaiidを比較して、readumaiidに_idを格納する
        for (int j = 0; j < cursor2.getCount(); j++) {
            if(umaiid == cursor2.getInt(0)) {
                readumaiid = cursor2.getInt(1);
            }
            cursor2.moveToNext();
        }

        //readumaiidとumaidbの_idを比較してTextViewにいれる
        for (int i = 0; i < cursor.getCount(); i++) {
            if (readumaiid == cursor.getInt(0)) {
                menuTextView.setText(cursor.getString(1));
                matTextView.setText(cursor.getString(2));
            }
            cursor.moveToNext();
        }

        cursor.close();
        cursor2.close();
        Log.d("debug","**********weekmenus");

    }

    public void reroadweekData(Activity activity) {
        ContentValues values = new ContentValues();

        db.delete("umaiweek", null, null);
        helper.saveWData(db);
    }

    public void insertData(String menu, String mat, String url){

        ContentValues values = new ContentValues();
        values.put("menu", menu);
        values.put("mat", mat);
        values.put("url", url);
        values.put("fav", 0);

        db.insert("umaidb", null, values);
    }

    public void reLike(Activity activity, int umaiid){
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "umaidb",
                new String[] {"_id", "menu","mat", "fav"},
                null,
                null,
                null,
                null,
                null
        );
        Cursor cursor2 = db.query(
                "umaiweek",
                new String[] {"w_id", "umaiid"},
                null,
                null,
                null,
                null,
                null
        );

        int readumaiid = 0;
        ContentValues cv = new ContentValues();
        cursor.moveToFirst();
        cursor2.moveToFirst();
        //umaiweekとumaiidを比較して、readumaiidに_idを格納する
        for (int j = 0; j < cursor2.getCount(); j++) {
            if(umaiid == cursor2.getInt(0)) {
                readumaiid = cursor2.getInt(1);
            }
            cursor2.moveToNext();
        }

        cursor.moveToFirst();
        //readumaiidとumaidbの_idを比較して一致したらlikeの値を変える
        for (int i = 0; i < cursor.getCount(); i++) {
            if (readumaiid == cursor.getInt(0)) {

                if (cursor.getInt(3) == 0){
                    cv.put("fav", 1);
                }else {
                    cv.put("fav", 0);
                }
                db.update("umaidb", cv, "_id = " + readumaiid, null);
            }
            cursor.moveToNext();
        }

        cursor.close();
        cursor2.close();

        Log.d("debug","**********weekmenus");
    }

}
