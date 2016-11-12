package com.example.afleshner.therealmvp.web;

import com.example.afleshner.therealmvp.objects.ATIdentity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by afleshner on 11/7/2016.
 */

public interface IdentityApi {

    @FormUrlEncoded
    @POST("identity/connect/token")
    Call<ATIdentity> getIdentity(@Field("client_id") String client_id,
                                 @Field("client_secret") String client_secret,
                                 @Field("scope") String scope,
                                 @Field("grant_type") String grant_type);
}
