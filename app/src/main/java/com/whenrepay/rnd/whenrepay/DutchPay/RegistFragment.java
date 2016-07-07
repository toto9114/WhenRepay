package com.whenrepay.rnd.whenrepay.DutchPay;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.whenrepay.rnd.whenrepay.Group.ContractActivity;
import com.whenrepay.rnd.whenrepay.Group.MemberListAdapter;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistFragment extends Fragment {


    public RegistFragment() {
        // Required empty public constructor
    }

    public static final String EXTRA_RESULT = "result";

    EditText editEvent;
    FamiliarRecyclerView recyclerView;
    //    PersonListAdapter mAdapter;
    MemberListAdapter mAdapter;
    LinearLayoutManager layoutManager;
    List<PersonData> personDataList = new ArrayList<>();

    DutchPayData dutchPayData;
    public static final int REQUEST_PERSON_LIST = 200;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regist, container, false);

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

        dutchPayData = new DutchPayData();
        editEvent = (EditText) view.findViewById(R.id.edit_event);
        recyclerView = (FamiliarRecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        mAdapter = new PersonListAdapter();
        mAdapter = new MemberListAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new FadeInDownAnimator());

        Button btn = (Button) view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dutchPayData.title = editEvent.getText().toString();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
                dutchPayData.date = sdf.format(date);  //더치페이 날짜
                ((DutchPayActivity) getActivity()).changeEditMoney(dutchPayData);
            }
        });

        btn = (Button) view.findViewById(R.id.btn_contact);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ContractActivity.class);
                i.putExtra(ContractActivity.EXTRA_TYPE,ContractActivity.TYPE_DUTCH);
                startActivityForResult(i, REQUEST_PERSON_LIST);
            }
        });

        btn = (Button) view.findViewById(R.id.btn_direct);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                int id = v.getId();
//                if (id == R.id.btn_contact) {
//                    Intent intent = new Intent(Intent.ACTION_PICK);
//                    intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
//                    startActivityForResult(intent, REQUEST_CONTACT);
//                } else {
//                    Toast.makeText(getContext(), "direct", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        mAdapter.setOnDelButtonClickListener(new OnDelButtonClickListener() {
//            @Override
//            public void onDelButtonClick(View view, int position) {
//                Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
//                mAdapter.deleteData(position);
//            }
//        });
//        mAdapter.add(null);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERSON_LIST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mAdapter.clear();
//            groupData.personList = ((GroupData) data.getSerializableExtra(EXTRA_RESULT)).personList;
//                groupData.setPersonList(((GroupData) data.getSerializableExtra(EXTRA_RESULT)).getPersonList());
//                for (PersonData personData : groupData.getPersonList()) {
//                    mAdapter.add(personData);
//                }
                dutchPayData.personList = ((DutchPayData)data.getSerializableExtra(EXTRA_RESULT)).personList;
                for(PersonData personData : dutchPayData.personList){
                    mAdapter.add(personData);
                }
            }
        }
    }
}
