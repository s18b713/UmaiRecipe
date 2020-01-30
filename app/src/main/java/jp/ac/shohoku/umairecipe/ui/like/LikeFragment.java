package jp.ac.shohoku.umairecipe.ui.like;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import jp.ac.shohoku.umairecipe.R;
import jp.ac.shohoku.umairecipe.ui.like.LikeViewModel;


public class LikeFragment extends Fragment {

    private LikeViewModel likeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        likeViewModel =
                ViewModelProviders.of(this).get(LikeViewModel.class);
        View root = inflater.inflate(R.layout.like_view, container, false);
        return root;
    }
}