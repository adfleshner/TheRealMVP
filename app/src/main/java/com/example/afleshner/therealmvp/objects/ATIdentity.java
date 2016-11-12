package com.example.afleshner.therealmvp.objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by afleshner on 11/7/2016.
 */
public class ATIdentity {

    private String access_token;
    private int expires_in;
    private String token_type;

    private Calendar timeExpires;

    public ATIdentity() {
        //needed when created so that we know what to add the time to.
        timeExpires = Calendar.getInstance();//expires_in is not added here because I dont know what the expires_in actually is and we need to wait until hasExpired() is actually called.... i think.
    }

    public void setIdentity(ATIdentity identity) {
        access_token = identity.access_token;
        expires_in = identity.expires_in;
        token_type = identity.token_type;
    }

    public String getAccessToken() {
        return access_token;
    }

    //helper function to tell whether something expired.
    public boolean hasExpired() {
        //adds the actual time to expire because it changes the actual object instead of being allowed to set it to a new object.
        timeExpires.add(Calendar.SECOND, expires_in); // needs to be added every time it checks.
        return Calendar.getInstance().after(timeExpires);
    }

    public String prettyPrintExpirationDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy'T'hh:mm:ss aa", Locale.US);
        dateFormat.setTimeZone(timeExpires.getTimeZone());
        return dateFormat.format(timeExpires.getTime());
    }


}
