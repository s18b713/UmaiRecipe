package jp.ac.shohoku.umairecipe;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;
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
            sbuilder.append("【");
            sbuilder.append(cursor.getString(0));
            sbuilder.append("：");
            sbuilder.append(cursor.getString(1));
            sbuilder.append("】");
            sbuilder.append("\n");
            sbuilder.append(cursor.getString(2));
            sbuilder.append("    url=");
            sbuilder.append(cursor.getString(3));
            sbuilder.append("    ");
            if (cursor.getInt(4) == 1){
                sbuilder.append("<fav>");
            }else {
                sbuilder.append("<unfav>");
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


        Log.d("debug","**********all data read");
        textView.setText(sbuilder.toString());
        textView2.setText(sbuilder2.toString());
    }

    public void readMenuData(TextView[] textViews){
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
        Log.d("debug","**********read weekmenus");
    }

    public void readMatData(TextView[] textViews){
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
        Log.d("debug","*********read weekmats");
    }

    public String readOneData(TextView menuTextView, TextView matTextView, TextView urlTextView, int id) {
        Cursor cursor = db.query(
                "umaidb",
                new String[] {"_id", "menu","mat", "url"},
                null,
                null,
                null,
                null,
                null
        );

        String url = "";
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            if(id == cursor.getInt(0)) {
                menuTextView.setText(cursor.getString(1));
                matTextView.setText(cursor.getString(2));
                urlTextView.setText(cursor.getString(3));
                url = cursor.getString(3);
            }
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("debug","**********read recipe view(to view)");

        return url;
    }

    public int readOneData(int w_id){
        Cursor cursor2 = db.query(
                "umaiweek",
                new String[] {"w_id", "umaiid"},
                null,
                null,
                null,
                null,
                null
        );
        int _id = 0;
        cursor2.moveToFirst();
        //umaiweekとumaidbを比較して、_idを格納する
        for (int i = 0; i < cursor2.getCount(); i++) {
            if(w_id == cursor2.getInt(0)) {
                _id = cursor2.getInt(1);
            }
            cursor2.moveToNext();
        }
        cursor2.close();
        Log.d("debug","**********read recipe view(home to view)");
        return _id;
    }

    public Object[] readLikeData() {
        Log.d("debug","**********likeCursor");
        int fav = 1;

        Cursor cursor = db.query(
                "umaidb",
                new String[] {"_id", "menu", "fav"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        StringBuilder likesbuilder = new StringBuilder();
        StringBuilder likesbuilder2 = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.getInt(2) == 1) {
                likesbuilder.append(cursor.getString(0));
                likesbuilder.append(",");
                likesbuilder2.append(cursor.getString(1));
                likesbuilder2.append(",");
            }
            cursor.moveToNext();
        }
        cursor.close();
        String x =likesbuilder.toString();
        x = x.replaceAll("　", "");
        x = x.replaceAll(" ", "");
        String x2 =likesbuilder2.toString();
        x2 = x2.replaceAll("　", "");
        x2 = x2.replaceAll(" ", "");

        if (x2 == "" || x == ""){
            likesbuilder2.append("登録がありません,");
            likesbuilder.append("-1");
            fav = 0;
        }

        String likesid = likesbuilder.toString();
        String likesmenu = likesbuilder2.toString();

        Object[] like = new Object[3];
        like[0] = likesid;
        like[1] = likesmenu;
        like[2] = fav;

        Log.d("debug", "get likemenu and id");

        return like;
    }

    public int[] countWeekMat() {
        int[] weekmat = new int[7];

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

        int umaiid;
        int id;
        String string;
        String[] strs;

        cursorweek.moveToFirst();
        for (int i = 0; i < cursorweek.getCount(); i++){
            umaiid = cursorweek.getInt(0);
            cursor.moveToFirst();
            for (int j = 0; j < cursor.getCount(); j++) {
                id = cursor.getInt(0);
                if (umaiid == id) {
                    string = cursor.getString(1);
                    strs = string.split("\n");
                    weekmat[i] = strs.length;
                }
                cursor.moveToNext();
            }
            cursorweek.moveToNext();
        }
        cursor.close();
        cursorweek.close();

        Log.d("debug", "count mats of week");

        return weekmat;
    }

    public void editText(int id, EditText edimenu, EditText edimat, EditText ediurl) {
        Cursor cursor = db.query(
                "umaidb",
                new String[] {"_id", "menu","mat", "url"},
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            if (id == cursor.getInt(0)) {
                edimenu.setText(cursor.getString(1));
                edimat.setText(cursor.getString(2));
                ediurl.setText(cursor.getString(3));
            }
            cursor.moveToNext();
        }

        cursor.close();
        Log.d("debug","**********read edit texs (view to edit)");

    }

    public void editData(int id, String menu, String mat, String url) {
        Cursor cursor = db.query(
                "umaidb",
                new String[] {"_id", "menu","mat", "url"},
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        ContentValues cv = new ContentValues();

        for (int i = 0; i < cursor.getCount(); i++) {
            if (id == cursor.getInt(0)) {
                cv.put("menu", menu);
                cv.put("mat", mat);
                cv.put("url", url);
                db.update("umaidb", cv, "_id = " + id, null);
            }
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("debug","**********reedit data");
    }

    public void reroadweekData() {
        db.delete("umaiweek", null, null);
        helper.saveWData(db);
        Log.d("debug","**********remake week data");

    }

    public void insertData(String menu, String mat, String url){

        ContentValues values = new ContentValues();
        values.put("menu", menu);
        values.put("mat", mat);
        values.put("url", url);
        values.put("fav", 0);

        db.insert("umaidb", null, values);
        Log.d("debug","**********insertdata");

    }

    public int reLike(int _id){
        Cursor cursor = db.query(
                "umaidb",
                new String[] {"_id", "menu","mat", "fav"},
                null,
                null,
                null,
                null,
                null
        );


        ContentValues cv = new ContentValues();
        int like = 0;

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {

            if (_id == cursor.getInt(0)) {
                if (cursor.getInt(3) == 0){
                    cv.put("fav", 1);
                    like = 1;
                }else {
                    cv.put("fav", 0);
                    like = 0;
                }
                db.update("umaidb", cv, "_id = " + _id, null);
            }
            cursor.moveToNext();
        }

        cursor.close();
        Log.d("debug","**********relike");
        return like;
    }



}
