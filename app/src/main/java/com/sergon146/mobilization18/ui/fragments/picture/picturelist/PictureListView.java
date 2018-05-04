package com.sergon146.mobilization18.ui.fragments.picture.picturelist;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.sergon146.business.model.base.ResultTitle;
import com.sergon146.business.model.picture.Picture;
import com.sergon146.mobilization18.ui.base.BaseMvpView;

import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PictureListView extends BaseMvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void initShowPictures(List<Picture> pictures, ResultTitle resultTitle);

    void preparePagination();

    void showThrobber();

    void hideThrobber();

    @StateStrategyType(AddToEndStrategy.class)
    void addPictures(List<Picture> pictures);

    @StateStrategyType(SkipStrategy.class)
    void showMainThrobber();

    @StateStrategyType(SkipStrategy.class)
    void hideMainThrobber();
}
