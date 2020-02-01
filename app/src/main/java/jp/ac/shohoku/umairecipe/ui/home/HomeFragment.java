package jp.ac.shohoku.umairecipe.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;

import jp.ac.shohoku.umairecipe.DBTest;
import jp.ac.shohoku.umairecipe.MakeDB;
import jp.ac.shohoku.umairecipe.R;
import jp.ac.shohoku.umairecipe.RecipeView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.main_home, container, false);

        final Activity Activity = getActivity();

        //DBを作る
        final MakeDB makedb = new MakeDB(Activity);

        //各テキスト、ボタンをまとめる
        final TextView[] textView1s = {(TextView) root.findViewById(R.id.SunText1),
                (TextView) root.findViewById(R.id.MonText1),
                (TextView) root.findViewById(R.id.TueText1),
                (TextView) root.findViewById(R.id.WedText1),
                (TextView) root.findViewById(R.id.ThuText1),
                (TextView) root.findViewById(R.id.FriText1),
                (TextView) root.findViewById(R.id.SatText1)};

        final TextView[] textView2s = {(TextView) root.findViewById(R.id.SunText2),
                (TextView) root.findViewById(R.id.MonText2),
                (TextView) root.findViewById(R.id.TueText2),
                (TextView) root.findViewById(R.id.WedText2),
                (TextView) root.findViewById(R.id.ThuText2),
                (TextView) root.findViewById(R.id.FriText2),
                (TextView) root.findViewById(R.id.SatText2)};

        final Button[] buttons = {(Button) root.findViewById(R.id.SunButton),
                (Button) root.findViewById(R.id.MonButton),
                (Button) root.findViewById(R.id.TueButton),
                (Button) root.findViewById(R.id.WedButton),
                (Button) root.findViewById(R.id.ThuButton),
                (Button) root.findViewById(R.id.WedButton),
                (Button) root.findViewById(R.id.SatButton)};

        //まずはDBのメニュー名を曜日欄に表示する
        makedb.readMenuData(Activity, textView2s);

        //今日の曜日の色を変える
        WeekColor(textView1s);
        //ボタンなどのサイズを画面サイズに合わせて変更する
        setSize(textView1s, textView2s, buttons);

//**********DB_test用
        Button dbbutton = (Button) root.findViewById(R.id.dbButton);
        dbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity, DBTest.class);
                startActivity(intent);
            }
        });
//********

        //ボタンを押したときにイベントを取得できるようにする
        //メニューボタン
        Button menubarbutton = (Button) root.findViewById(R.id.menubarButton);
        menubarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここにメニューボタンを押したときの処理
            }
        });

        //料理ボタン
        Button menubutton = (Button) root.findViewById(R.id.menuButton);
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに料理ボタンを押したときの処理
                //料理名だけの表示になる
                makedb.readMenuData(Activity, textView2s);
                //曜日の表示サイズの変更
            }
        });

        // 材料ボタン
        Button matbutton = (Button) root.findViewById(R.id.matButton);
        matbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ここに材料ボタンを押したときの処理
                //材料だけの表示になる
                makedb.readMatData(Activity, textView2s);
                //曜日の表示サイズの変更、表示内容の変更
            }
        });

        //更新ボタン
        Button reroadbutton = (Button) root.findViewById(R.id.reroadButton);
        reroadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //曜日ごとのメニューを変える
                makedb.reroadweekData(Activity);
                //画面を更新する
                makedb.readMenuData(Activity, textView2s);
            }
        });


        // 曜日ボタン
        Button monbutton = (Button) root.findViewById(R.id.MonButton);
        monbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // インデントにこの画面と遷移するRecipeViewを指定する
                Intent intent = new Intent(Activity, RecipeView.class);
                intent.putExtra("umaiid", 2);
                startActivity(intent);
            }
        });
        Button tuebutton = (Button) root.findViewById(R.id.TueButton);
        tuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに火曜ボタンを押したときの処理
                Intent intent = new Intent(Activity, RecipeView.class);
                intent.putExtra("umaiid", 3);
                startActivity(intent);
            }
        });
        Button wedbutton = (Button) root.findViewById(R.id.WedButton);
        wedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに水曜ボタンを押したときの処理
                Intent intent = new Intent(Activity, RecipeView.class);
                intent.putExtra("umaiid", 4);
                startActivity(intent);
            }
        });
        Button thubutton = (Button) root.findViewById(R.id.ThuButton);
        thubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに木曜ボタンを押したときの処理
                Intent intent = new Intent(Activity, RecipeView.class);
                intent.putExtra("umaiid", 5);
                startActivity(intent);
            }
        });
        Button fributton = (Button) root.findViewById(R.id.FriButton);
        fributton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに金曜ボタンを押したときの処理
                Intent intent = new Intent(Activity, RecipeView.class);
                intent.putExtra("umaiid", 6);
                startActivity(intent);
            }
        });
        Button satbutton = (Button) root.findViewById(R.id.SatButton);
        satbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに土曜ボタンを押したときの処理
                Intent intent = new Intent(Activity, RecipeView.class);
                intent.putExtra("umaiid", 7);
                startActivity(intent);
            }
        });
        Button sunbutton = (Button) root.findViewById(R.id.SunButton);
        sunbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここに日曜ボタンを押したときの処理
                Intent intent = new Intent(Activity, RecipeView.class);
                intent.putExtra("umaiid", 1);
                startActivity(intent);
            }
        });


        return root;
    }

    public void WeekColor(TextView[] textView1s) {
        //今日の曜日の色だけ変える
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        int[] color = {255, 160, 160};
        if (week == 1) {
            TextView SunText1 = textView1s[0];
            SunText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 2) {
            TextView MonText1 = textView1s[1];
            MonText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 3) {
            TextView TueText1 = textView1s[2];
            TueText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 4) {
            TextView WedText1 = textView1s[3];
            WedText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 5) {
            TextView ThuText1 = textView1s[4];
            ThuText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 6) {
            TextView FriText1 = textView1s[5];
            FriText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        } else if (week == 7) {
            TextView SatText1 = textView1s[6];
            SatText1.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        }
    }

    public void setSize(TextView[] textView1s, TextView[] textView2s, Button[] buttons) {
        SharedPreferences preferences = getContext().getSharedPreferences("winSize", Context.MODE_PRIVATE);
        int width = preferences.getInt("width", 0);
        int height = preferences.getInt("height", 0);

        // ひきわたされた画面サイズからサイズ調整をする
        int Hsize = height / 10;
        int Wsize1 = width / 6 ;
        int Wsize2 = width / 2 ;
        int Wsize3 = width / 4 * 3;
        for (int i = 0; i<7; i++){
            textView1s[i].setHeight(Hsize);
            textView1s[i].setWidth(Wsize1);
            textView2s[i].setHeight(Hsize);
            textView2s[i].setWidth(Wsize2);
            buttons[i].setHeight(Hsize);
            buttons[i].setWidth(Wsize3);
        }
    }
}