package com.github.katari15045.recyclercumcardview;

/**
 * Created by Saketh Katari on 22-02-2018.
 */

public class Card {
    private String title = null;
    private String author = null;
    private String checkouts = null;
    private String holdings = null;
    private int image = -1;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(String checkouts) {
        this.checkouts = checkouts;
    }

    public String getHoldings() {
        return holdings;
    }

    public void setHoldings(String holdings) {
        this.holdings = holdings;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
