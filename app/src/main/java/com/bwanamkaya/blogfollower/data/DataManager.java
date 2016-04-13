package com.bwanamkaya.blogfollower.data;

import com.bwanamkaya.blogfollower.data.model.Channel;
import com.bwanamkaya.blogfollower.data.remote.RemoteHelper;
import com.bwanamkaya.blogfollower.utils.LogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bwana.mkaya on 30/12/15.
 */
public class DataManager {
    private static final String TAG = LogUtils.makeLogTag(DataManager.class);
    RemoteHelper remoteHelper;

    public DataManager(String url) {
        remoteHelper = new RemoteHelper(url);
    }

    public void getFeed() {
        Call<Channel> channelCall = remoteHelper.getApi().feed();
        channelCall.enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(Response<Channel> response) {
                LogUtils.LOGI(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtils.LOGE(TAG, t.getMessage());
            }
        });
    }
}
