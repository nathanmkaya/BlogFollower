package com.bwanamkaya.blogfollower.data.local;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static com.bwanamkaya.blogfollower.utils.LogUtils.LOGE;
import static com.bwanamkaya.blogfollower.utils.LogUtils.LOGW;
import static com.bwanamkaya.blogfollower.utils.LogUtils.makeLogTag;

/**
 * Created by bwana.mkaya on 30/12/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = makeLogTag(DBHelper.class);

    private final Context context;


    public DBHelper(Context context) {
        super(context, Tables.DATABASE_NAME, null, Tables.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LOGW(TAG, "Creating Database");

        try {
            db.execSQL("CREATE TABLE " + Tables.BLOGS.TABLE_NAME + " (" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Tables.BLOGS.COLUMN_TITLE + " TEXT NOT NULL," +
                    Tables.BLOGS.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
                    Tables.BLOGS.COLUMN_LINK + " TEXT NOT NULL," +
                    Tables.BLOGS.COLUMN_FEED_URL + " TEXT NOT NULL," +
                    "UNIQUE (" + Tables.BLOGS.COLUMN_TITLE + ") ON CONFLICT REPLACE)");

            db.execSQL("CREATE TABLE " + Tables.POSTS.TABLE_NAME + " (" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Tables.POSTS.COLUMN_TITLE + " TEXT NOT NULL," +
                    Tables.POSTS.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
                    Tables.POSTS.COLUMN_PUBDATE + " DATE NOT NULL," +
                    Tables.POSTS.COLUMN_AUTHOR + " TEXT," +
                    Tables.POSTS.COLUMN_LINK + " TEXT NOT NULL," +
                    Tables.POSTS.COLUMN_TITLE_BLOGS + " TEXT NOT NULL," +
                    "UNIQUE (" + Tables.POSTS.COLUMN_TITLE + ") ON CONFLICT REPLACE," +
                    "FOREIGN KEY (" + Tables.POSTS.COLUMN_TITLE_BLOGS + ") REFERENCES " +
                    Tables.BLOGS.TABLE_NAME + "(" + Tables.BLOGS.COLUMN_TITLE + ") )");
        } catch (SQLException e) {
            LOGE(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LOGW(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Tables.POSTS.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Tables.BLOGS.TABLE_NAME);
        } catch (SQLException e) {
            LOGE(TAG, e.getMessage());
        }
        onCreate(db);
    }
}
