package com.example.afleshner.therealmvp.modules;

import com.example.afleshner.therealmvp.presenters.CameraCollectionPresenter;
import com.example.afleshner.therealmvp.presenters.CamerasInteractorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by afleshner on 11/7/2016.
 */

@Module
public class CameraCollectionPresenterModule {

    @Provides
    @Singleton
    public CameraCollectionPresenter providesCameraPresenter() {
        return new CameraCollectionPresenter(new CamerasInteractorImpl());
    }

}
