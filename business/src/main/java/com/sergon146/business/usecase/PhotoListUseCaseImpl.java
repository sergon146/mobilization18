package com.sergon146.business.usecase;


import com.sergon146.business.contracts.PictureListUseCase;
import com.sergon146.business.model.PicturesList;
import com.sergon146.business.repository.PictureRepository;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class PhotoListUseCaseImpl implements PictureListUseCase {

    private final PictureRepository pictureRepository;

    public PhotoListUseCaseImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Observable<PicturesList> getData(String queryKeyword) {
        return pictureRepository.loadData(queryKeyword);
    }
}
