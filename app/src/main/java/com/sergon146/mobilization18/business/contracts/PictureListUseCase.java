package com.sergon146.mobilization18.business.contracts;

import com.sergon146.mobilization18.core.api.entities.PictureResponse;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public interface PictureListUseCase {
    Observable<PictureResponse> getData(String queryKeyword);
}
