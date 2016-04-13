package com.bwanamkaya.blogfollower.data.local;

/**
 * Created by bwana.mkaya on 17/01/16.
 */
public final class Tables {

    public static final String DATABASE_NAME = "blogfollower.db";

    public static final int DATABASE_VERSION = 1;


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
        public static final String TABLE_NAME = "blogs";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_LINK = "link";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_BUILD_DATE = "lastBuildDate";
        public static final String COLUMN_FEED_URL = "feed_url";

    }
}
