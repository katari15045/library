package com.github.katari15045.iiitdlibrary.biblio;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class Biblio {

    private String biblioNumber = null;
    private String author = null;           // biblio::author
    private String title = null;            // biblio::title
    private String notes = null;            // biblio::notes
    private String copyrightDate = null;    // biblio::copyrightdate
    private String isbn = null;             // biblioitems::isbn
    private String publisher = null;        // biblioitems::publishercode
    private String edition = null;          // biblioitems::editionstatement
    private String pages = null;            // biblioitems::pages

    public String getBiblioNumber() {
        return biblioNumber;
    }

    public void setBiblioNumber(String biblioNumber) {
        this.biblioNumber = biblioNumber;
    }

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCopyrightDate() {
        return copyrightDate;
    }

    public void setCopyrightDate(String copyrightDate) {
        this.copyrightDate = copyrightDate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
