package com.whenrepay.rnd.whenrepay.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.R;

public class SettingActivity extends AppCompatActivity {

    EditText nameView, accountView;
    Spinner spinner;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        nameView = (EditText)findViewById(R.id.edit_name);
        accountView = (EditText)findViewById(R.id.edit_account);

        spinner = (Spinner)findViewById(R.id.spinner);
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.bank));
        spinner.setAdapter(mAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SettingActivity.this,mAdapter.getItem(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
