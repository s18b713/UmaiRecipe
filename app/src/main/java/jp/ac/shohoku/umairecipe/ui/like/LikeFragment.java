package jp.ac.shohoku.umairecipe.ui.like;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import jp.ac.shohoku.umairecipe.MakeDB;
import jp.ac.shohoku.umairecipe.R;

public class LikeFragment extends Fragment {

    private LikeViewModel likeViewModel;
    private TextView liketext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        likeViewModel =
                ViewModelProviders.of(this).get(LikeViewModel.class);
        View root = inflater.inflate(R.layout.like_view, container, false);

        liketext = root.findViewById(R.id.liketextView);
        final MakeDB makedb = new MakeDB(getActivity());
        makedb.readLikeData(getActivity(), liketext);



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