package com.sergon146.mobilization18;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sergon146.core.Core;
import com.sergon146.mobilization18.di.base.AppInjector;
import com.sergon146.mobilization18.di.component.AppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class App extends Application implements HasActivityInjector {
    private static App instance;

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    public static App getInstance() {
        return instance;
    }

    public static Resources getAppResources() {
        return instance.getResources();
    }

    public static AppComponent getAppComponent() {
        return AppInjector.getAppComponent();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppInjector.init(this);
        initCore();
        Fresco.initialize(this);
    }

    private void initCore() {
        Resources res = getAppResources();
        Core core = Core.initInstance(res.getString(R.string.pixbay_base_url),
                res.getString(R.string.pixbay_api_key));
        core.initApi();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
