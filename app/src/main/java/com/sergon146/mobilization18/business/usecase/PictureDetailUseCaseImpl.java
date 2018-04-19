package com.sergon146.mobilization18.business.usecase;

import com.sergon146.mobilization18.business.contracts.PictureDetailUseCase;
import com.sergon146.mobilization18.core.api.entities.PictureResponse;
import com.sergon146.mobilization18.core.dal.repository.PictureRepository;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public class PictureDetailUseCaseImpl implements PictureDetailUseCase {

    private final PictureRepository pictureRepository;

    public PictureDetailUseCaseImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Observable<PictureResponse> getData(String queryKeyword) {
        return pictureRepository.loadData(queryKeyword);
    }
}