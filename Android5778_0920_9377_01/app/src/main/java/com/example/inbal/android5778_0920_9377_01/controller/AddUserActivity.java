package com.example.inbal.android5778_0920_9377_01.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inbal.android5778_0920_9377_01.R;
import com.example.inbal.android5778_0920_9377_01.model.backend.DB_ManagerFactory;
import com.example.inbal.android5778_0920_9377_01.model.backend.RentConst;

public class AddUserActivity extends Activity implements View.OnClickListener {
    private EditText IdEditText;
    private EditText FirstNameEditText;
    private EditText LastNameEditText;
    private EditText PhoneEditText;
    private EditText EmailEditText;
    private  EditText CreditCardNumberText;
    private Button AddUserButton;


    private void findViews() {
        IdEditText = (EditText)findViewById( R.id.IdEditText );
        FirstNameEditText = (EditText)findViewById( R.id.FirstNameEditText );
        LastNameEditText = (EditText)findViewById( R.id.LastNameEditText );
        PhoneEditText = (EditText)findViewById( R.id.PhoneEditText );
        EmailEditText = (EditText)findViewById( R.id.EmailEditText );
        CreditCardNumberText=(EditText)findViewById( R.id.CreditCardNumberText);
        AddUserButton = (Button)findViewById( R.id.AddUserButton );

        AddUserButton.setOnClickListener( this );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        findViews();
    }

    @Override
    public void onClick(View v) {
        if ( v == AddUserButton )
        {
            AddUser();
            IdEditText.getText().clear();
            FirstNameEditText.getText().clear();
            LastNameEditText.getText().clear();
            PhoneEditText.getText().clear();
            EmailEditText.getText().clear();
            CreditCardNumberText.getText().clear();
        }
    }
    private void AddUser()
    {
        final ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(RentConst.ClientConst.ID,this.IdEditText.getText().toString());
            contentValues.put(RentConst.ClientConst.FIRST_NAME, this.FirstNameEditText.getText().toString());
            contentValues.put(RentConst.ClientConst.LAST_NAME, this.LastNameEditText.getText().toString());
            contentValues.put(RentConst.ClientConst.PHONE_NUMBER, this.PhoneEditText.getText().toString());
            contentValues.put(RentConst.ClientConst.EMAIL, this.EmailEditText.getText().toString());
            contentValues.put(RentConst.ClientConst.CREDIT_CARD_NUMBER, this.CreditCardNumberText.getText().toString());

            new AsyncTask<Void, Void, String>() {
                @Override
                protected void onPostExecute(String idResult) {
                    super.onPostExecute(idResult);
                    if (idResult != null)
                        Toast.makeText(getBaseContext(), "insert id: " + idResult, Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(Void... params) {
                    return DB_ManagerFactory.getManager().addClient(contentValues);
                }
            }.execute();

        } catch (Exception e) {
        }
    }
}

