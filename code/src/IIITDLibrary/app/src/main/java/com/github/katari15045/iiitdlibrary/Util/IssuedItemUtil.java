package com.github.katari15045.iiitdlibrary.Util;

/**
 * Created by Mayank on 3/16/2018.
 */

public class IssuedItemUtil
{
    private String title;
    private String barcode;
    private String callno;
    private String duedate;
    private String fine;

    public IssuedItemUtil(String mTitle, String mBarCode, String mCallNo, String mDueDate, String mFine)
    {
        this.title = mTitle;
        this.barcode = mBarCode;
        this.callno = mCallNo;
        this.duedate = mDueDate;
        this.fine = mFine;
    }

    public String getTitle()
    {
        return this.title;
    }
    public String getBarcode()
    {
        return this.barcode;
    }
    public String getCallno()
    {
        return this.callno;
    }
    public String getDuedate()
    {
        return this.duedate;
    }
    public String getFine()
    {
        return this.fine;
    }
}

