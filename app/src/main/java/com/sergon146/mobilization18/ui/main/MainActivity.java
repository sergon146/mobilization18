package com.sergon146.mobilization18.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.navigation.Screens;
import com.sergon146.mobilization18.ui.base.BaseMvpActivity;
import com.sergon146.mobilization18.ui.fragments.photo.photodetail.PhotoDetailFragment;
import com.sergon146.mobilization18.ui.fragments.photo.photolist.PhotoListFragment;

import javax.inject.Inject;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainView {

    @Inject
    MainRouter router;

    @Inject
    @InjectPresenter
    MainPresenter presenter;

    @Override
    @ProvidePresenter
    protected MainPresenter providePresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            router.showMainScreen();
        }
    }

    @Override
    protected int getNavigationContainerId() {
        return R.id.container;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        Screens screen = Screens.valueOf(screenKey);
        switch (screen) {
            case MAIN_SCREEN:
                return PhotoListFragment.getInstance();
            case DETAIL_SCREEN:
                int pos = (int) data;
                return PhotoDetailFragment.getInstance(pos);
            default:
                throw new RuntimeException("Unknown screen");
        }
    }

    @Override
    public void onBackPressed() {
        int entryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (entryCount > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }
}
