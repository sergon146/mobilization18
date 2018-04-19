package com.sergon146.mobilization18.di.features;

import com.sergon146.mobilization18.business.contracts.PictureListUseCase;
import com.sergon146.mobilization18.core.dal.repository.PictureRepository;
import com.sergon146.mobilization18.business.usecase.PhotoListUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.picture.picturelist.PictureListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@Module
public abstract class PictureListModule {

    @Provides
    static PictureListUseCase providePhotoListUseCase(PictureRepository pictureRepository) {
        return new PhotoListUseCaseImpl(pictureRepository);
    }

    @Provides
    static PictureListPresenter providePhotoPresenter(MainRouter router, PictureListUseCase useCase) {
        return new PictureListPresenter(router, useCase);
    }
}
