package com.github.katari15045.iiitdlibrary.Home;

import android.graphics.Bitmap;

/**
 * Created by Saketh Katari on 23-02-2018.
 */

public class NewArrivalCard {
    private Bitmap image = null;
    private String isbn = null;
    private String biblionumber = null;

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public String getBiblionumber(){
        return biblionumber;
    }

    public void setBiblionumber(String biblionumber){
        this.biblionumber = biblionumber;
    }

    public void setImage(Bitmap image){
        this.image = image;
    }

    public Bitmap getImage(){
        return image;
    }
}
