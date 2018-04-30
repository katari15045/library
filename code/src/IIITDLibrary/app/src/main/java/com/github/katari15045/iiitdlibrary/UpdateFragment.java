package com.github.katari15045.iiitdlibrary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.CheckUtil.UpdateSanityCheck;
import com.github.katari15045.iiitdlibrary.Util.UsernameUtil;

import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.transform.Result;


public class UpdateFragment extends Fragment {

    private View  changePasswordView;
    private View changeProfileDeatilsView;
    private static ResultSet resultSet;

    private int flag0 = 0;
    private int flag1= 0;
    private int flag2= 0;

    TextView expirationDateTextView;
    TextView cardNoTextView;

    private ImageView mChangePasswordOld;
    private ImageView mChangePasswordNew;
    private ImageView mChangePasswordConfirm;

    private Spinner spinnerUpdate;
    private Spinner salutation_spinner;

    private EditText surname_edittext;
    private EditText firstname_edittext;
    private EditText primary_email_edittext;
    private EditText secondary_email_editext;
    private CheckBox gender_female;
    private CheckBox gender_male;
    private CheckBox gender_nonspecified;

    private ImageView datePickerImageView;
    public TextView dateOfBirthTextView;
    private Calendar myCalendar = Calendar.getInstance();


    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragment_update_view = inflater.inflate(R.layout.fragment_update, container, false);



        cardNoTextView = (TextView) fragment_update_view.findViewById(R.id.profile_update_cardnumber_textview);
        expirationDateTextView = (TextView) fragment_update_view.findViewById(R.id.profile_update_expirationDate_textview);
        surname_edittext = (EditText) fragment_update_view.findViewById(R.id.surname_edittext);
        firstname_edittext = (EditText) fragment_update_view.findViewById(R.id.firstname_edittext);
        primary_email_edittext = (EditText) fragment_update_view.findViewById(R.id.primary_email_edittext);
        secondary_email_editext = (EditText) fragment_update_view.findViewById(R.id.secondary_email_edittext);
        gender_female = (CheckBox) fragment_update_view.findViewById(R.id.gender_female_checkbox);
        gender_male = (CheckBox) fragment_update_view.findViewById(R.id.gender_male_checkbox);
        gender_nonspecified = (CheckBox) fragment_update_view.findViewById(R.id.gender_none_specified_checkbox);
        dateOfBirthTextView = (TextView) fragment_update_view.findViewById(R.id.profile_dateofbirth);
        datePickerImageView = (ImageView) fragment_update_view.findViewById(R.id.datepickerIcon);

        // Inflate the layout for this fragment
        changePasswordView = (View) fragment_update_view.findViewById(R.id.profile_activity_update_password);
        changeProfileDeatilsView = (View) fragment_update_view.findViewById(R.id.profile_activity_update_profile);

        changeProfileDeatilsView.setVisibility(View.GONE);
        changeProfileDeatilsView.setVisibility(View.GONE);

        spinnerUpdate = (Spinner) fragment_update_view.findViewById(R.id.profile_update_spinner);
        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add("Update profile");
        spinnerList.add("Change Password");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUpdate.setAdapter(spinnerAdapter);

        spinnerUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                {
                    changePasswordView.setVisibility(View.GONE);
                    changeProfileDeatilsView.setVisibility(View.VISIBLE);
                    //fetch_details();
                }
                else if(i == 1)
                {
                    changePasswordView.setVisibility(View.VISIBLE);
                    changeProfileDeatilsView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final ImageView oldPassword_visibility = fragment_update_view.findViewById(R.id.changepassword_visibility_oldPassword);
        final ImageView newPassword_visibility = fragment_update_view.findViewById(R.id.changepassword_visibility_newPassword);
        final ImageView confirmPassword_visibility = fragment_update_view.findViewById(R.id.changepassword_visibility_confirmPassword);

        oldPassword_visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag0 == 0)
                {
                    flag0 = 1;
                    EditText oldpassword =  fragment_update_view.findViewById(R.id.oldPasswordEditText);
                    oldpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    oldpassword.setSelection(oldpassword.getText().length());
                    oldPassword_visibility.setImageResource(R.drawable.ic_visibility_off_black_36dp);
                }
                else
                {
                    flag0=0;
                    EditText oldpassword =  fragment_update_view.findViewById(R.id.oldPasswordEditText);
                    oldpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    oldpassword.setSelection(oldpassword.getText().length());
                    oldPassword_visibility.setImageResource(R.drawable.ic_visibility_black_36dp);
                }
            }
        });
        newPassword_visibility.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag1 == 0)
                {
                    flag1 = 1;
                    EditText newpassword =  fragment_update_view.findViewById(R.id.newPasswordEditText);
                    newpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newpassword.setSelection(newpassword.getText().length());
                    newPassword_visibility.setImageResource(R.drawable.ic_visibility_off_black_36dp);
                }
                else
                {
                    flag1=0;
                    EditText newpassword =  fragment_update_view.findViewById(R.id.newPasswordEditText);
                    newpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    newpassword.setSelection(newpassword.getText().length());
                    newPassword_visibility.setImageResource(R.drawable.ic_visibility_black_36dp);
                }
            }
        });

        confirmPassword_visibility.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag2 == 0)
                {
                    flag2 = 1;
                    EditText confirmpassword =  fragment_update_view.findViewById(R.id.confirmPasswordEditText);
                    confirmpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    confirmpassword.setSelection(confirmpassword.getText().length());
                    confirmPassword_visibility.setImageResource(R.drawable.ic_visibility_off_black_36dp);
                }
                else
                {
                    flag2=0;
                    EditText confirmpassword =  fragment_update_view.findViewById(R.id.confirmPasswordEditText);
                    confirmpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    confirmpassword.setSelection(confirmpassword.getText().length());
                    confirmPassword_visibility.setImageResource(R.drawable.ic_visibility_black_36dp);
                }
            }
        });


        salutation_spinner = (Spinner) fragment_update_view.findViewById(R.id.profile_salutation);
        ArrayList<String> spinner_adapter_data = getSpinnerAdapter();
        ArrayAdapter<String> spinnerSalutationAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinner_adapter_data);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        salutation_spinner.setAdapter(spinnerSalutationAdapter);

        Button cancel_button_password = fragment_update_view.findViewById(R.id.update_password_cancel);
        cancel_button_password.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText oldPasswordView = (EditText) fragment_update_view.findViewById(R.id.oldPasswordEditText);
                EditText newPasswordView = (EditText) fragment_update_view.findViewById(R.id.newPasswordEditText);
                EditText confirmPasswordView = (EditText) fragment_update_view.findViewById(R.id.confirmPasswordEditText);

                oldPasswordView.setText("");
                newPasswordView.setText("");
                confirmPasswordView.setText("");

            }
        });

        Button submit_button_password = (Button) fragment_update_view.findViewById(R.id.profile_update_changepassword_sumbit);
        submit_button_password.setOnClickListener(new View.OnClickListener()
        {
            EditText oldPasswordView = (EditText) fragment_update_view.findViewById(R.id.oldPasswordEditText);
            EditText newPasswordView = (EditText) fragment_update_view.findViewById(R.id.newPasswordEditText);
            EditText confirmPasswordView = (EditText) fragment_update_view.findViewById(R.id.confirmPasswordEditText);

            @Override
            public void onClick(View view) {
                UpdateSanityCheck sanityCheck = new UpdateSanityCheck(getActivity());
                boolean checkSanityPassword = sanityCheck.PasswordSanityCheck(oldPasswordView.getText().toString(), newPasswordView.getText().toString(), confirmPasswordView.getText().toString());
                if(checkSanityPassword)
                {
                    Toast.makeText(getActivity(), "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Error: Please try again", Toast.LENGTH_SHORT).show();
                    oldPasswordView.setText("");
                    newPasswordView.setText("");
                    confirmPasswordView.setText("");
                }

            }
        });
        Button submit_button_profile = (Button) fragment_update_view.findViewById(R.id.profile_update_submit_button);
        submit_button_profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                UpdateSanityCheck sanityCheck = new UpdateSanityCheck(getActivity());
                boolean profileSanityCheck = sanityCheck.ProfileSanityCheck(fragment_update_view);
                if(profileSanityCheck)
                {
                    Toast.makeText(getActivity(), "Request submitted successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        datePickerImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDatePickerDialog(fragment_update_view);
            }
        });

        ProfileDetailsAsynTask task = new ProfileDetailsAsynTask();
        task.execute(UsernameUtil.username);

        return fragment_update_view;

    }

    public void showDatePickerDialog(View v) {


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH))
                .show();

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateOfBirthTextView.setText(sdf.format(myCalendar.getTime()));
    }

    private ArrayList<String> getSpinnerAdapter()
    {
        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add("Mr");
        spinnerList.add("Mrs");
        spinnerList.add("Miss");
        spinnerList.add("Ms");

        return spinnerList;
    }

    private class ProfileDetailsAsynTask extends AsyncTask<String, Void,Void>
    {
        Database database;
        @Override
        protected Void doInBackground(String... ids) {
            String command="select userid,firstname, cardnumber, surname, email, emailpro, branchcode, dateenrolled, dateexpiry,sex from borrowers where userid=\"" + ids[0] + "\";";
            database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            try {
                dbThread.join();
                resultSet = database.getResultSet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            String userId = "";
            String expiration = "";
            String sex = "";
            String firstname = "";
            String surname = "";
            String email = "";
            String emailpro = "";
            String branchcode = "";
            String dateenrolled = "";
            String cardnumber = "";



            if(resultSet != null ) {
                Log.i(getActivity() + "", "nnnnnnnnnnnnnnnnnnnnnnnnnn");
                try {
                    while (resultSet.next()) {
                        Log.i(getActivity() + "", resultSet.getString("userid") + " ---------------  ");
                        userId = resultSet.getString("userid");
                        expiration = resultSet.getString("dateexpiry");
                        sex = resultSet.getString("sex");
                        firstname = resultSet.getString("firstname");
                        surname = resultSet.getString("surname");
                        email = resultSet.getString("email");
                        emailpro = resultSet.getString("emailpro");
                        branchcode = resultSet.getString("branchcode");
                        dateenrolled = resultSet.getString("dateenrolled");
                        cardnumber = resultSet.getString("cardnumber");


                        Log.i("" + getActivity(), sex);
                        Log.i("" + getActivity(), firstname);
                        Log.i("" + getActivity(), surname);
                        Log.i("" + getActivity(), email);
                        Log.i("" + getActivity(), emailpro);
                        //Log.i(""+getActivity(), branchcode);
                        //Log.i(""+getActivity(), dateenrolled);
                        Log.i("" + getActivity(), expiration);
                        Log.i("" + getActivity(), cardnumber);

                        break;
                    }
                    Log.i("" + getActivity(), "***************** ");

                    cardNoTextView.setText(cardnumber);
                    firstname_edittext.setText(firstname);
                    surname_edittext.setText(surname);
                    expirationDateTextView.setText(expiration);
                    primary_email_edittext.setText(email);
                    secondary_email_editext.setText(emailpro);
                    if (sex.equals("M")) {
                        gender_male.setChecked(true);
                    } else if (sex.equals("F")) {
                        gender_female.setChecked(true);
                    } else {
                        gender_nonspecified.setChecked(true);
                    }

                } catch (SQLException e) {
                    Log.i(getActivity() + "", "FUCK -------------------");
                }
            }

            database.close();
        }

        public void setDateOfBirth(String birth)
        {
            dateOfBirthTextView.setText(birth);
        }
    }


}
