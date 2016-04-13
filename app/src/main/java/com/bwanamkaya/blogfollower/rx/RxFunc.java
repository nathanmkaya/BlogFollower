package com.bwanamkaya.blogfollower.rx;

import android.content.ContentValues;
import android.content.Context;

import com.bwanamkaya.blogfollower.data.local.DBContract;
import com.bwanamkaya.blogfollower.data.local.DBResolver;
import com.bwanamkaya.blogfollower.data.local.Tables;
import com.bwanamkaya.blogfollower.data.model.Channel;
import com.bwanamkaya.blogfollower.services.BlodAddService;
import com.bwanamkaya.blogfollower.utils.LogUtils;
import com.bwanamkaya.blogfollower.utils.UrlUtils;

import java.io.IOException;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bwana.mkaya on 12/01/16.
 */
public class RxFunc {
    public static final String TAG = LogUtils.makeLogTag(RxFunc.class);
    public static String checkedUrl;
    public static Action1<String> onNext = new Action1<String>() {
        @Override
        public void call(String s) {

            LogUtils.LOGI(TAG, s);
        }
    };
    private static Response response;
    private static Channel blog;
    private static Action1<Throwable> onError = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {

            LogUtils.LOGE(TAG, ": Error", throwable);
        }
    };

    public static String validUrl(final String url) {

        Observable<String> validUrl = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (String link : UrlUtils.buildUrls(url)) {
                    //LogUtils.LOGI(TAG, link);
                    try {
                        response = UrlUtils.checkUrl(link);
                        if (response.header("Content-Type").equals("text/xml;charset=utf-8") || response.header("Content-Type").equals("application/rss+xml;charset=UTF-8") && !response.header("Content-Type").equals("text/html")) {
                            subscriber.onNext(link);
                            //LogUtils.LOGI(TAG, link);

                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        });

        Action1<String> onNext = new Action1<String>() {
            @Override
            public void call(String s) {
                checkedUrl = s;
                LogUtils.LOGI(TAG, s);
            }
        };

        validUrl.observeOn(Schedulers.io()).subscribe(onNext, onError);
        //LogUtils.LOGI(TAG, checkedUrl);
        return checkedUrl;
    }

    public static void wait(/*final DBFunctions dbFunctions*/final Context context, final String url, final String name) {
        Observable<String> validUrl = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(validUrl(url));
            }
        });

        Action1<String> onNext = new Action1<String>() {
            @Override
            public void call(String s) {
                ContentValues values = new ContentValues();
                values.put(Tables.BLOGS.TABLE_NAME, name);
                values.put(Tables.BLOGS.COLUMN_LINK, s);
                DBResolver dbResolver = new DBResolver(context);
                dbResolver.insert(DBContract.Blogs.CONTENT_URI, values);
                //dbFunctions.newBlog(name, s);
            }
        };

        Action1<Throwable> onError = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.LOGE(TAG, ": Error", throwable);
            }
        };

        validUrl.observeOn(Schedulers.io()).subscribe(onNext, onError, new Action0() {
            @Override
            public void call() {
                response.body().close();
                BlodAddService blodAddService = new BlodAddService();
            }
        });
    }

    public static Channel blog(final String url) {

        Observable<Response> channel = Observable.create(new Observable.OnSubscribe<Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                for (String link : UrlUtils.buildUrls(url)) {
                    //LogUtils.LOGI(TAG, link);
                    try {
                        response = UrlUtils.checkUrl(link);
                        if (response.header("Content-Type").equals("text/xml;charset=utf-8") || response.header("Content-Type").equals("application/rss+xml;charset=UTF-8") && !response.header("Content-Type").equals("text/html")) {
                            subscriber.onNext(response);
                            //LogUtils.LOGI(TAG, link);

                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Action1<Response> responseAction = new Action1<Response>() {
            @Override
            public void call(Response response) {
                try {
                    String xml = response.body().string().toString();

                    System.out.println(UrlUtils.query);
                    //System.out.println(response.body().string().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    response.body().close();
                }
            }
        };

        channel.subscribeOn(Schedulers.io()).subscribe(responseAction, onError);

        return blog;
    }
}
