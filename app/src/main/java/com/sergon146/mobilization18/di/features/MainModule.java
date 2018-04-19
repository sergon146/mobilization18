package com.sergon146.mobilization18.di.features;

import com.sergon146.mobilization18.business.contracts.MainUseCase;
import com.sergon146.mobilization18.business.usecase.MainUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.picture.picturedetail.PictureDetailFragment;
import com.sergon146.mobilization18.ui.fragments.picture.picturelist.PictureListFragment;
import com.sergon146.mobilization18.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
@Module
public abstract class MainModule {

    @Provides
    static MainUseCase provideMainUseCase() {
        return new MainUseCaseImpl();
    }

    @Provides
    static MainPresenter provideMainPresenter(MainRouter router, MainUseCase useCase) {
        return new MainPresenter(router, useCase);
    }

    @ContributesAndroidInjector(modules = PictureListModule.class)
    abstract PictureListFragment contributePhotoListFragment();

    @ContributesAndroidInjector(modules = PictureDetailModule.class)
    abstract PictureDetailFragment contributePhotoDetailFragment();
}
