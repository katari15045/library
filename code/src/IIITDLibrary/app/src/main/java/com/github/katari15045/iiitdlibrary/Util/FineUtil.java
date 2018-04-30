package com.github.katari15045.iiitdlibrary.Util;

/**
 * Created by Mayank on 3/11/2018.
 */

public class FineUtil
{
    private String mDate;
    private String mDescription;
    private String mFineAmount;
    private String mAmountOutstanding;

    public FineUtil(String iDate, String iDescription,String iFineAmount, String iAmountOutstanding)
    {
        this.mDate = iDate;
        this.mDescription = iDescription;
        this.mFineAmount = iFineAmount;
        this.mAmountOutstanding = iAmountOutstanding;
    }

    public String getDate()
    {
        return this.mDate;
    }
    public String getDescription()
    {
        return this.mDescription;
    }
    public String getFineAmount()
    {
        return this.mFineAmount;
    }
    public String getAmountOutstanding()
    {
        return this.mAmountOutstanding;
    }
}
