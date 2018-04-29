package com.sergon146.core.api;

import com.sergon146.core.api.entities.PictureResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public interface PictureApiService {

    @GET("api/")
    Observable<PictureResponse> getPage(@Query("q") String queryKeyword,
                                        @Query("page") int page);
}
