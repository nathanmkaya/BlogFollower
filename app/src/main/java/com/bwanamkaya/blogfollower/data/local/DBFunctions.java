package com.bwanamkaya.blogfollower.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bwanamkaya.blogfollower.data.model.Blog;
import com.bwanamkaya.blogfollower.utils.LogUtils;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by bwana.mkaya on 30/12/15.
 */
public class DBFunctions {

    static final String TAG = LogUtils.makeLogTag(DBFunctions.class);
    private DBHelper helper;
    private SQLiteDatabase db;


    public DBFunctions(Context context) {
        helper = new DBHelper(context.getApplicationContext());
        db = helper.getWritableDatabase();
    }

    /*
    Blog Table -- Beginning
     */

    //Create item
    public void newBlog(String name, String url) {
        LogUtils.LOGI(TAG, name + " : " + url);
        Blog blog = new Blog(name, url);
        long id = cupboard().withDatabase(db).put(blog);
    }

    // Get Blog by id
    public Blog getBlog(long id) {
        Blog blog = cupboard().withDatabase(db).get(Blog.class, id);
        return blog;
    }

    public Blog firstBlog() {
        Blog blog = cupboard().withDatabase(db).query(Blog.class).get();
        return blog;
    }

    public void updateBlog(String name, String url, String validUrl) {
        throw new UnsupportedOperationException("Not yet implemented");
        /*ContentValues values = new ContentValues();
        values.put("validUrl", validUrl);
        Blog blog = new Blog(name, url);
        long id = cupboard().withDatabase(db).update(Blog.class, values,"name = ?", name);
        */
    }

    // Get Blog by name
    public Blog getBlog(String name) {
        Blog blog = cupboard().withDatabase(db).query(Blog.class).withSelection("name = ?", name).get();
        return blog;
    }

    // Delete by id
    public void deleteBlog_byId(long id) {
        cupboard().withDatabase(db).delete(Blog.class, id);
    }

    // Delete by selection
    public void deleteBlog_bySelection(String name) {
        cupboard().withDatabase(db).delete(Blog.class, "name = ?", name);
    }

    // Delete all blog entries
    public void deleteAllBlogs() {
        cupboard().withDatabase(db).delete(Blog.class, null);
    }

    /*
    Blog Table -- End
     */

    /*
    Posts Table -- Beginning
     */

    /*
    Posts Table -- End
     */

}
