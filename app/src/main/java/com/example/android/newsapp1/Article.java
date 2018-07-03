package com.example.android.newsapp1;

import android.text.Html;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Article {

    private String title;
    private String url;
    private String imageUrl;
    private String publishedDate;
    private String authorName;
    private String authorSurname;

    public Article(String title, String url, String imageUrl, String publishedDate, String authorName, String authorSurname) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedDate = publishedDate;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getAuthor() {
        if (authorName != null && authorSurname != null) {
            return StringUtils.capitalize(authorName) + " " + StringUtils.capitalize(authorSurname);
        }
        return null;
    }

    private Date fromISO8601UTC(String dateStr) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFormatedDate() {
        Date date = fromISO8601UTC(publishedDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM. yyyy");
        return dateFormat.format(date);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                '}';
    }
}
