package jp.ac.shohoku.umairecipe.ui.like;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;

import jp.ac.shohoku.umairecipe.MakeDB;
import jp.ac.shohoku.umairecipe.R;
import jp.ac.shohoku.umairecipe.RecipeView;

public class LikeFragment extends Fragment {

    private LikeViewModel likeViewModel;
    private LinearLayout linearLayout;
    private AppBarConfiguration mAppBarConfiguration;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        likeViewModel =
                ViewModelProviders.of(this).get(LikeViewModel.class);
        View root = inflater.inflate(R.layout.like_view, container, false);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(this, "likeF");

        linearLayout = root.findViewById(R.id.likeViewLayout);

        final MakeDB makedb = new MakeDB(getActivity());
        Object[] getLIDandfav = makedb.readLikeData();
        String ids = (String) getLIDandfav[0];
        String menus = (String) getLIDandfav[1];
        final int fav = (int) getLIDandfav[2];
        String[] idS = ids.split(",");
        String[] menuS = menus.split(",");
        int[] Ids = new int[idS.length];
        for (int i = 0; i < idS.length; i++) {
            if (idS[i] != "") {
                Ids[i] = Integer.parseInt(idS[i]);
            }
        }

        for (int i = 0; i < Ids.length; i++){
            final TextView textView = new TextView(getContext());
            textView.setText(menuS[i]);

            final int _id = Ids[i];
            textView.setId(R.id.likeText);
            textView.setLinksClickable(true);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (fav == 1) {
                        String umai = String.valueOf(_id);
                        Toast.makeText(getActivity(), umai, Toast.LENGTH_SHORT).show();
                        //Recipeviewに遷移
                        Intent intent = new Intent(getActivity(), RecipeView.class);
                        intent.putExtra("_id", _id);
                        startActivity(intent);
                    }
                }
            });
            linearLayout.addView(textView);
        }


        //閉じるボタン
        Button likeclosebutton = (Button) root.findViewById(R.id.likecloseButton);
        likeclosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //このボタンを押すと、ホーム画面に移動する
                getFragmentManager().popBackStack();
            }
        });

        return root;
    }
}