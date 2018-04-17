package com.sergon146.mobilization18.di.features;

import com.sergon146.mobilization18.core.dal.repository.PictureRepository;
import com.sergon146.mobilization18.business.contracts.PhotoListUseCase;
import com.sergon146.mobilization18.business.usecase.PhotoListUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.photo.photolist.PhotoListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@Module
public abstract class PhotoModule {

    @Provides
    static PhotoListUseCase providePhotoListUseCase(PictureRepository pictureRepository) {
        return new PhotoListUseCaseImpl(pictureRepository);
    }

    @Provides
    static PhotoListPresenter providePhotoPresenter(MainRouter router, PhotoListUseCase useCase) {
        return new PhotoListPresenter(router, useCase);
    }
}
