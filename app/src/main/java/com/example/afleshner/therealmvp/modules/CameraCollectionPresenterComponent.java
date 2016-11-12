package com.example.afleshner.therealmvp.modules;

import com.example.afleshner.therealmvp.MainActivity;
import com.example.afleshner.therealmvp.presenters.CameraCollectionPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by afleshner on 11/7/2016.
 */

@Singleton
@Component(modules = {CameraCollectionPresenterModule.class})
public interface CameraCollectionPresenterComponent {

//    void inject(CameraCollectionPresenter presenter);

    void inject(MainActivity mainActivity);
}
