package com.sergon146.business.usecase;

import com.sergon146.business.model.PicturesList;
import com.sergon146.business.repository.PictureRepository;
import com.sergon146.business.contracts.PictureDetailUseCase;

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
    public Observable<PicturesList> getPage(String queryKeyword, int page) {
        return pictureRepository.loadPage(queryKeyword, page);
    }
}