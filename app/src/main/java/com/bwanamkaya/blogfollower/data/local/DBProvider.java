package com.bwanamkaya.blogfollower.data.local;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;

public class DBProvider extends ContentProvider {
    private static final int POSTS = 100;
    private static final int POSTS_ID = 110;
    private static final int BLOGS = 200;
    private static final int BLOGS_ID = 210;
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(DBContract.AUTHORITY, "posts", POSTS);
        URI_MATCHER.addURI(DBContract.AUTHORITY, "posts/#", POSTS_ID);
        URI_MATCHER.addURI(DBContract.AUTHORITY, "blogs", BLOGS);
        URI_MATCHER.addURI(DBContract.AUTHORITY, "blogs/#", BLOGS_ID);
    }

    private DBHelper dbHelper;

    public DBProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder builder = builderSimpleSelection(uri);
        final String postId = uri.getLastPathSegment();
        final String blogId = uri.getLastPathSegment();
        switch (URI_MATCHER.match(uri)) {
            case POSTS:
                break;
            case POSTS_ID:
                break;
            case BLOGS:
                break;
            case BLOGS_ID:
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int retVal = db.delete(builder.getTables(), selection, selectionArgs);
        notifyChange(uri);
        return retVal;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case POSTS:
                return DBContract.Posts.CONTENT_TYPE;
            case POSTS_ID:
                return DBContract.Posts.CONTENT_ITEM_TYPE;
            case BLOGS:
                return DBContract.Blogs.CONTENT_TYPE;
            case BLOGS_ID:
                return DBContract.Blogs.CONTENT_ITEM_TYPE;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final long id;
        switch (URI_MATCHER.match(uri)) {
            case POSTS:
                id = db.insert(Tables.POSTS.TABLE_NAME, null, values);
                return getUriForId(id, uri);
            case POSTS_ID:
                id = db.insert(Tables.POSTS.TABLE_NAME, null, values);
                return getUriForId(id, uri);
            case BLOGS:
                id = db.insert(Tables.BLOGS.TABLE_NAME, null, values);
                return getUriForId(id, uri);
            case BLOGS_ID:
                id = db.insert(Tables.BLOGS.TABLE_NAME, null, values);
                return getUriForId(id, uri);
            default:
                throw new UnsupportedOperationException("Unknown uri for " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder builder = builderSimpleSelection(uri);
        switch (URI_MATCHER.match(uri)) {
            case POSTS:
                break;
            case POSTS_ID:
                break;
            case BLOGS:
                break;
            case BLOGS_ID:
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder builder = builderSimpleSelection(uri);
        final String postId = uri.getLastPathSegment();
        final String blogId = uri.getLastPathSegment();
        switch (URI_MATCHER.match(uri)) {
            case POSTS:
                break;
            case POSTS_ID:
                break;
            case BLOGS:
                break;
            case BLOGS_ID:
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int retVal = db.update(builder.getTables(), values, selection, selectionArgs);
        notifyChange(uri);
        return retVal;
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentProviderResult[] results = applyBatch(operations);
            db.setTransactionSuccessful();
            getContext().getContentResolver().notifyChange(DBContract.BASE_CONTENT_URI, null);
            return results;

        } finally {
            db.endTransaction();
        }
    }

    private void notifyChange(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    private SQLiteQueryBuilder builderSimpleSelection(Uri uri) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        final String postId = DBContract.Posts.getPostId(uri);
        final String blogId = DBContract.Blogs.getBlogId(uri);
        switch (URI_MATCHER.match(uri)) {
            case POSTS:
                builder.setTables(Tables.POSTS.TABLE_NAME);
            case POSTS_ID:
                builder.setTables(Tables.POSTS.TABLE_NAME);
                builder.appendWhere(Tables.POSTS.COLUMN_ID + "=?" + postId);
            case BLOGS:
                builder.setTables(Tables.BLOGS.TABLE_NAME);
            case BLOGS_ID:
                builder.setTables(Tables.BLOGS.TABLE_NAME);
                builder.appendWhere(Tables.BLOGS.COLUMN_ID + "=?" + blogId);
        }
        return builder;
    }

    private Uri getUriForId(long id, Uri uri) {
        if (id > 0) {
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            notifyChange(itemUri);
            return itemUri;
        }
        // s.th. went wrong:
        throw new SQLException("Problem while inserting into uri: " + uri);
    }
}
