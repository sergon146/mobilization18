package com.sergon146.mobilization18.ui.fragments.picture.picturelist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.PictureListUseCase;
import com.sergon146.business.model.base.ResultTitle;
import com.sergon146.business.model.picture.Picture;
import com.sergon146.business.model.picture.PicturesList;
import com.sergon146.core.utils.Const;
import com.sergon146.core.utils.Logger;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;
import com.sergon146.mobilization18.util.pagination.PaginationTool;
import com.sergon146.mobilization18.util.pagination.PagingListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

@InjectViewState
public class PictureListPresenter extends BasePresenter<PictureListView>
    implements PagingListener<List>, PaginationTool.LoadedItemsCounter {
    private final PictureListUseCase useCase;
    public List<Picture> pictures = new ArrayList<>();
    PaginationTool<List> paginationTool;
    private String keyword = "";
    private int totalHits;
    private int currentPage = 1;

    public PictureListPresenter(MainRouter router, PictureListUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void loadFirstPage(String keyword) {
        this.keyword = keyword;
        this.currentPage = 1;
        bind(onUi(useCase.getData(keyword))
            .doOnSubscribe(d -> getViewState().showMainThrobber())
            .doOnTerminate(() -> getViewState().hideMainThrobber())
            .subscribe(data -> {
                    this.totalHits = data.getTotalCounts();
                    this.pictures.clear();
                    this.pictures.addAll(data.getPictures());
                    getViewState().initShowPictures(data.getPictures(),
                        new ResultTitle(keyword, data.getTotalCounts()));

                    if (totalHits != 0 && pictures.size() < totalHits) {
                        getViewState().preparePagination();
                    }

                    Logger.d(getScreenTag(),
                        "Loaded first page, loaded: " + data.getPictures().size()
                            + " of " + totalHits);
                },
                th -> getViewState().showLoadingError()
            ), LifeLevel.PER_PRESENTER);
    }

    public void preparePagination(RecyclerView recyclerView) {
        if (totalHits == 0 || pictures.size() >= totalHits) {
            return;
        }

        paginationTool = PaginationTool.buildPagingObservable(recyclerView, this, this)
            .setTotal(totalHits)
            .build();

        startPagination();
    }

    public void startPagination() {
        if (paginationTool == null) {
            return;
        }

        bind(onUi(paginationTool.getPagingObservable())
                .subscribe(data -> {
                    },
                    throwable -> Log.e(getScreenTag(), throwable.getMessage())),
            LifeLevel.PER_UI);
    }

    @Override
    public Observable<List> onNextPage(int offset) {
        int page = offset / Const.PICTURE_PER_PAGE + 1;
        if (page == currentPage) {
            getViewState().hideThrobber();
            return Observable.just(new ArrayList());
        } else {
            currentPage = page;
        }
        return onUi(useCase.getPage(keyword, page))
            .doOnSubscribe(d -> getViewState().showThrobber())
            .doOnNext(data -> {
                getViewState().hideThrobber();
                pictures.addAll(data.getPictures());
                getViewState().addPictures(data.getPictures());
            })
            .map(PicturesList::getPictures);
    }

    @Override
    public void showTrobber() {
        getViewState().showThrobber();
    }

    @Override
    public void hideTrobber() {
        getViewState().hideThrobber();
    }

    @Override
    public int getItems() {
        return pictures.size();
    }

    public void openDetail(PicturesList picturesDto) {
        getRouter().showDetailScreen(picturesDto);
    }

    @Override
    protected String getScreenTag() {
        return "PhotoList";
    }

    public void loadNextPageIfAvailable() {
        if (totalHits != 0 && pictures.size() < totalHits) {
            getViewState().preparePagination();
        }
    }
}
