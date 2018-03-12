package com.github.katari15045.iiitdlibrary.Helper;

import android.graphics.Bitmap;

/**
 * Created by Saketh Katari on 05-03-2018.
 */

// POJO class for an item in E-Resources Slider
public class EResourceCard {
    private int image = -1;
    private String urlString = null;

    public EResourceCard(int image, String urlString){
        this.image = image;
        this.urlString = urlString;
    }

    public void setImage(int image){
        this.image = image;
    }

    public int getImage(){
        return image;
    }

    public void setUrlString(String urlString){
        this.urlString = urlString;
    }

    public String getUrlString(){
        return  urlString;
    }
}
