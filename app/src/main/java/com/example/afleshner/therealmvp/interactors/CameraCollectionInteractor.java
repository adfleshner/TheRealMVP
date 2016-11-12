package com.example.afleshner.therealmvp.interactors;

import com.example.afleshner.therealmvp.objects.ATCamera;
import com.example.afleshner.therealmvp.objects.ATCollection;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by afleshner on 11/7/2016.
 */

public interface CameraCollectionInteractor {

    Call<ArrayList<ATCollection<ATCamera>>> getAllTrafficCameras();

}
