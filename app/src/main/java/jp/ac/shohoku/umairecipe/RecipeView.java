package jp.ac.shohoku.umairecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class RecipeView extends AppCompatActivity {

    private TextView menuTextView;
    private TextView matTextView;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.recipe_view);

        Intent intent = getIntent();
        final int umaiid = intent.getIntExtra("umaiid", 0);


        // 表示する
        menuTextView = findViewById(R.id.mainmenu);
        matTextView = findViewById(R.id.mainmat);
        final MakeDB makedb = new MakeDB(this);
        makedb.readOneData(this, umaiid, menuTextView, matTextView);


        //閉じるボタン
        Button closebutton =(Button)findViewById(R.id.closeButton);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //このボタンを押すと、ホーム画面に移動する
                finish();
            }
        });

        //お気に入りボタン
        Button likebutton =(Button)findViewById(R.id.likeButton);
        likebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makedb.reLike(RecipeView.this, umaiid);

            }
        });

        //おすすめ
        Button rec =(Button)findViewById(R.id.rec);
        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ここにおすすめを押したときの処理
            }
        });



    }
}
