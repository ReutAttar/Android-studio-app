package com.example.inbal.android5778_0920_9377_01.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.inbal.android5778_0920_9377_01.R;
import com.example.inbal.android5778_0920_9377_01.model.datasource.List_DBManager;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button CheckUserButton;
    private Button AddUserButton;
    private Button AddCarModelButton;
    private Button AddCarButton;
    private Button ShowCarsModelsListButton;
    private Button ShowUserListButton;
    private Button ShowBranchListButton;
    private Button ShowCarListButton;
    private VideoView videoView;

    private void findViews() {
        CheckUserButton = (Button)findViewById( R.id.CheckUserButton );
        AddUserButton = (Button)findViewById( R.id.AddUserButton );
        AddCarModelButton = (Button)findViewById( R.id.AddCarModelButton );
        AddCarButton = (Button)findViewById( R.id.AddCarButton );
        ShowCarsModelsListButton = (Button)findViewById( R.id.ShowCarModelListButton );
        ShowUserListButton = (Button)findViewById( R.id.ShowUserListButton );
        ShowBranchListButton = (Button)findViewById( R.id.ShowBranchListButton );
        ShowCarListButton = (Button)findViewById( R.id.ShowCarListButton );

        CheckUserButton.setOnClickListener( this );
        AddUserButton.setOnClickListener( this );
        AddCarModelButton.setOnClickListener( this );
        AddCarButton.setOnClickListener( this );
        ShowCarsModelsListButton.setOnClickListener( this );
        ShowUserListButton.setOnClickListener( this );
        ShowBranchListButton.setOnClickListener( this );
        ShowCarListButton.setOnClickListener( this );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List_DBManager.initBranches();
        findViews();
    }
    private void CheckUser()
    {
        Intent MyIntent = new Intent(this,CheckUserActivity.class);
        startActivity(MyIntent);
    }
    private void AddUser()
    {
        Intent MyIntent = new Intent(this,AddUserActivity.class);
        startActivity(MyIntent);
    };
    private void AddCarModel()
    {
        Intent MyIntent = new Intent(this,AddCarModelActivity.class);
        startActivity(MyIntent);
    };
    private void AddCar()
    {
        Intent MyIntent = new Intent(this,AddCarActivity.class);
        startActivity(MyIntent);
    };
    private void ShowCarsModelsList()
    {
        Intent MyIntent = new Intent(this,AllCarsModelsActivity.class);
        startActivity(MyIntent);
    };
    private void ShowUserList()
    {
        Intent MyIntent = new Intent(this,AllUsersListActivity.class);
        startActivity(MyIntent);
    };
    private void ShowBranchList()
    {
        Intent MyIntent = new Intent(this,AllBranchesListActivity.class);
        startActivity(MyIntent);
    };
    private void ShowCarList()
    {
        Intent MyIntent = new Intent(this,AllCarsListActivity.class);
        startActivity(MyIntent);
    };

    @Override
    public void onClick(View v) {
        if ( v == CheckUserButton ) {
            CheckUser();
        } else if ( v == AddUserButton ) {
            AddUser();
        } else if ( v == AddCarModelButton ) {
            AddCarModel();
        } else if ( v == AddCarButton ) {
           AddCar();
        } else if ( v == ShowCarsModelsListButton ) {
            ShowCarsModelsList();
        } else if ( v == ShowUserListButton ) {
            ShowUserList();
        } else if ( v == ShowBranchListButton ) {
            ShowBranchList();
        } else if ( v == ShowCarListButton ) {
            ShowCarList();
        }
    }
}
