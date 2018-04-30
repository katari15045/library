package com.github.katari15045.iiitdlibrary.Util;

/**
 * Created by Mayank on 3/11/2018.
 */

public class SuggestionUtil
{
    private String title;
    private String author;
    private String copyright;
    private String standardNumber;
    private String publisher;
    private String collectionTitle;
    private String publicationPlace;
    private String item;
    private String reason;
    private String notes;


    public SuggestionUtil(String title, String author, String copyright,
                        String standardNumber, String publisher, String collectionTitle,
                          String publicationPlace, String item, String reason, String notes)
    {
        this.title = title;
        this.author = author;
        this.copyright = copyright;
        this.standardNumber = standardNumber;
        this.publisher = publisher;

        this.collectionTitle = collectionTitle;
        this.publicationPlace = publicationPlace;
        this.item = item;
        this.reason = reason;
        this.notes = notes;
    }

    public String getTitle()
    {
        return this.title;
    }
    private String getAuthor()
    {
        return this.author;
    }
    private String getCopyright()
    {
        return this.copyright;
    }
    private String getStandardNumber()
    {
        return this.standardNumber;
    }
    private String getPublisher()
    {
        return this.publisher;
    }
    private String getCollectionTitle()
    {
        return this.collectionTitle;
    }
    private String getPublicationPlace()
    {
        return this.publicationPlace;
    }
    private String getItem()
    {
        return item;
    }
    private String getReason()
    {
        return this.reason;
    }
    private String getNotes()
    {
        return this.notes;
    }
}
