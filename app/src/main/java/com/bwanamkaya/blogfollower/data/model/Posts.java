package com.bwanamkaya.blogfollower.data.model;

import java.util.Date;

/**
 * Created by bwana.mkaya on 30/12/15.
 */

public class Posts {

    public Long _id;

    public String title;

    public String author;

    public String link;

    public String description;

    public Date pub_date;


    public Posts(String author, String description, String link, Date pub_date, String title) {
        this.author = author;
        this.description = description;
        this.link = link;
        this.pub_date = pub_date;
        this.title = title;
    }

    public Posts() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Posts{");
        sb.append("_id=").append(_id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", pub_date=").append(pub_date);
        sb.append('}');
        return sb.toString();
    }
}
