package com.sergon146.mobilization18.ui.fragments.picture.picturelist;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.sergon146.mobilization18.core.api.entities.Picture;
import com.sergon146.mobilization18.ui.base.BaseMvpView;

import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PictureListView extends BaseMvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showPictures(List<Picture> pictures);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSearchResultCount(int count);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideSearchResultCount();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showThrobber();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideThrobber();
}
