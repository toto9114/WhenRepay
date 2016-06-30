package com.whenrepay.rnd.whenrepay.Intro;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankListDialog extends DialogFragment {


    public BankListDialog() {
        // Required empty public constructor
    }

    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    ListView listView;
    ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_list, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bank));
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bank = mAdapter.getItem(position);
                if(itemClickListener!=null){
                    itemClickListener.OnItemClick(bank);
                }
                dismiss();
            }
        });
        return view;
    }

}
