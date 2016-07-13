package com.whenrepay.rnd.whenrepay.Contact;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.DutchPay.RegistFragment;
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
public class RecentFragment extends Fragment {


    public RecentFragment() {
        // Required empty public constructor
    }

    ListView listView;
    RecentAdapter mAdapter;

    public static final String EXTRA_TYPE = "type";
    int type = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(EXTRA_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mAdapter = new RecentAdapter();
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.notifyDataSetChanged();
            }
        });

        Button btn = (Button)view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == ContractActivity.TYPE_GROUP) {
//                    Intent intent = new Intent();
//                    GroupData data = new GroupData();
//                    List<String> list = new ArrayList<String>();
//                    for (int i = 0; i < mAdapter.getCount(); i++) {
//                        if (listView.isItemChecked(i)) {
//                            list.add(mAdapter.getItem(i));
//                        }
//                    }
//                    data.setPersonList(list);
//                    intent.putExtra(RegistGroupInfoFragment.EXTRA_RESULT, data);
//                    getActivity().setResult(Activity.RESULT_OK, intent);
                } else {
                    Intent intent = new Intent();
                    DutchPayData data = new DutchPayData();
                    List<PersonData> list = new ArrayList<PersonData>();
                    for (int i = 0; i < mAdapter.getCount(); i++) {
                        if (listView.isItemChecked(i)) {
                            PersonData personData = new PersonData();
                            personData.setName(mAdapter.getItem(i));
                            list.add(personData);
                        }
                    }
                    data.personList = list;
                    intent.putExtra(RegistFragment.EXTRA_RESULT, data);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                }
                getActivity().finish();
            }
        });
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
            mAdapter.add(it.next().toString());
        }
    }
}
