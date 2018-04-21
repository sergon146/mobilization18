package com.sergon146.business.contracts;


import com.sergon146.business.model.PicturesList;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public interface PictureDetailUseCase {
    Observable<PicturesList> getData(String queryKeyword);
}
