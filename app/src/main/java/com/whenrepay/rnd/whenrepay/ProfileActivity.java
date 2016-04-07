package com.whenrepay.rnd.whenrepay;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    ImageView profileView;
    EditText nameView, phoneView;
    Spinner spinner;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileView = (ImageView)findViewById(R.id.image_profile);
        nameView = (EditText)findViewById(R.id.edit_name);
        phoneView = (EditText)findViewById(R.id.edit_phone);
        spinner = (Spinner)findViewById(R.id.spinner);
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ProfileActivity.this,""+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initData();

    }

    private void initData() {
        String[] arrays = getResources().getStringArray(R.array.items);
        for (String s : arrays) {
            mAdapter.add(s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.regist_profile, menu);
        return true;
    }

    Handler mHandler = new Handler();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.regist_profile) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("주소연결")
                    .setMessage(R.string.connect_contract_message)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            final ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
                            progressDialog.setTitle("주소연결");
                            progressDialog.setMessage(getResources().getString(R.string.loading_contract_message));
                            progressDialog.show();
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                                    finish();
                                }
                            },1500);
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                            finish();
                        }
                    })
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
