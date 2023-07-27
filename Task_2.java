package com.example.temperature_app;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class temperature extends Activity {

    private EditText inputTemperatureEditText;
    private TextView celsiusOutputTextView, fahrenheitOutputTextView, kelvinOutputTextView;
    private Spinner temperatureTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        // Initialize views
        inputTemperatureEditText = findViewById(R.id.inputTemperatureEditText);
        celsiusOutputTextView = findViewById(R.id.celsiusOutputTextView);
        fahrenheitOutputTextView = findViewById(R.id.fahrenheitOutputTextView);
        kelvinOutputTextView = findViewById(R.id.kelvinOutputTextView);
        temperatureTypeSpinner = findViewById(R.id.temperatureTypeSpinner);

        // Set up the temperature type selection dropdown
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.temperature,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temperatureTypeSpinner.setAdapter(adapter);

        // Set up the spinner item selection listener
        temperatureTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertTemperature();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Set up the input temperature EditText change listener
        inputTemperatureEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String inputTemperatureStr = inputTemperatureEditText.getText().toString();
        if (!inputTemperatureStr.isEmpty()) {
            double inputTemperature = Double.parseDouble(inputTemperatureStr);
            String selectedTemperatureType = temperatureTypeSpinner.getSelectedItem().toString();

            if (selectedTemperatureType.equals("Celsius")) {
                double celsius = inputTemperature;
                double fahrenheit = (celsius * 9 / 5) + 32;
                double kelvin = celsius + 273.15;

                celsiusOutputTextView.setText("Celsius: " + celsius);
                fahrenheitOutputTextView.setText("Fahrenheit: " + fahrenheit);
                kelvinOutputTextView.setText("Kelvin: " + kelvin);
            } else if (selectedTemperatureType.equals("Fahrenheit")) {
                double fahrenheit = inputTemperature;
                double celsius = (fahrenheit - 32) * 5 / 9;
                double kelvin = celsius + 273.15;

                celsiusOutputTextView.setText("Celsius: " + celsius);
                fahrenheitOutputTextView.setText("Fahrenheit: " + fahrenheit);
                kelvinOutputTextView.setText("Kelvin: " + kelvin);
            } else if (selectedTemperatureType.equals("Kelvin")) {
                double kelvin = inputTemperature;
                double celsius = kelvin - 273.15;
                double fahrenheit = (celsius * 9 / 5) + 32;

                celsiusOutputTextView.setText("Celsius: " + celsius);
                fahrenheitOutputTextView.setText("Fahrenheit: " + fahrenheit);
                kelvinOutputTextView.setText("Kelvin: " + kelvin);
            }
        } else {
            // If the input temperature is empty, clear the output TextViews
            celsiusOutputTextView.setText("Celsius:");
            fahrenheitOutputTextView.setText("Fahrenheit:");
            kelvinOutputTextView.setText("Kelvin:");
        }
    }
}
