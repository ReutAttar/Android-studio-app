package com.example.inbal.android5778_0920_9377_01.controller;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.inbal.android5778_0920_9377_01.R;
import com.example.inbal.android5778_0920_9377_01.model.backend.DB_ManagerFactory;
import com.example.inbal.android5778_0920_9377_01.model.entities.Branch;

import java.util.List;

public class AllBranchesListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncTask<Void, Void, ListAdapter>() {
            @Override
            protected ListAdapter doInBackground(Void... params) {
                List<Branch> BrancheList = DB_ManagerFactory.getManager().getAllBranchs();
                return new ArrayAdapter<Branch>(getBaseContext(), R.layout.item_view, BrancheList);
            }

            @Override
            protected void onPostExecute(ListAdapter adapter) {
                setListAdapter(adapter);
            }
        }.execute();
    }
}
