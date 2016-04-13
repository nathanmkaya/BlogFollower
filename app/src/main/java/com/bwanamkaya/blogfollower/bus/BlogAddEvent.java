package com.bwanamkaya.blogfollower.bus;

/**
 * Created by bwana.mkaya on 31/12/15.
 */
public class BlogAddEvent {
    public String blog_name;
    public String blog_url;

    public BlogAddEvent(String blog_name, String blog_url) {
        this.blog_name = blog_name;
        this.blog_url = blog_url;
    }

    @Override
    public String toString() {
        return "BlogAddEvent{" +
                "blog_name='" + blog_name + '\'' +
                ", blog_url='" + blog_url + '\'' +
                '}';
    }
}
