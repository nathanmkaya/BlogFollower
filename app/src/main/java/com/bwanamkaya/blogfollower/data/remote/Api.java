package com.bwanamkaya.blogfollower.data.remote;

import com.bwanamkaya.blogfollower.data.model.Channel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bwana.mkaya on 15/01/16.
 */
public interface Api {

    @GET("")
    Call<Channel> feed();
}
