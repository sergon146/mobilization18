package com.sergon146.mobilization18.di.features;

import com.sergon146.mobilization18.business.contracts.PictureDetailUseCase;
import com.sergon146.mobilization18.business.usecase.PictureDetailUseCaseImpl;
import com.sergon146.mobilization18.core.dal.repository.PictureRepository;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.picture.picturedetail.PictureDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

@Module
public abstract class PictureDetailModule {

    @Provides
    static PictureDetailUseCase providePhotoListUseCase(PictureRepository pictureRepository) {
        return new PictureDetailUseCaseImpl(pictureRepository);
    }

    @Provides
    static PictureDetailPresenter providePhotoDetailPresenter(MainRouter router,
                                                              PictureDetailUseCase useCase) {
        return new PictureDetailPresenter(router, useCase);
    }
}
