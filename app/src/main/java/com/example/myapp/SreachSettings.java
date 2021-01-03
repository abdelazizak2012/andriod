package com.example.myapp;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SreachSettings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button backButton;
    private Button resetButton;
    private EditText prise;
    private SeekBar seekBarRoomsNumber;
    private TextView textViewRoomsNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sreach_settings);
        setTitle("Suche Einstellung");
        setTextViewRoomsNumber();
        setPriceTextEditer();
        setSeekBarRoomsNumber();
        setBackButton();
        setSpinner();
        setResetButton();
    }

    private void setTextViewRoomsNumber() {
        textViewRoomsNumber = (TextView) findViewById(R.id.sreachSettingsRoomsNumber);
        if (Buffer.getRoomsNumber() == 0){
            textViewRoomsNumber.setText("Egal");
        } else {
            textViewRoomsNumber.setText(String.valueOf(Buffer.getRoomsNumber())+" Max");
        }
    }

    private void setSeekBarRoomsNumber() {
        seekBarRoomsNumber = (SeekBar) findViewById(R.id.sreachSettingsSeekerbar);
        seekBarRoomsNumber.setProgress(Buffer.getRoomsNumber());
        seekBarRoomsNumber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Buffer.setRoomsNumber(progress);
                if (progress == 0){
                    textViewRoomsNumber.setText("Egal");
                } else {
                    textViewRoomsNumber.setText(String.valueOf(progress)+" Max");
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setPriceTextEditer() {
        prise = (EditText) findViewById(R.id.sreachSettingsPreis);
        if(Buffer.getPrice() != 0) {
            prise.setText(String.valueOf(Buffer.getPrice()));
        }

    }

    private void setResetButton() {
        resetButton = (Button) findViewById(R.id.sreachSettingsRestButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setSelection(0);
                seekBarRoomsNumber.setProgress(0);
                prise.setText("");
                textViewRoomsNumber.setText("Egal");
                Buffer.setOfferArt(0);
                Buffer.setRoomsNumber(0);
                Buffer.setPrice(0);
            }
        });
    }


    private void setBackButton() {
        backButton = (Button) findViewById(R.id.backSreachSettings);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Seeker.class);
                if(!prise.getText().toString().equals("")) {
                    if(Double.parseDouble(prise.getText().toString()) != 0 ){
                        Buffer.setPrice(Double.parseDouble(prise.getText().toString()));
                    }
                } else {
                    Buffer.setPrice(0);
                }
                startActivity(intent);
            }
        });
    }

    private void setSpinner() {
        spinner = (Spinner) findViewById(R.id.rentBuyBoth);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.offer_art, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(Buffer.getOfferArt());
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0) { // Rent & Sell
            Buffer.setOfferArt(0);
        } else if (position == 1) { // Rent
            Buffer.setOfferArt(1);
        } else { // Sell
            Buffer.setOfferArt(2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}