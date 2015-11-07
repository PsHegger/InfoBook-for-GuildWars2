package io.github.pshegger.infobook.fragments;

import io.github.pshegger.infobook.GWApiService;
import io.github.pshegger.infobook.InfoBookApplication;

/**
 * Created by pshegger on 2015.10.13..
 */
public abstract class BaseInfoBookFragment extends NamedFragment {
    protected GWApiService getAPIService() {
        return InfoBookApplication.getInstance().getService();
    }
}
