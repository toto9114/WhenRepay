package com.whenrepay.rnd.whenrepay;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveFragment extends Fragment {


    public ReceiveFragment() {
        // Required empty public constructor
    }

    ListView listView;
    SimpleCursorAdapter mCursorAdapter;
    int total;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receive, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        String[] from = {DBContants.AddressBook.COLUMN_NAME, DBContants.AddressBook.COLUMN_MONEY};
        int[] to = {R.id.text_name, R.id.text_money};

        mCursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.view_recieve, null, from, to, 0);
        listView.setAdapter(mCursorAdapter);

        Button btn = (Button)view.findViewById(R.id.btn_clear);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               for(int i = 0 ; i<mCursorAdapter.getCount() ; i ++) {
                   DataManager.getInstance().deleteAddress((AddressData) mCursorAdapter.getItem(i));
               }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    String mKeyword = null;

    public void setData() {
        Cursor c = DataManager.getInstance().getAddressCursor(mKeyword);
        mCursorAdapter.changeCursor(c);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCursorAdapter.changeCursor(null);
    }
}
