package com.example.afleshner.therealmvp.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by afleshner on 11/7/2016.
 */
public class ATCollection<Event extends Object> implements Parcelable {

    private String name;
    private ArrayList<Event> entries;

    public ATCollection() {

    }

    public ATCollection(String name, ArrayList<Event> entries) {
        this.name = name;
        this.entries = entries;
    }

    protected ATCollection(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0x01) {
            entries = new ArrayList<>();
            in.readList(entries, entries.getClass().getClassLoader());
        } else {
            entries = null;
        }
    }


    public ArrayList<Event> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Event> entries) {
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (entries == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(entries);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ATCollection> CREATOR = new Parcelable.Creator<ATCollection>() {
        @Override
        public ATCollection createFromParcel(Parcel in) {
            return new ATCollection(in);
        }

        @Override
        public ATCollection[] newArray(int size) {
            return new ATCollection[size];
        }
    };

}

