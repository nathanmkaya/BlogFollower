package com.bwanamkaya.blogfollower.data.model;

/**
 * Created by bwana.mkaya on 30/12/15.
 */
public class Blog {
    public Long _id;
    public String name;
    public String link;
    public String host;

    public Blog(String name, String link) {
        this.link = link;
        this.name = name;
    }

    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
