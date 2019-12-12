package jp.ac.shohoku.umairecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_edit);

        //ボタンを押したときにイベントを取得できるようにする
        //メニュー追加ボタン
        Button addmenubutton =(Button)findViewById(R.id.addmenuButton);
        addmenubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここにメニュー追加ボタンを押したときの処理
            }
        });

        //お気に入り表示ボタン
        Button showlikebutton =(Button)findViewById(R.id.showlikeButton);
        showlikebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //このボタンを押すと、お気に入り表示画面に移動する
                Intent intent = new Intent(MenuView.this, LikeView.class);
                startActivity(intent);
            }
        });


    }
}
