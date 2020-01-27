package jp.ac.shohoku.umairecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DBTest extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.db_test);

        textView = findViewById(R.id.dbtesttext);
        textView2 = findViewById(R.id.weekdbtest);
        button = findViewById(R.id.testbutton);
        button2 = findViewById(R.id.button2);

        final MakeDB makedb = new MakeDB(this);
        makedb.readAllData(this, textView, textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DBTest.this, RecipeEdit.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makedb.readAllData(DBTest.this, textView, textView2);
            }
        });
    }

}
