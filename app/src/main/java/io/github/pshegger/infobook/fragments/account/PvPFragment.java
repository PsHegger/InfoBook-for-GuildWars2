package io.github.pshegger.infobook.fragments.account;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.pshegger.infobook.InfoBookApplication;
import io.github.pshegger.infobook.fragments.BaseInfoBookFragment;

/**
 * Created by pshegger on 2015.10.04..
 */
public class PvPFragment extends BaseInfoBookFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = new View(container.getContext());
        v.setBackgroundColor(
                Color.rgb(
                        InfoBookApplication.RNG.nextInt(255),
                        InfoBookApplication.RNG.nextInt(255),
                        InfoBookApplication.RNG.nextInt(255)
                )
        );

        return v;
    }

    @Override
    public String getName() {
        return "PvP";
    }
}
