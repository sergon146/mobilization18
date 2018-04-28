package com.sergon146.business.repository;


import com.sergon146.business.model.picture.PicturesList;

import io.reactivex.Observable;


/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public interface PictureRepository {

    Observable<PicturesList> loadData(String keyword);

    Observable<PicturesList> loadPage(String queryKeyword, int page);
}
