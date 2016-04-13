package com.bwanamkaya.blogfollower.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

/**
 * Created by bwana.mkaya on 17/01/16.
 */
public class DBResolver {

    private final Context context;

    public DBResolver(Context context) {
        this.context = context;
    }

    public void insert(Uri uri, ContentValues values) {
        context.getContentResolver().insert(uri, values);
    }
}
