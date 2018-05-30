package com.example.inbal.android5778_0920_9377_01.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inbal.android5778_0920_9377_01.R;
import com.example.inbal.android5778_0920_9377_01.model.backend.DB_ManagerFactory;
import com.example.inbal.android5778_0920_9377_01.model.backend.RentConst;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Color;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Fuel;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.GearBox;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Seats;

public class AddCarModelActivity extends Activity implements View.OnClickListener {
    private EditText CodeModelEditText;
    private EditText CompanyNameEditText;
    private EditText ModelNameEditText;
    private EditText EngineCapacityEditText;
    private Spinner GearBoxSpinner;
    private Spinner SeatsSpinner;
    private Spinner ColorSpinner;
    private Spinner FuelTypeSpinner;
    private Button AddCarModelButton;

    private void findViews() {
        CodeModelEditText = (EditText)findViewById( R.id.CodeModelEditText );
        CompanyNameEditText = (EditText)findViewById( R.id.CompanyNameEditText );
        ModelNameEditText = (EditText)findViewById( R.id.ModelNameEditText );
        EngineCapacityEditText = (EditText)findViewById( R.id.EngineCapacityEditText );
        GearBoxSpinner = (Spinner)findViewById( R.id.GearBoxSpinner );
        SeatsSpinner = (Spinner) findViewById( R.id.SeatsSpinner );
        ColorSpinner =(Spinner) findViewById(R.id.ColorSpinner);
        FuelTypeSpinner=(Spinner)findViewById(R.id.FuelTypeSpinner);
        AddCarModelButton = (Button)findViewById( R.id.AddCarModelButton );

        GearBoxSpinner.setAdapter(new ArrayAdapter<GearBox>(this,android.R.layout.simple_expandable_list_item_1,GearBox.values()));
        SeatsSpinner.setAdapter(new ArrayAdapter<Seats>(this,android.R.layout.simple_expandable_list_item_1,Seats.values()));
        ColorSpinner.setAdapter(new ArrayAdapter<Color>(this,android.R.layout.simple_expandable_list_item_1, Color.values()));
        FuelTypeSpinner.setAdapter(new ArrayAdapter<Fuel>(this,android.R.layout.simple_expandable_list_item_1, Fuel.values()));

        AddCarModelButton.setOnClickListener( this );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_model);
        findViews();
    }

    @Override
    public void onClick(View v) {
        if ( v == AddCarModelButton ) {
            AddCarModel();
            CodeModelEditText.getText().clear();
            CompanyNameEditText.getText().clear();
            ModelNameEditText.getText().clear();
            EngineCapacityEditText.getText().clear();
        }
    }
    public void AddCarModel()
    {
        final ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(RentConst.CarModelConst.CODE_MODEL, this.CodeModelEditText.getText().toString());
            contentValues.put(RentConst.CarModelConst.COMPANY_NAME, this.CompanyNameEditText.getText().toString());
            contentValues.put(RentConst.CarModelConst.MODEL_NAME, this.ModelNameEditText.getText().toString());
            contentValues.put(RentConst.CarModelConst.ENGINE_CAPACITY, this.EngineCapacityEditText.getText().toString());

            String gear = ((GearBox) GearBoxSpinner.getSelectedItem()).name();
            contentValues.put(RentConst.CarModelConst.GEARBOX, gear);

            String seats=((Seats)SeatsSpinner.getSelectedItem()).name();
            contentValues.put(RentConst.CarModelConst.SEATS_NUMBER, seats);

            String color=((Color)ColorSpinner.getSelectedItem()).name();
            contentValues.put(RentConst.CarModelConst.COLOR,color);

            String fuel=((Fuel)FuelTypeSpinner.getSelectedItem()).name();
            contentValues.put(RentConst.CarModelConst.FUEL_TYPE,fuel);

            new AsyncTask<Void, Void, String>() {
                @Override
                protected void onPostExecute(String idResult) {
                    super.onPostExecute(idResult);
                    if (idResult != null)
                        Toast.makeText(getBaseContext(), "insert code model: " + idResult, Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(Void... params)  {
                    return DB_ManagerFactory.getManager().addCarModel(contentValues);
                }
            }.execute();

        }
        catch (Exception e)
        {

        }
    }
}
