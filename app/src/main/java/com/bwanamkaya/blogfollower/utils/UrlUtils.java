package com.bwanamkaya.blogfollower.utils;

import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by bwana.mkaya on 10/01/16.
 */
public class UrlUtils {

    public static final String query = SQLiteQueryBuilder.buildQueryString(false, BLOGS.TABLE_NAME,
            new String[]{BLOGS.COLUMN_TITLE, BLOGS.COLUMN_LINK, BLOGS.COLUMN_DESCRIPTION, BLOGS.COLUMN_BUILD_DATE},
            null, null, null, null, null);
    private static final String queryUrl = "https://query.yahooapis.com/v1/public/yql?q=";
    private static final String queryUrlsuffix = "&format=json&callback=";
    private static final String SinglequeryUrl = "select channel.title, channel.link, channel.description, channel.lastBuildDate from xml where url='httt://chrislema.com/feed/'";
    private static String[] suffix = new String[]{"/feeds/posts/default?alt=rss", "/rss", "/feed/"};

    public static List<String> buildUrls(String url) {
        List<String> possibleUrl = new ArrayList<>();
        for (String end : suffix) {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority(url);
            possibleUrl.add(builder.build().toString() + end);
        }
        return possibleUrl;
    }

    public static Response checkUrl(String url) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .followRedirects(false)
                .followSslRedirects(false)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request)
                .execute();
        response.request().url();
        return response;
    }

    public static interface POSTS {
        public static final String TABLE_NAME = "posts";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_LINK = "link";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PUBDATE = "pubDate";
        public static final String COLUMN_TITLE_BLOGS = "title_blogs";
    }

    public static interface BLOGS {
        public static final String TABLE_NAME = "xml";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "channel.title";
        public static final String COLUMN_LINK = "channel.link";
        public static final String COLUMN_DESCRIPTION = "channel.description";
        public static final String COLUMN_BUILD_DATE = "channel.lastBuildDate";
        public static final String COLUMN_FEED_URL = "feed_url";

    }
}
