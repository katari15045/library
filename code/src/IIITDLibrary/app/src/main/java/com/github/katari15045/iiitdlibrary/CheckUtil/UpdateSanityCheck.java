package com.github.katari15045.iiitdlibrary.CheckUtil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;


/**
 * Created by Mayank on 3/15/2018.
 */

public class UpdateSanityCheck
{
    private Context mContext;
    private int password_length = 3;


    public UpdateSanityCheck(Activity context)
    {
        this.mContext = context;
    }


    public boolean ProfileSanityCheck(View view)
    {
        Spinner profile_salutation = (Spinner) view.findViewById(R.id.profile_salutation);

        EditText profile_surname = (EditText) view.findViewById(R.id.surname_edittext);
        EditText profile_firstname = (EditText) view.findViewById(R.id.firstname_edittext);

        TextView profile_dateofbirth = (TextView) view.findViewById(R.id.profile_dateofbirth);
        EditText profile_initials = (EditText) view.findViewById(R.id.profile_intials);

        EditText profile_othername = (EditText) view.findViewById(R.id.profile_othername);

        CheckBox gender_male_checkbox = (CheckBox) view.findViewById(R.id.gender_male_checkbox);
        CheckBox gender_female_checkbox = (CheckBox) view.findViewById(R.id.gender_female_checkbox);
        CheckBox gender_none_specified_checkbox = (CheckBox) view.findViewById(R.id.gender_none_specified_checkbox);

        EditText profile_streetnumber = (EditText) view.findViewById(R.id.profile_streetnumber);

        EditText profile_address = (EditText) view.findViewById(R.id.profile_address);
        EditText profile_address2 = (EditText) view.findViewById(R.id.profile_address2);

        EditText profile_city = (EditText) view.findViewById(R.id.profile_city);
        EditText profile_primaryemail = (EditText) view.findViewById(R.id.primary_email_edittext);

        if(profile_surname.getText().toString().length() <=2)
        {
            if(profile_surname.getText().toString().length() == 0)
            {
                Toast.makeText(mContext, "Enter a surname", Toast.LENGTH_SHORT).show();
                return false;
            }
            else
            {
                Toast.makeText(mContext, "Enter valid surname", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else if(profile_firstname.getText().toString().length() < 2)
        {
            if(profile_firstname.getText().toString().length() == 0)
            {
                Toast.makeText(mContext, "Enter valid firstname", Toast.LENGTH_SHORT).show();
                return false;
            }
            else
            {
                Toast.makeText(mContext, "Enter valid surname", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else if(gender_female_checkbox.isChecked() == false && gender_male_checkbox.isChecked() == false && gender_none_specified_checkbox.isChecked() == false)
        {
            Toast.makeText(mContext, "Select your gender to continue", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(profile_primaryemail.getText().toString().length() == 0)
        {
            Toast.makeText(mContext, "Enter valid primary email address to continue", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!profile_primaryemail.getText().toString().contains("@iiitd.ac.in"))
        {
            Toast.makeText(mContext, "Enter IIITD email address in primary email to continue", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }

    public boolean PasswordSanityCheck(String oldPassword, String newPassword, String confirmPassword)
    {
        if(oldPassword.length() < password_length)
        {
            Toast.makeText(mContext, "Length of Old password cannot be less than 3", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(newPassword.length() < password_length)
        {
            Toast.makeText(mContext, "Length of New password cannot be less than 3", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!newPassword.equals(confirmPassword))
        {
            Toast.makeText(mContext, "New and confirm password do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(confirmPassword.length() < password_length)
        {
            Toast.makeText(mContext, "Length of Confirm password cannot be less than 3", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
}
