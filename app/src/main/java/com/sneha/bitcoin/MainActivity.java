package com.sneha.bitcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  MyAsynTask.MyStr, AdapterView.OnItemSelectedListener {


    Spinner spinnerDialog;
    TextView price;
    String url ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> items=new ArrayList<>();
        spinnerDialog = (Spinner) findViewById(R.id.spinner);
        price = (TextView)findViewById(R.id.price);
        spinnerDialog.setOnItemSelectedListener(this);
        //Listener(this);

        items.add("AFN"); items.add("ALL"); items.add("AMD"); items.add("AOA"); items.add("ARS"); items.add("AUD");
        items.add("AWD"); items.add("AZN"); items.add("BBT"); items.add("BAM"); items.add("BDT"); items.add("BIF");
        items.add("BGN"); items.add("BHD"); items.add("BMD"); items.add("BND"); items.add("BOB"); items.add("BRL");
        items.add("BSD"); items.add("BTN"); items.add("BYN"); items.add("BZD"); items.add("CDF");
        items.add("CHF"); items.add("CLP"); items.add("CNY"); items.add("COP"); items.add("CVE"); items.add("CZK");
        items.add("DOP"); items.add("DJF"); items.add("DKK"); items.add("DZD"); items.add("EGP"); items.add("ERN");
        items.add("ETB"); items.add("EUR"); items.add("FJD"); items.add("FKP"); items.add("GEL"); items.add("GGP");
        items.add("GHS"); items.add("GIP"); items.add("GMD"); items.add("GNF"); items.add("GTQ"); items.add("GYD");
        items.add("HTG");items.add("HNL");items.add("HKD");items.add("HUF");items.add("IDR");items.add("ILS");
        items.add("IMP");items.add("INR");items.add("IQD");items.add("IRR");items.add("ISK");items.add("JEP");items.add("JMD");
        items.add("JOD");items.add("JPY");items.add("KES");items.add("KGS");items.add("KHR");items.add("KMF");items.add("KWD");
        items.add("KZT");items.add("LAK");items.add("LBP");items.add("LRD");items.add("LSL");items.add("LYD");items.add("NZD");
        items.add("SHP");items.add("USD");items.add("XAF");items.add("XCD");items.add("XDR");items.add("XPF");items.add("XOF");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDialog.setAdapter(adapter);
        Intent intent = getIntent();
        url = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        String final_url = url + item ;

        MyAsynTask myAsynTask = new MyAsynTask(this);
        myAsynTask.execute(final_url);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onDownload(ArrayList<String> item) {
        price.setText(item.get(0).toString());
    }
}

