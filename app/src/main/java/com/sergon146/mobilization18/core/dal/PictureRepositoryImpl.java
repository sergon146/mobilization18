package com.sergon146.mobilization18.core.dal;

import com.sergon146.mobilization18.core.api.PictureApiService;
import com.sergon146.mobilization18.core.api.entities.PictureResponse;
import com.sergon146.mobilization18.core.dal.repository.PictureRepository;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class PictureRepositoryImpl implements PictureRepository {

    private final PictureApiService apiService;

    public PictureRepositoryImpl(PictureApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<PictureResponse> loadData(String keyword) {
        return apiService.getData(keyword);
    }
}
