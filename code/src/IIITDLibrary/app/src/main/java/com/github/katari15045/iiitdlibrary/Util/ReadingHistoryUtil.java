package com.github.katari15045.iiitdlibrary.Util;

/**
 * Created by Mayank on 3/11/2018.
 */

public class ReadingHistoryUtil
{
    private String title;
    private String itemtype;
    private String date;

    public ReadingHistoryUtil(String temp_title, String temp_itemtype, String temp_date)
    {
        this.title = temp_title;
        this.itemtype = temp_itemtype;
        this.date = temp_date;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getItemtype()
    {
        return this.itemtype;
    }
    public String getDate()
    {
        return this.date;
    }
}
