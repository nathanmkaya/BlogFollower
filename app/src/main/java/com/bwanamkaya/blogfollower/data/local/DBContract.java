package com.bwanamkaya.blogfollower.data.local;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by bwana.mkaya on 17/01/16.
 */
public final class DBContract {

    public static final String AUTHORITY = "com.bwanamkaya.blogfollower.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class Posts implements Tables.POSTS, BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath("posts").build();

        public static final String DEFAULT_SORT = Tables.POSTS.COLUMN_PUBDATE + " ASC";

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/postItem";

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/postItem";

        public static String getPostId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    public static final class Blogs implements Tables.BLOGS, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath("blogs").build();

        public static final String DEFAULT_SORT = Tables.BLOGS.COLUMN_BUILD_DATE + " ASC";

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/blogItem";

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/blogItem";

        public static String getBlogId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
