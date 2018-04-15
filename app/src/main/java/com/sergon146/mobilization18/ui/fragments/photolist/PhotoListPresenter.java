package com.sergon146.mobilization18.ui.fragments.photolist;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.mobilization18.business.contracts.PhotoListUseCase;
import com.sergon146.mobilization18.core.api.entities.Picture;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@InjectViewState
public class PhotoListPresenter extends BasePresenter<PhotoListView> {
    private final PhotoListUseCase useCase;
    private List<Picture> pictures = new ArrayList<>();

    public PhotoListPresenter(MainRouter router, PhotoListUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadFirstPage("");
    }

    void loadFirstPage(String keyword) {
        bind(onUi(useCase.getData(keyword)).subscribe(
                data -> {
                    if (data.getPictures().size() > 0) {
                        pictures.clear();
                        pictures.addAll(data.getPictures());
                        getViewState().showPictures(pictures);
                    } else {
                        getViewState().showEmptyMessages();
                    }
                },
                t -> getViewState().showConnectionError()
        ), LifeLevel.PER_PRESENTER);
    }

    @Override
    protected String getScreenTag() {
        return "PhotoList";
    }
}
