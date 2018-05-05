package com.sergon146.business.contracts;


import com.sergon146.business.model.picture.PicturesList;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public interface PictureDetailUseCase {
    Observable<PicturesList> getPage(String queryKeyword, int page, int countPerPage);
}
