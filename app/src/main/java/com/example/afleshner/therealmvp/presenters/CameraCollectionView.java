package com.example.afleshner.therealmvp.presenters;

import com.example.afleshner.therealmvp.objects.ATCamera;
import com.example.afleshner.therealmvp.objects.ATCollection;

import java.util.ArrayList;

/**
 * Created by afleshner on 11/7/2016.
 */
public interface CameraCollectionView {

    void updateUI(ArrayList<ATCollection<ATCamera>> collectionList);

}
