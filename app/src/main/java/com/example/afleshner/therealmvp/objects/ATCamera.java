package com.example.afleshner.therealmvp.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by afleshner on 11/7/2016.
 */
public class ATCamera implements Parcelable {

    private long id;
    private String controlType;
    private int imageSupported;
    private String imageUrl;
    private Date lastUpdate;
    private String organizationId;
    private double latitude;
    private double longitude;
    private String streamUrl;
    private String primaryRoad;
    private double mileMarker;
    private String direction;
    private String crossStreet;
    private String requestCommand;
    private String name;
    private long deviceId;

    public ATCamera() {

    }

    public String getTitle() {
        return primaryRoad;
    }

    public String getSubtitle() {
        return crossStreet;
    }

    public String getUrl() {
        return streamUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDirection() {
        String temp = direction.toLowerCase();
        String directionName = "";
        char[] directionChars = temp.toCharArray();
        for (char dirLetter :
                directionChars) {
            switch (dirLetter) {
                case 'n':
                    directionName = directionName.concat("North ");
                    break;
                case 's':
                    directionName = directionName.concat("South ");
                    break;
                case 'e':
                    directionName = directionName.concat("East ");
                    break;
                case 'w':
                    directionName = directionName.concat("West ");
                    break;
            }
        }
        return directionName;
    }

    protected ATCamera(Parcel in) {
        id = in.readLong();
        controlType = in.readString();
        imageSupported = in.readInt();
        imageUrl = in.readString();
        long tmpLastUpdate = in.readLong();
        lastUpdate = tmpLastUpdate != -1 ? new Date(tmpLastUpdate) : null;
        organizationId = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        streamUrl = in.readString();
        primaryRoad = in.readString();
        mileMarker = in.readDouble();
        direction = in.readString();
        crossStreet = in.readString();
        requestCommand = in.readString();
        name = in.readString();
        deviceId = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(controlType);
        dest.writeInt(imageSupported);
        dest.writeString(imageUrl);
        dest.writeLong(lastUpdate != null ? lastUpdate.getTime() : -1L);
        dest.writeString(organizationId);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(streamUrl);
        dest.writeString(primaryRoad);
        dest.writeDouble(mileMarker);
        dest.writeString(direction);
        dest.writeString(crossStreet);
        dest.writeString(requestCommand);
        dest.writeString(name);
        dest.writeLong(deviceId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ATCamera> CREATOR = new Parcelable.Creator<ATCamera>() {
        @Override
        public ATCamera createFromParcel(Parcel in) {
            return new ATCamera(in);
        }

        @Override
        public ATCamera[] newArray(int size) {
            return new ATCamera[size];
        }
    };

    public String getDescription() {
        return primaryRoad + " & " + crossStreet + " heading " + getDirection();

    }


    public String getId() {
        return String.valueOf(id);
    }

    public String say() {
        return "Camera at " + primaryRoad + "at" + crossStreet;
    }
}
