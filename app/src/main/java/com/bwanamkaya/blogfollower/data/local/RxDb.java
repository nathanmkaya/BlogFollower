package com.bwanamkaya.blogfollower.data.local;

import com.bwanamkaya.blogfollower.utils.LogUtils;

import rx.functions.Action1;

/**
 * Created by bwana.mkaya on 15/01/16.
 */
public class RxDb {
    private static final String TAG = LogUtils.makeLogTag(RxDb.class);

    public static Action1<String> RxCreate = new Action1<String>() {
        @Override
        public void call(String s) {

            LogUtils.LOGI(TAG, s);
        }
    };

}
