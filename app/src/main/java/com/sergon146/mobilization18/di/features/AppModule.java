package com.sergon146.mobilization18.di.features;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.sergon146.mobilization18.core.Core;
import com.sergon146.mobilization18.core.api.PictureApiService;
import com.sergon146.mobilization18.core.dal.PictureRepositoryImpl;
import com.sergon146.mobilization18.core.dal.repository.PictureRepository;
import com.sergon146.mobilization18.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 10.04.2018
 */

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Context provideContext(Application app) {
        return app;
    }

    @Singleton
    @Provides
    static Resources provideResources(Context context) {
        return context.getResources();
    }

    @Singleton
    @Provides
    static PictureApiService provideMiddlewareService() {
        return Core.api();
    }

    @Singleton
    @Provides
    static PictureRepository provideParentControlRepository(PictureApiService apiService) {
        return new PictureRepositoryImpl(apiService);
    }

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity contributeMainActivity();
}
