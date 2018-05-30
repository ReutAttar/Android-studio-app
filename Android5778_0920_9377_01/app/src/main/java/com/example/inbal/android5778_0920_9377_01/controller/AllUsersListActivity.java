package com.example.inbal.android5778_0920_9377_01.controller;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;


import com.example.inbal.android5778_0920_9377_01.R;
import com.example.inbal.android5778_0920_9377_01.model.backend.DB_ManagerFactory;
import com.example.inbal.android5778_0920_9377_01.model.entities.Client;

import java.util.List;



public class AllUsersListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncTask<Void, Void, ListAdapter>() {
            @Override
            protected ListAdapter doInBackground(Void... params) {
                List<Client> clientList = DB_ManagerFactory.getManager().getAllClients();
                return new ArrayAdapter<Client>(getBaseContext(), R.layout.item_view, clientList);
            }

            @Override
            protected void onPostExecute(ListAdapter adapter) {
                setListAdapter(adapter);
            }
        }.execute();
    }
}
