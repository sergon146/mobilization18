package com.sergon146.mobilization18.business.contracts;

import com.sergon146.mobilization18.core.api.entities.PictureResponse;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public interface PictureDetailUseCase {
    Observable<PictureResponse> getData(String queryKeyword);
}
