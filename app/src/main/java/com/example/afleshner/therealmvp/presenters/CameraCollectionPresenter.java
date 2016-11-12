package com.example.afleshner.therealmvp.presenters;

import android.util.Log;

import com.example.afleshner.therealmvp.interactors.CameraCollectionInteractor;
import com.example.afleshner.therealmvp.objects.ATCamera;
import com.example.afleshner.therealmvp.objects.ATCollection;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by afleshner on 11/7/2016.
 */

public class CameraCollectionPresenter {

    private CameraCollectionView view;

    private CameraCollectionInteractor interactor;

    public CameraCollectionPresenter(CameraCollectionInteractor interactor) {
        this.interactor = interactor;
    }

    public void bind(CameraCollectionView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }


    public void getAllCameras() {
        if (interactor != null && interactor.getAllTrafficCameras() != null) {
            interactor.getAllTrafficCameras().enqueue(new Callback<ArrayList<ATCollection<ATCamera>>>() {
                @Override
                public void onResponse(Call<ArrayList<ATCollection<ATCamera>>> call, Response<ArrayList<ATCollection<ATCamera>>> response) {
                    if (view != null) {
                        view.updateUI(response.body());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ATCollection<ATCamera>>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }else{
            Log.d("Presenter","Stop!");
        }
    }
}
