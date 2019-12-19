package jp.ac.shohoku.umairecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //DBを作る
        MakeDB makedb = new MakeDB(this);

        setContentView(R.layout.main_home);

        //今日の曜日の色だけ変える
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        int[] color = {255, 160, 160};
        if (week == 1) {
            TextView SunText1 = (TextView) findViewById(R.id.SunText1);
            SunText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 2) {
            TextView MonText1 = (TextView) findViewById(R.id.MonText1);
            MonText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 3) {
            TextView TueText1 = (TextView) findViewById(R.id.TueText1);
            TueText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 4) {
            TextView WedText1 = (TextView) findViewById(R.id.WedText1);
            WedText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 5) {
            TextView ThuText1 = (TextView) findViewById(R.id.ThuText1);
            ThuText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 6) {
            TextView FriText1 = (TextView) findViewById(R.id.FriText1);
            FriText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 7) {
            TextView SatText1 = (TextView) findViewById(R.id.SatText1);
            SatText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        };

        //ボタンを押したときにイベントを取得できるようにする
        //メニューボタン
        Button menubarbutton = (Button)findViewById(R.id.menubarButton);
        menubarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここにメニューボタンを押したときの処理
                //フラグメントの表示処理はここに追記する？
            }
        });

        //料理ボタン
        Button menubutton = (Button)findViewById(R.id.menuButton);
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //ここに料理ボタンを押したときの処理
            //料理名だけの表示になる
                //曜日の表示サイズの変更、表示内容の変更
            }
        });

        // 材料ボタン
        Button matbutton = (Button)findViewById(R.id.matButton);
        matbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //ここに材料ボタンを押したときの処理
            //材料だけの表示になる
                //曜日の表示サイズの変更、表示内容の変更
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
