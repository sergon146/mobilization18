package com.sergon146.mobilization18.di.features;

import com.sergon146.mobilization18.core.dal.repository.PictureRepository;
import com.sergon146.mobilization18.business.contracts.PhotoListUseCase;
import com.sergon146.mobilization18.business.usecase.PhotoListUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.photolist.PhotoListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@Module
public abstract class PhotoListModule {

    @Provides
    static PhotoListUseCase provideMainUseCase(PictureRepository pictureRepository) {
        return new PhotoListUseCaseImpl(pictureRepository);
    }

    @Provides
    static PhotoListPresenter provideMainPresenter(MainRouter router, PhotoListUseCase useCase) {
        return new PhotoListPresenter(router, useCase);
    }

}
