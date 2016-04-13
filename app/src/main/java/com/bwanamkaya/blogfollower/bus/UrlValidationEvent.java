package com.bwanamkaya.blogfollower.bus;

import com.bwanamkaya.blogfollower.data.model.Blog;

/**
 * Created by bwana.mkaya on 11/01/16.
 */
public class UrlValidationEvent {
    public Blog blog;

    public UrlValidationEvent(Blog blog) {
        this.blog = blog;
    }
}
