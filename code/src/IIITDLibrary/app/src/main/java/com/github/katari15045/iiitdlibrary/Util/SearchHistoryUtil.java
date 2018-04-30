package com.github.katari15045.iiitdlibrary.Util;

import android.util.Log;

/**
 * Created by Mayank on 3/11/2018.
 */

public class SearchHistoryUtil
{
    private boolean mchecked;
    private String mDate;
    private String mTime;
    private String mSearch;
    private int mRseult;

    public SearchHistoryUtil(boolean checked, String newDate, String time_temp, String temp_search, int temp_result)
    {
        this.mchecked = checked;
        this.mDate = newDate;
        this.mTime = time_temp;
        this.mSearch = temp_search;
        this.mRseult = temp_result;
    }
    public void setChecked(boolean var)
    {
        this.mchecked = var;
    }
    public boolean getChecked()
    {
        return this.mchecked;
    }
    public String getDate()
    {
        return this.mDate;
    }
    public String getTime()
    {
        return  this.mTime;
    }
    public String getSearch()
    {
        return this.mSearch;
    }
    public int getResult()
    {
        return this.mRseult;
    }
}
