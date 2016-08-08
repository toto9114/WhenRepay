package com.whenrepay.rnd.whenrepay.Group.MemberManage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.DutchPay.MemberListAdapter;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupMemberManageFragment extends Fragment {


    public GroupMemberManageFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    MemberListAdapter mAdapter;
    LinearLayoutManager layoutManager;

    public static final String EXTRA_GROUP_ID = "id";

    int groupId = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            groupId = getArguments().getInt(EXTRA_GROUP_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_member_manage, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.container);
        layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL,false);
        mAdapter = new MemberListAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    public void setData(){
        mAdapter.clear();
        mAdapter.addAll(DataManager.getInstance().getMemberList(groupId));
    }

    private void addMember(PersonData data){
        DataManager.getInstance().insertMember(groupId,data);
    }
    private void deleteMember(PersonData data){
        DataManager.getInstance().deleteMember(groupId,data);
    }
}
