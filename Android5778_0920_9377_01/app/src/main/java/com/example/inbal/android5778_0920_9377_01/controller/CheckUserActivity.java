package com.example.inbal.android5778_0920_9377_01.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inbal.android5778_0920_9377_01.R;
import com.example.inbal.android5778_0920_9377_01.model.backend.DB_ManagerFactory;
import com.example.inbal.android5778_0920_9377_01.model.backend.RentConst;

public class CheckUserActivity extends Activity implements View.OnClickListener  {

    private EditText IdEditText;
    private Button EntarButton;

    private void findViews() {
        IdEditText = (EditText)findViewById( R.id.IdEditText );
        EntarButton = (Button)findViewById( R.id.EntarButton );

        EntarButton.setOnClickListener( this );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user);
        findViews();
    }

    @Override
    public void onClick(View v) {
        if ( v == EntarButton ) {
            checkUser();
            IdEditText.getText().clear();
        }
    }

    private void checkUser()
    {
        final ContentValues contentValues = new ContentValues();
        try
        {
            contentValues.put(RentConst.ClientConst.ID,this.IdEditText.getText().toString());

            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected void onPostExecute(Boolean idResult) {
                    super.onPostExecute(idResult);
                    if (idResult) {
                        Toast.makeText(getBaseContext(), "The user exist ", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(getBaseContext(), "User not exist ", Toast.LENGTH_SHORT).show();
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    return DB_ManagerFactory.getManager().clientExist(contentValues);
                }
            }.execute();
        }

        catch (Exception e)
        {

        }
    }
}
