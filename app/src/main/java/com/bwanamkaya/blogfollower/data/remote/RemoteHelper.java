package com.bwanamkaya.blogfollower.data.remote;


import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.SimpleXmlConverterFactory;

/**
 * Created by bwana.mkaya on 15/01/16.
 */
public class RemoteHelper {
    Api api;

    public RemoteHelper(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //.baseUrl(MemoApi.BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public Api getApi() {
        return api;
    }
}
