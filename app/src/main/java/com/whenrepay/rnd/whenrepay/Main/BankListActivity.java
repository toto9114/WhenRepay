package com.whenrepay.rnd.whenrepay.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.whenrepay.rnd.whenrepay.R;

public class BankListActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);

        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bank));
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bank = mAdapter.getItem(position);
                Intent i = new Intent();
                i.putExtra(SettingActivity.RESULT_INFO,bank);
                setResult(RESULT_OK,i );
                finish();
            }
        });
    }
}
