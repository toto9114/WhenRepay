package com.whenrepay.rnd.whenrepay.DutchPay;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.BorrowMoney.OnDelButtonClickListener;
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

    private static final int REQUEST_CONTACT = 200;

    EditText editEvent;
    FamiliarRecyclerView recyclerView;
    PersonListAdapter mAdapter;
    LinearLayoutManager layoutManager;
    List<PersonData> personDataList = new ArrayList<>();

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

        editEvent = (EditText) view.findViewById(R.id.edit_event);
        recyclerView = (FamiliarRecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new PersonListAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new FadeInDownAnimator());

        Button btn = (Button) view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mAdapter.getItemCount() - 1; i++) {
                    personDataList.add(mAdapter.getItem(i));
                }
                DutchPayData data = new DutchPayData();
                data.personList = personDataList; //참석 인원 리스트
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
                data.date = sdf.format(date);  //더치페이 날짜
                ((DutchPayActivity) getActivity()).changeEditMoney(data);
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                int id = v.getId();
                if (id == R.id.btn_contact) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                    startActivityForResult(intent, REQUEST_CONTACT);
                } else {
                    Toast.makeText(getContext(), "direct", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAdapter.setOnDelButtonClickListener(new OnDelButtonClickListener() {
            @Override
            public void onDelButtonClick(View view, int position) {
                Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
                mAdapter.deleteData(position);
            }
        });
        mAdapter.add(null);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONTACT && resultCode != Activity.RESULT_CANCELED) {
            Cursor cursor = getActivity().getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();
            String name = cursor.getString(0);
            String phone = cursor.getString(1);
            PersonData personData = new PersonData();
            personData.name = name;
            personData.phone = phone;
            mAdapter.setData(personData);
            mAdapter.add(null);
        }
    }
}
