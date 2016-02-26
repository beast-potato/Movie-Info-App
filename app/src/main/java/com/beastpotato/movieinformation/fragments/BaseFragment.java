package com.beastpotato.movieinformation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.beastpotato.movieinformation.activities.MainActivity;

/**
 * Created by Oleksiy on 1/31/2016.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setupView(view);
    }

    protected abstract void setupView(View view);

    public MainActivity getBaseActivity() {
        return (MainActivity) super.getActivity();
    }

}
