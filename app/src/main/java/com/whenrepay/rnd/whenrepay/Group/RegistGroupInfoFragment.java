package com.whenrepay.rnd.whenrepay.Group;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistGroupInfoFragment extends Fragment {


    public RegistGroupInfoFragment() {
        // Required empty public constructor
    }

    EditText titleView;
    RecyclerView recyclerView;
    MemberListAdapter mAdapter;
    LinearLayoutManager layoutManager;
    GroupData groupData;

    public static final String EXTRA_RESULT = "result";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regist_group_info, container, false);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
        titleView = (EditText) view.findViewById(R.id.edit_group_name);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mAdapter = new MemberListAdapter();
        layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        groupData = new GroupData();
        Button btn = (Button) view.findViewById(R.id.btn_contact);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), ContractActivity.class), ContractActivity.REQUEST_PERSON_LIST);
            }
        });

        btn = (Button) view.findViewById(R.id.btn_direct);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn = (Button) view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupData.groupName = titleView.getText().toString();
                ((AddGroupActivity)getActivity()).changePayment();
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAdapter.clear();
        groupData.personList = ((GroupData) data.getSerializableExtra(EXTRA_RESULT)).personList;
        for (PersonData personData : groupData.personList) {
            mAdapter.add(personData);
        }
    }
}
