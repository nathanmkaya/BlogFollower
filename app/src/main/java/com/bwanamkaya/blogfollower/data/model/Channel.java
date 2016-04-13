package com.bwanamkaya.blogfollower.data.model;

import java.util.Date;

/**
 * Created by bwana.mkaya on 15/01/16.
 */
public class Channel {

    public String title;

    public String link;

    public String description;

    public Date BuildDate;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Channel{");
        sb.append("BuildDate=").append(BuildDate);
        sb.append(", title='").append(title).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
