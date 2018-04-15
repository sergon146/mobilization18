package com.sergon146.mobilization18.core.dal.repository;


import com.sergon146.mobilization18.core.api.entities.PictureResponse;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public interface PictureRepository {

    Observable<PictureResponse> loadData(String keyword);
}
