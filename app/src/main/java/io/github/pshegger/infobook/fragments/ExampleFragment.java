package io.github.pshegger.infobook.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pshegger on 2015.10.04..
 */
public class ExampleFragment extends NamedFragment {
    private static final int[] colors = new int[] {
            Color.RED, Color.YELLOW, Color.GREEN,
            Color.BLUE, Color.CYAN
    };
    private static int idCtr = 0;

    private int myId;
    private String baseName;

    public static ExampleFragment newInstance() {
        return newInstance("Example");
    }

    public static ExampleFragment newInstance(String name) {
        ExampleFragment ef = new ExampleFragment();

        ef.myId = idCtr++;
        ef.baseName = name;

        return ef;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = new View(container.getContext());
        v.setBackgroundColor(colors[myId % colors.length]);

        return v;
    }

    @Override
    public String getName() {
        return baseName+myId;
    }
}
