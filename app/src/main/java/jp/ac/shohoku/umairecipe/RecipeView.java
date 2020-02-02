package jp.ac.shohoku.umairecipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;


public class RecipeView extends AppCompatActivity {

    private TextView menuTextView;
    private TextView matTextView;
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.recipe_view);

        Intent intent = getIntent();
        int w_id = intent.getIntExtra("w_id", 0);
        int _id = intent.getIntExtra("_id", 0);

        // 表示する
        menuTextView = findViewById(R.id.mainmenu);
        matTextView = findViewById(R.id.mainmat);
        final MakeDB makedb = new MakeDB(this);
        if (w_id != 0) {
            _id = makedb.readOneData(w_id);
        }
        makedb.readOneData(menuTextView, matTextView, _id);

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
        final int final_id = _id;
        likebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int like = makedb.reLike(final_id);

                if (like == 0){
                    Toast.makeText(RecipeView.this, "お気に入りから削除しました", Toast.LENGTH_SHORT).show();
                }else if (like == 1){
                    Toast.makeText(RecipeView.this, "お気に入りに登録しました", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RecipeView.this, "LikeError", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //編集ボタン
        Button toEditbutton = (Button)findViewById(R.id.toEditbutton);
        final int final_id1 = _id;
        toEditbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                SharedPreferences umaiPreferences;
                umaiPreferences = getApplicationContext().getSharedPreferences("_Id", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = umaiPreferences.edit();
                editor.putInt("ID", final_id1);
                editor.commit();

                //recipeEditにいく

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
