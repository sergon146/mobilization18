package com.sergon146.mobilization18.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.Injectable;
import com.sergon146.mobilization18.di.InjectableFragment;
import com.sergon146.mobilization18.ui.LogNamed;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 08.04.2018
 */

public abstract class BaseMvpFragment<Presenter extends BasePresenter> extends InjectableFragment
        implements BaseMvpView, Injectable, LogNamed {

    protected Context appContext;
    private Presenter presenter;
    private BaseMvpActivity hostActivity;

    protected Presenter providePresenter() {
        return null;
    }

    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = providePresenter();
    }

    @Override
    public void showToast(int stringId) {
        Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(getActivity(), R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void restartFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appContext = context.getApplicationContext();
//        appContext.getApplicationContext();
        if (context instanceof BaseMvpActivity) {
            hostActivity = ((BaseMvpActivity) context);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        hostActivity = null;
    }
}
