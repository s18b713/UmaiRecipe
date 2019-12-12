package jp.ac.shohoku.umairecipe;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MakeDB extends MainActivity{
    private TextView textView;
    private OpenHelper helper;
    private EditText editTextKey, editTextValue;
    private SQLiteDatabase db;

    public  void MakeDB() {
        if (helper  == null){
            helper = new OpenHelper(getApplicationContext());
        }
        if (db == null){
            db = helper.getWritableDatabase();
        }
    }

    private void readData(){
        if(helper == null){
            helper = new OpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "menudb",
                new String[] { "menu", "mat" },
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
            sbuilder.append(": ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        cursor.close();

        Log.d("debug","**********"+sbuilder.toString());
        textView.setText(sbuilder.toString());
    }

    private void insertData(SQLiteDatabase db, String menu, String mat){

        ContentValues values = new ContentValues();
        values.put("menu", menu);
        values.put("mat", mat);

        db.insert("menudb", null, values);
    }
}
