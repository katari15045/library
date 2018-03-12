package com.github.katari15045.iiitdlibrary;

import android.graphics.Bitmap;

/**
 * Created by Saketh Katari on 05-03-2018.
 */

// POJO class for an item in E-Resources Slider
public class EResourceCard {
    private int image = -1;
    String urlString = null;

    EResourceCard(int image, String urlString){
        this.image = image;
        this.urlString = urlString;
    }

    void setImage(int image){
        this.image = image;
    }

    int getImage(){
        return image;
    }

    void setUrlString(String urlString){
        this.urlString = urlString;
    }

    String getUrlString(){
        return  urlString;
    }
}
