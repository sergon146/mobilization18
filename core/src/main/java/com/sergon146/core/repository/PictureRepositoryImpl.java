package com.sergon146.core.repository;


import com.sergon146.business.model.PicturesList;
import com.sergon146.business.repository.PictureRepository;
import com.sergon146.core.api.PictureApiService;
import com.sergon146.core.mappers.PictureListMapper;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class PictureRepositoryImpl implements PictureRepository {

    private final PictureApiService apiService;
    private PictureListMapper pictureListMapper = new PictureListMapper();

    public PictureRepositoryImpl(PictureApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<PicturesList> loadData(String keyword) {
        return apiService.getData(keyword)
                .map(t -> pictureListMapper.from(t));
    }

    @Override
    public Observable<PicturesList> loadPage(String queryKeyword, int page) {
        return apiService.getPage(queryKeyword, page).map(t -> pictureListMapper.from(t));
    }
}
