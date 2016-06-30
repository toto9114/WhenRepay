package com.whenrepay.rnd.whenrepay.DutchPay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wefika.flowlayout.FlowLayout;
import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditEventFragment extends Fragment {


    public EditEventFragment() {
        // Required empty public constructor
    }

    public static final String EXTRA_DUTCH_DATA = "dutch";
    DutchPayData dutchPayData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dutchPayData = (DutchPayData) getArguments().getSerializable(EXTRA_DUTCH_DATA);
        }
    }

    FlowLayout mFlowLayout;
    EventView eventView;
    int index = 1;
    List<EventData> eventList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_event, container, false);
        mFlowLayout = (FlowLayout) view.findViewById(R.id.flowlayout);

        Button btn = (Button) view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventList.add(eventView.getData());
                for (EventData data : eventList) {
                    dutchPayData.totalPrice += data.money;
                }
                dutchPayData.eventList = eventList;
                ((DutchPayActivity) getActivity()).changeSend(dutchPayData);
            }
        });

        btn = (Button) view.findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventList.add(eventView.getData());
                eventView = new EventView(getContext(), dutchPayData.personList);
                mFlowLayout.addView(eventView);
            }
        });
        init();
        return view;
    }

    private void init() {
        eventView = new EventView(getContext(), dutchPayData.personList);
        mFlowLayout.addView(eventView);
    }


}