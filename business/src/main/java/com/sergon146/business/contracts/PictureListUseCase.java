package com.sergon146.business.contracts;


import com.sergon146.business.model.picture.PicturesList;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public interface PictureListUseCase {
    Observable<PicturesList> getData(String queryKeyword);

    Observable<PicturesList> getPage(String queryKeyword, int page);
}
