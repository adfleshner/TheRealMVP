package com.example.afleshner.therealmvp;

import android.os.PersistableBundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.afleshner.therealmvp.objects.ATCamera;
import com.example.afleshner.therealmvp.objects.ATCollection;
import com.example.afleshner.therealmvp.presenters.CameraCollectionPresenter;
import com.example.afleshner.therealmvp.presenters.CameraCollectionView;

import java.util.ArrayList;


import javax.inject.Inject;

import dagger.Lazy;
import icepick.Icepick;
import icepick.State;

public class MainActivity extends AppCompatActivity implements CameraCollectionView {

    private Button btnGetCameras;
    private TextView tvCameras;
    private ProgressBar bar;
    @Inject
    Lazy<CameraCollectionPresenter> presenter;

    @State String cameras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponents();
        presenter.get().bind(this);
        btnGetCameras = (Button) findViewById(R.id.btnGetCameras);
        tvCameras = (TextView) findViewById(R.id.tvGetCameras);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        btnGetCameras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCameras.setText("");
                bar.setVisibility(View.VISIBLE);
                presenter.get().getAllCameras();
            }
        });
        makegif();
    }

    private void makegif() {
        ImageView i = (ImageView) findViewById(R.id.imageView);
        Glide.with(this).load("http://gifyu.com/images/magicb41e5.gif").asGif().into(i);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        if(cameras!=null) {
            tvCameras.setText(cameras);
            Linkify.addLinks(tvCameras, Linkify.ALL);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.get().unbind();
        super.onDestroy();
    }

    @Override
    public void updateUI(ArrayList<ATCollection<ATCamera>> collectionList) {
        bar.setVisibility(View.GONE);
        if (collectionList != null) {
            String allCams = "";
            for (ATCollection c : collectionList) {
                ArrayList<ATCamera> cameras = c.getEntries();
                for (ATCamera cam : cameras) {
                    allCams = allCams.concat(cam.getUrl() + "\n\n");
                }
            }
            cameras = allCams;
            tvCameras.setText(cameras);
            Linkify.addLinks(tvCameras, Linkify.ALL);
        } else {
            tvCameras.setText("No Cameras Available");
        }
    }

    public void getComponents() {
        TheRealMVPApp base = (TheRealMVPApp) getApplication();
        base.getCameraPresenterComponent().inject(this);
    }
}
