package com.example.owner.android5778_0920_9377_02.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.owner.android5778_0920_9377_02.R;
import com.example.owner.android5778_0920_9377_02.model.backend.DB_ManagerFactory;
import com.example.owner.android5778_0920_9377_02.model.backend.RentConst;

public class LoginActivity extends Activity implements View.OnClickListener   {
    private EditText firstNameEditText;
    private EditText passwordEditText;
    private Button signButton;
    private Button registerButton;
    boolean exist=false;

    private void findViews() {
        firstNameEditText = (EditText)findViewById( R.id.firstNameEditText );
        passwordEditText = (EditText)findViewById( R.id.passwordEditText );
        signButton = (Button)findViewById( R.id.signButton );
        registerButton = (Button)findViewById( R.id.registerButton );

        signButton.setOnClickListener( this );
        registerButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    /**
     The register button ment for the first use in the application. Its open the option to register and add the new client to the database.
     The sign in button ment for enter to the application if you already register in the database.
     */
    @Override
    public void onClick(View v) {
        if ( v == registerButton ) {
            Intent MyIntent = new Intent(this,AddUserActivity.class);
            startActivity(MyIntent);
        }
        else if (v==signButton) {
            loadSharedPreferences();
        }

    }

    /**
     save the username and password that has been received in order to enable the use just sign in the next time.
     */
    private void saveSharedPreferences()
    {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String firstName = firstNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            editor.putString("NAME", firstName);
            editor.putString("PASSWORD", password);
            editor.commit();
            Toast.makeText(this, "save name and password Preferences", Toast.LENGTH_SHORT).show();

        }
        catch (Exception ex) {
            Toast.makeText(this, "failed to save Preferences", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     check if the username and the password are the last that saved in the application if so open the main window
     else check if the user exist in the database.
     */
    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getString("NAME", null).equals(firstNameEditText.getText().toString())
                && sharedPreferences.getString("PASSWORD", null).equals(passwordEditText.getText().toString()))
        {
            Intent MyIntent = new Intent(this,MainActivity.class);
            startActivity(MyIntent);
        }
        else {
            checkUser(passwordEditText.getText().toString()); //call to function with the user id,the function check if the user exist at the database
        }
    }

    /**
     check if the user exist in the database by using the clientExist function
     if he exist the main window opened. else, it means that the user need to register first.
     */
    private void checkUser(String id)
    {
        final ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(RentConst.ClientConst.ID, id);

            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected void onPostExecute(Boolean idResult) {
                    super.onPostExecute(idResult);
                    exist = idResult;
                    if(exist)
                    {
                        saveSharedPreferences();
                        Intent MyIntent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(MyIntent);
                    }
                    else Toast.makeText(LoginActivity.this, "User not exist! Please register first", Toast.LENGTH_LONG).show();
                }

                @Override
                protected Boolean doInBackground(Void... params) {

                    return DB_ManagerFactory.getManager().clientExist(contentValues);
                }
            }.execute();
        }
        catch (Exception e) {

        }
    }

}
