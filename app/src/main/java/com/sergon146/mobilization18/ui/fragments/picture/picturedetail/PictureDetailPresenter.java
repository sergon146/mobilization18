package com.sergon146.mobilization18.ui.fragments.picture.picturedetail;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.PictureDetailUseCase;
import com.sergon146.business.model.picture.Picture;
import com.sergon146.business.model.picture.PicturesList;
import com.sergon146.core.utils.Const;
import com.sergon146.mobilization18.App;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

@InjectViewState
public class PictureDetailPresenter extends BasePresenter<PictureDetailView> {
    private static final int UPDATE_LIMIT = 2;

    private final PictureDetailUseCase useCase;
    private List<Picture> pictures = new ArrayList<>();
    private int currentPosition = Const.NONE;
    private int totalCount = 0;
    private String keyword;

    public PictureDetailPresenter(MainRouter router, PictureDetailUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    public void setupData(PicturesList data) {
        pictures.clear();
        pictures.addAll(data.getPictures());
        currentPosition = data.getPosition();
        totalCount = data.getTotalCounts();
        keyword = data.getKeyword();

        getViewState().initShowPictures(pictures);
        getViewState().showPicture(currentPosition);
    }

    public void pageChanged(int newPosition) {
        if (newPosition >= pictures.size() - UPDATE_LIMIT - 1 && pictures.size() < totalCount) {
            loadNextPage();
        }
        getViewState().setTitleText(App.getAppResources()
            .getString(R.string.picture_state, newPosition + 1, totalCount));

        currentPosition = newPosition;
        if (newPosition == 0) {
            getViewState().hideLeftArrow();
        } else {
            getViewState().showLeftArrow();
        }

        if (newPosition == pictures.size() - 1) {
            getViewState().hideRightArrow();
        } else {
            getViewState().showRightArrow();
        }

        getViewState().showPicture(currentPosition);
    }

    private void loadNextPage() {
        bind(onUi(useCase.getPage(keyword, pictures.size() / 20 + 1))
                .subscribe(data -> {
                    List<Picture> pictures = data.getPictures();
                    this.pictures.addAll(pictures);
                    getViewState().addPictures(pictures);
                })
            , LifeLevel.PER_PRESENTER);
    }

    public void navigateBack() {
        getRouter().back();
    }

    @Override
    protected String getScreenTag() {
        return "PhotoDetail";
    }
}
