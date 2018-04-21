package com.sergon146.mobilization18.ui.fragments.picture.picturelist;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.model.Picture;
import com.sergon146.business.model.PicturesList;
import com.sergon146.business.contracts.PictureListUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;
import com.sergon146.core.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@InjectViewState
public class PictureListPresenter extends BasePresenter<PictureListView> {
    private final PictureListUseCase useCase;
    public List<Picture> pictures = new ArrayList<>();

    public PictureListPresenter(MainRouter router, PictureListUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void loadFirstPage(String keyword) {
        bind(onUi(useCase.getData(keyword))
                .doOnSubscribe(d -> getViewState().showThrobber())
                .doOnTerminate(() -> getViewState().hideThrobber())
                .subscribe(data -> {
                            this.pictures.clear();
                            this.pictures.addAll(data.getPictures());
                            getViewState().showPictures(this.pictures);
                            getViewState().showSearchResultCount(data.getTotalHits());
                            Logger.d(getScreenTag(),
                                    "Loaded first page, count: " + data.getPictures().size());
                        }
                ), LifeLevel.PER_PRESENTER);
    }

    @Override
    protected String getScreenTag() {
        return "PhotoList";
    }

    public void openDetail(PicturesList picturesDto) {
        getRouter().showDetailScreen(picturesDto);
    }
}
