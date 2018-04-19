package com.sergon146.mobilization18.ui.fragments.picture.picturedetail;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.mobilization18.business.contracts.PictureDetailUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

@InjectViewState
public class PictureDetailPresenter extends BasePresenter<PictureDetailView> {

    private final PictureDetailUseCase useCase;

    public PictureDetailPresenter(MainRouter router, PictureDetailUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected String getScreenTag() {
        return "PhotoDetail";
    }
}
