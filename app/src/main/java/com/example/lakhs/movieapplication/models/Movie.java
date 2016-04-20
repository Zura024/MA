package com.example.lakhs.movieapplication.models;

import android.graphics.Bitmap;

/**
 * Created by lakhs on 3/14/2016.
 */
public class Movie {
    String title,year,rate,desc,rsc,id;
    Bitmap bitmap;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setRsc(String rsc) {
        this.rsc = rsc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRsc() {
        return rsc;
    }

    public String getDesc() {
        return desc;
    }

    public String getRate() {
        return rate;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

}
