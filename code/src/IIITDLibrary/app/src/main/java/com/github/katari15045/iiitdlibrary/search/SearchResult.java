package com.github.katari15045.iiitdlibrary.search;

/**
 * Created by Saketh Katari on 27-04-2018.
 */

public class SearchResult {
    private String title = null;
    private String author = null;
    private String biblioNum = null;

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

    public String getBiblioNum() {
        return biblioNum;
    }

    public void setBiblioNum(String biblioNum) {
        this.biblioNum = biblioNum;
    }
}
