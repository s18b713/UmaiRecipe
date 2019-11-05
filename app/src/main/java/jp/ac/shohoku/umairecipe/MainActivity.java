package jp.ac.shohoku.umairecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private OpenHelper helper;
    private EditText editTextKey, editTextValue;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 各変数にidを格納
        editTextKey = findViewById(R.id.edit_text_key);
        editTextValue = findViewById(R.id.edit_text_value);
        textView = findViewById(R.id.text_view);

        // DB作成
        if(helper == null) {
            helper = new OpenHelper(getApplicationContext());
        }
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        helper.saveData(db,"menu","mat");



        saveData(textView);

        readData(textView);
    }

    /**
     * DBからデータを全取得し画面に表示する
     * @param  view
     */
    public void readData(View view){
        if (helper == null){
            helper = new OpenHelper(getApplicationContext());
        }
        if (db == null) {
            db = helper.getReadableDatabase();
        }
        Cursor cursor = db.query(
                "menudb",
                new String[]{ "title", "materila" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++){
            sbuilder.append(cursor.getString(0));
            sbuilder.append(":    ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("\n\n");
            cursor.moveToNext();
        }

        cursor.close();

        textView.setText(sbuilder.toString());
    }

    /**
     * データを保存する
     * @param view
     */
    public void saveData(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        EditText editTextTitle = editTextKey;
        EditText editTextMaterial = editTextValue;
        String title = editTextTitle.getText().toString();
        String material = editTextMaterial.getText().toString();

        values.put("title", title);
        values.put("material", material);

        db.insert("menubd", null, values);
    }

}
