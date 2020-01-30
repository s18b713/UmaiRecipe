package jp.ac.shohoku.umairecipe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RecipeEdit extends AppCompatActivity {

    private EditText edimenu, edimat, ediurl;
    private Context context = this;
    private CharSequence text = "保存しました";
    private int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_edit);

        edimenu = findViewById(R.id.edimenu);
        edimat = findViewById(R.id.edimat);
        ediurl = findViewById(R.id.ediurl);

        //保存ボタン
        Button savebutton =(Button)findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに保存ボタンを押したときの処理
                //テキストエディットの内容をDBに追加する処理
                String menu = edimenu.getText().toString();
                String mat = edimat.getText().toString();
                String url = ediurl.getText().toString();
                menu = menu.replaceAll("　", "");
                menu = menu.replaceAll(" ", "");
                mat = mat.replaceAll("　", "");
                mat = mat.replaceAll(" ", "");


                if (menu == "" || mat == ""){
                    Toast toast = Toast.makeText(context, "入力してください", duration);
                    toast.show();

                }else {
                    MakeDB makedb = new MakeDB(RecipeEdit.this);
                    makedb.insertData(menu, mat, url);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        //閉じるボタン
        Button ediclosebutton =(Button)findViewById(R.id.edicloseButton);
        ediclosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //このボタンを押すと、ホーム画面に移動する
                finish();
            }
        });
    }
}
