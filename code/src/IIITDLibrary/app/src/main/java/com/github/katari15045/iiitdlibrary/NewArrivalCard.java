package com.github.katari15045.iiitdlibrary;

import android.graphics.Bitmap;

/**
 * Created by Saketh Katari on 23-02-2018.
 */

public class NewArrivalCard {
    private Bitmap image = null;
    private String isbn = null;
    private String biblionumber = null;

    String getIsbn(){
        return isbn;
    }

    void setIsbn(String isbn){
        this.isbn = isbn;
    }

    String getBiblionumber(){
        return biblionumber;
    }

    void setBiblionumber(String biblionumber){
        this.biblionumber = biblionumber;
    }

    void setImage(Bitmap image){
        this.image = image;
    }

    Bitmap getImage(){
        return image;
    }
}
