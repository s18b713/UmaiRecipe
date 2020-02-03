package jp.ac.shohoku.umairecipe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DBTest extends AppCompatActivity {

    private TextView textView, textView2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.db_test);

        textView = findViewById(R.id.dbtesttext);
        textView2 = findViewById(R.id.weekdbtest);
        button = findViewById(R.id.button2);
        final MakeDB makedb = new MakeDB(this);
        makedb.readAllData(this, textView, textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makedb.readAllData(DBTest.this, textView, textView2);
            }
        });
    }

}
