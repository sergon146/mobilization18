package com.sergon146.mobilization18.ui.fragments.picture.picturedetail;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.sergon146.business.model.picture.Picture;
import com.sergon146.mobilization18.ui.base.BaseMvpView;

import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PictureDetailView extends BaseMvpView {

    void initShowPictures(List<Picture> pictures);

    @StateStrategyType(AddToEndStrategy.class)
    void addPictures(List<Picture> pictures);

    void showPicture(int currentPosition);

    void setTitleText(String titleText);

    void showLeftArrow();

    void hideLeftArrow();

    void showRightArrow();

    void hideRightArrow();
}
