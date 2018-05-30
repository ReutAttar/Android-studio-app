package com.example.inbal.android5778_0920_9377_01.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inbal.android5778_0920_9377_01.R;
import com.example.inbal.android5778_0920_9377_01.model.backend.DB_ManagerFactory;
import com.example.inbal.android5778_0920_9377_01.model.backend.RentConst;
import com.example.inbal.android5778_0920_9377_01.model.entities.Branch;
import com.example.inbal.android5778_0920_9377_01.model.entities.CarModel;

import java.util.List;

import static com.example.inbal.android5778_0920_9377_01.model.datasource.List_DBManager.branches;
import static com.example.inbal.android5778_0920_9377_01.model.datasource.List_DBManager.carsModels;

public class AddCarActivity extends Activity implements View.OnClickListener {

    private Spinner BranchParkingNumberSpinner;
    private Spinner CarModelsSpinner;
    private EditText KilometersEditText;
    private EditText CarNumberEditText;
    private EditText CarCostEditText;
    private Button AddCarButton;
    List<CarModel> carModelList;
    List<Branch> branchList;

    private void findViews() {
        BranchParkingNumberSpinner = (Spinner) findViewById(R.id.BranchParkingNumberSpinner);
        CarModelsSpinner = (Spinner) findViewById(R.id.CarModelsSpinner);
        KilometersEditText = (EditText) findViewById(R.id.KilometersEditText);
        CarNumberEditText = (EditText) findViewById(R.id.CarNumberEditText);
        CarCostEditText = (EditText) findViewById(R.id.CarCostEditText);
        AddCarButton = (Button) findViewById(R.id.AddCarButton);

        AddCarButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                initSpinner();
            }

            @Override
            protected Void doInBackground(Void... params) {
                carModelList = DB_ManagerFactory.getManager().getAllModels();
                branchList = DB_ManagerFactory.getManager().getAllBranchs();
                return null;
            }
        }.execute();

        findViews();
    }

    private void initSpinner() {
        BranchParkingNumberSpinner.setAdapter(new ArrayAdapter<Branch>(this, android.R.layout.simple_list_item_1, branchList));
        CarModelsSpinner.setAdapter(new ArrayAdapter<CarModel>(this, android.R.layout.simple_list_item_1, carModelList));
    }

    @Override
    public void onClick(View v) {
        if (v == AddCarButton) {
            AddCar();
            KilometersEditText.getText().clear();
            CarNumberEditText.getText().clear();
            CarCostEditText.getText().clear();
        }
    }

    public void AddCar() {
        final ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(RentConst.CarConst.KILOMETERS, this.KilometersEditText.getText().toString());
            contentValues.put(RentConst.CarConst.CAR_NUMBER, this.CarNumberEditText.getText().toString());
            contentValues.put(RentConst.CarConst.STATUS, "available");
            contentValues.put(RentConst.CarConst.COST, this.CarCostEditText.getText().toString());
            String branchparkingnumber = ((Branch) BranchParkingNumberSpinner.getSelectedItem()).getBranchNumber();
            contentValues.put(RentConst.CarConst.BRANCH_PARKING_NUMBER, branchparkingnumber);

            String carModel = ((CarModel) CarModelsSpinner.getSelectedItem()).getCodeModel();
            contentValues.put(RentConst.CarConst.CAR_MODEL, carModel);


            new AsyncTask<Void, Void, String>() {
                @Override
                protected void onPostExecute(String idResult) {
                    super.onPostExecute(idResult);
                    if (idResult != null)
                        Toast.makeText(getBaseContext(), "insert car: " + idResult, Toast.LENGTH_SHORT).show();
                }

                @Override
                protected String doInBackground(Void... params) {
                    return DB_ManagerFactory.getManager().addCar(contentValues);
                }
            }.execute();

        } catch (Exception e) {

        }
    }

}
