package com.example.afleshner.therealmvp;

import android.app.Application;

import com.example.afleshner.therealmvp.modules.CameraCollectionPresenterComponent;
import com.example.afleshner.therealmvp.modules.DaggerCameraCollectionPresenterComponent;


/**
 * Created by afleshner on 11/7/2016.
 */

public class TheRealMVPApp extends Application {


    private CameraCollectionPresenterComponent presenterComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        //components
        presenterComponent = DaggerCameraCollectionPresenterComponent.builder().build();
    }


    public CameraCollectionPresenterComponent getCameraPresenterComponent() {
        return presenterComponent;
    }

}
