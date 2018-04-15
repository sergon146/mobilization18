package com.sergon146.mobilization18.business.usecase;

import com.sergon146.mobilization18.core.api.entities.PictureResponse;
import com.sergon146.mobilization18.core.dal.repository.PictureRepository;
import com.sergon146.mobilization18.business.contracts.PhotoListUseCase;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class PhotoListUseCaseImpl implements PhotoListUseCase {

    private final PictureRepository pictureRepository;

    public PhotoListUseCaseImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Observable<PictureResponse> getData(String queryKeyword) {
        return pictureRepository.loadData(queryKeyword);
    }
}
