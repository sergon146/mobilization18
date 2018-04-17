package com.sergon146.mobilization18.ui.fragments.photo.photolist;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.mobilization18.business.contracts.PhotoListUseCase;
import com.sergon146.mobilization18.core.api.entities.Picture;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;
import com.sergon146.mobilization18.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@InjectViewState
public class PhotoListPresenter extends BasePresenter<PhotoListView> {
    private final PhotoListUseCase useCase;
    public List<Picture> pictures = new ArrayList<>();

    public PhotoListPresenter(MainRouter router, PhotoListUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void loadFirstPage(String keyword) {
        getViewState().hideSearchResultTitle();
        getViewState().showThrobber();
        bind(onUi(useCase.getData(keyword))
                .doOnTerminate(() -> getViewState().hideThrobber())
                .subscribe(data -> {
                            this.pictures.clear();
                            this.pictures.addAll(data.getPictures());
                            getViewState().showPictures(this.pictures);
                            getViewState().showSearchResultTitle(data.getTotalHits());
                            Logger.d(getScreenTag(),
                                    "Loaded first page, count: " + data.getPictures().size());
                        }
                ), LifeLevel.PER_PRESENTER);
    }

    @Override
    protected String getScreenTag() {
        return "PhotoList";
    }
}
