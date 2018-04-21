package com.sergon146.mobilization18.ui.base;import com.sergon146.mobilization18.App;import com.sergon146.mobilization18.navigation.MainRouter;import com.sergon146.core.utils.ErrorManager;import com.sergon146.core.utils.Logger;import com.sergon146.core.utils.NetworkUtil;import java.io.IOException;import io.reactivex.android.schedulers.AndroidSchedulers;import io.reactivex.disposables.Disposable;import io.reactivex.schedulers.Schedulers;import retrofit2.HttpException;/** * @author Sergon146 (sergon146@gmail.com) * @since 08.04.2018. */public abstract class BasePresenter<View extends BaseMvpView>        extends BaseSchedulablePresenter<View> {    protected final String screenTag;    private final MainRouter router;    public BasePresenter(MainRouter router) {        this.router = router;        screenTag = getScreenTag();    }    @Override    protected void onFirstViewAttach() {        super.onFirstViewAttach();        subscribeEvents();    }    protected void bind(Disposable disposable) {        bind(disposable, LifeLevel.PER_VIEW);    }    @Override    protected void bind(Disposable disposable, LifeLevel level) {        if (NetworkUtil.isLostConnection(App.getInstance().getApplicationContext())) {            getViewState().showConnectionError();            return;        }        super.bind(disposable, level);    }    private void subscribeEvents() {        bind(ErrorManager.errors()                        .filter(throwable -> (throwable instanceof IOException                                || throwable instanceof HttpException))                        .subscribeOn(Schedulers.io())                        .observeOn(AndroidSchedulers.mainThread())                        .subscribe(throwable -> getViewState().showConnectionError(),                                (t) -> Logger.w(Logger.getLocation(), screenTag, "subscribeEvents", t)),                LifeLevel.PER_PRESENTER);    }    protected MainRouter getRouter() {        return router;    }    protected abstract String getScreenTag();}