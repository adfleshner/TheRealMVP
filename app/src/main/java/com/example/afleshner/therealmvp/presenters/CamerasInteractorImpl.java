package com.example.afleshner.therealmvp.presenters;

import android.util.Log;

import com.example.afleshner.therealmvp.interactors.CameraCollectionInteractor;
import com.example.afleshner.therealmvp.objects.ATCamera;
import com.example.afleshner.therealmvp.objects.ATCollection;
import com.example.afleshner.therealmvp.objects.ATIdentity;
import com.example.afleshner.therealmvp.web.TrafficAPI;
import com.example.afleshner.therealmvp.web.IdentityApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by afleshner on 11/7/2016.
 */

public class CamerasInteractorImpl implements CameraCollectionInteractor {

    private Gson mGson;
    private ATIdentity identity;
    private TrafficAPI service;


    public CamerasInteractorImpl() {
        //Gson
        mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        talkToIdentity();
    }

    // All the code needed to connection to 
    private void talkToIdentity() {
        final String identity_client_id = "";
        final String identity_client_secret = "";
        final String identity_scope = "";
        final String identity_grant_type = "";
        Retrofit retrofitIdentity = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
        IdentityApi identityApi = retrofitIdentity.create(IdentityApi.class);
        Call<ATIdentity> response = identityApi.getIdentity(identity_client_id, identity_client_secret, identity_scope, identity_grant_type);
        response.enqueue(new Callback<ATIdentity>() {
            @Override
            public void onResponse(Call<ATIdentity> call, Response<ATIdentity> response) {
                Log.d("AccessToken Created", response.body().getAccessToken());
                identity = response.body();
                //After getting the identity from the identity server
                createService();
            }

            @Override
            public void onFailure(Call<ATIdentity> call, Throwable t) {
                Log.d("IDENTITY", "ERROR");
            }
        });
    }

    //The  Service is created after talking with identity server.
    private void createService() {
        Log.d("", "create  Webservice Called");
        createRetroFit(createOkHttpClient(identity.getAccessToken()));
        Log.d(" RetroFit", "Created");
    }

    private OkHttpClient createOkHttpClient(final String access_token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(1, TimeUnit.MINUTES);
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.retryOnConnectionFailure(true);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Log.d("", "auth token added to  service.");
                String authToken = "Bearer " + access_token;
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder().addHeader("Authorization", authToken).method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    private void createRetroFit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(client)
                .build();
        service = retrofit.create(TrafficAPI.class);
    }


    @Override
    public Call<ArrayList<ATCollection<ATCamera>>> getAllTrafficCameras() {
        return service != null ? service.getTrafficCameras() : null;
    }
}
