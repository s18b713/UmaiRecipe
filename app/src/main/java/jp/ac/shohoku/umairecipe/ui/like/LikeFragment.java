package jp.ac.shohoku.umairecipe.ui.like;

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
import androidx.lifecycle.ViewModelProviders;

import jp.ac.shohoku.umairecipe.MakeDB;
import jp.ac.shohoku.umairecipe.R;

public class LikeFragment extends Fragment {

    private LikeViewModel likeViewModel;
    private String liketext, haslike;
    private String[] likeText;
    private LinearLayout linearLayout;
    int fav = 0;
    Object[] getLTandfav;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        likeViewModel =
                ViewModelProviders.of(this).get(LikeViewModel.class);
        View root = inflater.inflate(R.layout.like_view, container, false);

        linearLayout = root.findViewById(R.id.likeViewLayout);

        final MakeDB makedb = new MakeDB(getActivity());
        getLTandfav = makedb.readLikeData();

        liketext = (String) getLTandfav[0];
        fav = (int) getLTandfav[1];
        likeText = liketext.split("\n");

        for (int i = 0; i < likeText.length; i++){
            final TextView textView = new TextView(getContext());
            textView.setText(likeText[i]);
            haslike = likeText[i];

            textView.setId(R.id.likeText);
            textView.setLinksClickable(true);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (fav == 1) {
                        Toast.makeText(getActivity(), haslike, Toast.LENGTH_SHORT).show();
                        //likeText[i]をもってRecipeViewに行きたい
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