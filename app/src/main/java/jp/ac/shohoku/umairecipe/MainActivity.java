package jp.ac.shohoku.umairecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //DBを作る
//        MakeDB makedb = new MakeDB();
//        makedb.MakeDB();

        setContentView(R.layout.main_home);

        //ボタンを押したときにイベントを取得できるようにする

        //メニューボタンの処理
        //フラグメントの表示処理はここに追記する

        //料理ボタン
        Button menubutton = (Button)findViewById(R.id.menuButton);
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //ここに料理ボタンを押したときの処理
    }
});
        // 材料ボタン
        Button matbutton = (Button)findViewById(R.id.matButton);
        matbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //ここに材料ボタンを押したときの処理
           }
        });
        // 曜日ボタン
        Button monbutton =(Button)findViewById(R.id.MonButton);
        monbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // インデントにこの画面と遷移するRecipeViewを指定する
               Intent intent = new Intent(MainActivity.this, RecipeView.class);
               startActivity(intent);
            }
          });
        Button tuebutton =(Button)findViewById(R.id.TueButton);
        tuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに火曜ボタンを押したときの処理
                Intent intent = new Intent(MainActivity.this, RecipeView.class);
                startActivity(intent);
            }
        });
        Button wedbutton =(Button)findViewById(R.id.WedButton);
        wedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに水曜ボタンを押したときの処理
                Intent intent = new Intent(MainActivity.this, RecipeView.class);
                startActivity(intent);
            }
        });
        Button thubutton =(Button)findViewById(R.id.ThuButton);
        thubutton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //ここに木曜ボタンを押したときの処理
                  Intent intent = new Intent(MainActivity.this, RecipeView.class);
                  startActivity(intent);
              }
        });
        Button fributton =(Button)findViewById(R.id.FriButton);
        fributton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //ここに金曜ボタンを押したときの処理
                  Intent intent = new Intent(MainActivity.this, RecipeView.class);
                  startActivity(intent);
              }
        });
        Button satbutton =(Button)findViewById(R.id.SatButton);
        satbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに土曜ボタンを押したときの処理
                Intent intent = new Intent(MainActivity.this, RecipeView.class);
                startActivity(intent);
            }
        });
        Button sunbutton =(Button)findViewById(R.id.SunButton);
        sunbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに日曜ボタンを押したときの処理
                Intent intent = new Intent(MainActivity.this, RecipeView.class);
                startActivity(intent);
            }
        });


    }
}
