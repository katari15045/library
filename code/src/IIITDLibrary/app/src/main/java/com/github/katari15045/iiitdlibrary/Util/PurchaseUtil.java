package com.github.katari15045.iiitdlibrary.Util;

import java.io.Serializable;

/**
 * Created by Mayank on 3/16/2018.
 */

public class PurchaseUtil implements Serializable {
    private String title;
    private String author;
    private String copyrightDate;
    private String stdNumber;
    private String publisher;
    private String collectionTitle;
    private String publicationPlace;

    public PurchaseUtil(String mTitle, String mAuthor, String mCopyrightDate, String mStdNumber, String mPublisher, String mCollectionTitle, String mPublicationPlace)
    {
        this.title = mTitle;
        this.author = mAuthor;
        this.copyrightDate = mCopyrightDate;
        this.stdNumber = mStdNumber;
        this.publisher = mPublisher;
        this.collectionTitle = mCollectionTitle;
        this.publicationPlace = mPublicationPlace;
    }

    public String getTitle()
    {
        return this.title;
    }
    public String getAuthor()
    {
        return this.author;
    }
    public String getCopyrightDate()
    {
        return this.copyrightDate;
    }
    public String getStdNumber()
    {
        return this.stdNumber;
    }
    public String getPublisher()
    {
        return this.publisher;
    }

    public String getCollectionTitle()
    {
        return this.collectionTitle;
    }
    public String getPublicationPlace()
    {
        return this.publicationPlace;
    }
}
