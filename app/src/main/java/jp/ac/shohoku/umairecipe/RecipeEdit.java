package jp.ac.shohoku.umairecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_edit);

        //保存ボタン
        Button savebutton =(Button)findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに保存ボタンを押したときの処理
            }
        });

        //閉じるボタン
        Button ediclosebutton =(Button)findViewById(R.id.edicloseButton);
        ediclosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //このボタンを押すと、ホーム画面に移動する
                Intent intent = new Intent(RecipeEdit.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //テキストエディットの処理
        //テキストエディットの内容をDBに追加する処理

    }
}
