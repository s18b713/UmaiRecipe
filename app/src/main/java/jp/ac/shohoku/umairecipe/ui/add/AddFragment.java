package jp.ac.shohoku.umairecipe.ui.add;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import jp.ac.shohoku.umairecipe.MakeDB;
import jp.ac.shohoku.umairecipe.R;

public class AddFragment extends Fragment {

    private AddViewModel addViewModel;
    private EditText edimenu, edimat, ediurl;
    private CharSequence text = "保存しました";
    private int duration = Toast.LENGTH_LONG;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.recipe_edit, container, false);

        final MakeDB makedb = new MakeDB(getActivity());


        //もしRecipeViewからきてたら0以外が受け取られる
        final SharedPreferences umaiPreferences = getContext().getSharedPreferences("_Id", Context.MODE_PRIVATE);
        final int id = umaiPreferences.getInt("ID", 0);


        edimenu = root.findViewById(R.id.edimenu);
        edimat = root.findViewById(R.id.edimat);
        ediurl = root.findViewById(R.id.ediurl);

        //homeから来た時の処理
        if (id == 0) {
            //保存ボタン
            Button savebutton = (Button) root.findViewById(R.id.saveButton);
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


                    if (menu == "" || mat == "") {
                        Toast.makeText(getActivity(), "入力してください", duration).show();

                    } else {
                        makedb.insertData(menu, mat, url);
                        Toast.makeText(getActivity(), text, duration).show();
                    }
                }
            });
        }else {
            //レシピビューから来た時の処理
            //それぞれのtexteditに既存の情報を入れる
            makedb.editText(id, edimenu, edimat, ediurl);
            //追加保存ではなく、編集を行う
            Button savebutton = (Button) root.findViewById(R.id.saveButton);
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

                    if (menu == "" || mat == "") {
                        Toast.makeText(getActivity(), "入力してください", duration).show();

                    } else {
                        makedb.editData(id, menu, mat, url);
                        Toast.makeText(getActivity(), text, duration).show();
                    }
                }
            });

        }

        //閉じるボタン
        Button ediclosebutton =(Button)root.findViewById(R.id.edicloseButton);
        ediclosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //このボタンを押すと、ホーム画面に移動する
                getFragmentManager().popBackStack();
            }
        });
        return root;
    }

    @Override
    public void onStart(){
        super.onStart();
    }


}