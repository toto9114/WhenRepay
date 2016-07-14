package com.whenrepay.rnd.whenrepay.Contact;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.Manager.DBContants;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleRecentFragment extends Fragment {


    public SingleRecentFragment() {
        // Required empty public constructor
    }

    ListView listView;
    SingleContactAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sing_recent, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new SingleContactAdapter();
        listView.setAdapter(mAdapter);
        initData();
        return view;
    }

    List<String> list = new ArrayList<>();

    private void initData() {
        if (DataManager.getInstance().getContractList(DBContants.SORT_TYPE_DATE).size() != 0) {
            for (TransactionData data : DataManager.getInstance().getContractList(DBContants.SORT_TYPE_DATE)) {
                AccountData accountData = (AccountData) data;
                list.add(accountData.name);
            }
        }
        if (DataManager.getInstance().getContractThingsList().size() != 0) {
            for (TransactionData data : DataManager.getInstance().getContractThingsList()) {
                ThingsData thingsData = (ThingsData) data;
                list.add(thingsData.borrowerName);
            }
        }
        if (DataManager.getInstance().getDutchPayList().size() != 0) {
            for (TransactionData data : DataManager.getInstance().getDutchPayList()) {

            }
        }
        HashSet hs = new HashSet(list);

        Iterator it = hs.iterator();
        while (it.hasNext()) {
            PersonData data = new PersonData();
            data.setName(it.next().toString());
            mAdapter.add(data);
        }
    }
}
