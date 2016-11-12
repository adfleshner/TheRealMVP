package com.example.afleshner.therealmvp.web;

import com.example.afleshner.therealmvp.objects.ATCamera;
import com.example.afleshner.therealmvp.objects.ATCollection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by afleshner on 11/7/2016.
 */

public interface TrafficAPI {

    @GET("")
    Call<ArrayList<ATCollection<ATCamera>>> getTrafficCameras();

}
