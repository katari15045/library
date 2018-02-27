package com.github.katari15045.mysql;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.activity_main_button_execute);
        button.setOnClickListener(new CommandListener(this));
    }
}

class CommandListener implements View.OnClickListener{

    private Context context = null;
    private static AsyncTask<Void, Void, Void> postExecTask = null;

    CommandListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view){
        final MainActivity mainActivity = (MainActivity) context;
        EditText editText = mainActivity.findViewById(R.id.activity_main_edit_text_command);
        String command = editText.getText().toString();
        final RadioButton radioButton = mainActivity.findViewById(R.id.activity_main_radio_button);
        final Database database = new Database(context, command, radioButton.isChecked());
        postExecTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                TextView textView = mainActivity.findViewById(R.id.activity_main_text_view_result);
                textView.setText(database.getResult());
                radioButton.setChecked(false);
            }
        };
        database.setPostExecTask(postExecTask);
        database.execute();
    }
}









