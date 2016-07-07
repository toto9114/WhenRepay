package com.whenrepay.rnd.whenrepay.Group;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {


    public GroupFragment() {
        // Required empty public constructor
    }

    FloatingActionsMenu groupMenu;
    FamiliarRecyclerView recyclerView;
    GroupAdapter mAdapter;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        setHasOptionsMenu(true);

        groupMenu = (FloatingActionsMenu) view.findViewById(R.id.floating_menu);
        recyclerView = (FamiliarRecyclerView) view.findViewById(R.id.recycler);
        mAdapter = new GroupAdapter();
        layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new FadeInDownAnimator());

        FloatingActionButton delBtn = (FloatingActionButton) view.findViewById(R.id.btn_delete);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setCheckBoxVisible(true);
//                DataManager.getInstance().deleteGroup(mAdapter.getItem(0));

                initData();
                groupMenu.collapse();
            }
        });
        FloatingActionButton addBtn = (FloatingActionButton) view.findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddGroupActivity.class));
                groupMenu.collapse();
            }
        });

        Button btn = (Button) view.findViewById(R.id.btn_delete_group);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < checkedList.length; i++) {
                    if (checkedList[i]) {
                        DataManager.getInstance().deleteGroup(mAdapter.getItem(i));
                    }
                }
                initData();
                mAdapter.setCheckBoxVisible(false);
            }
        });
        mAdapter.setOnItemCheckedListener(new OnItemCheckedListener() {
            @Override
            public void OnItemChecked(boolean isChecked, int position) {
                checkedList[position] = isChecked;
            }
        });

        recyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                Intent i = new Intent(getContext(), GroupManageActivity.class);
                i.putExtra(GroupManageActivity.EXTRA_GROUP_ID, mAdapter.getItem(position)._id);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    boolean[] checkedList;

    private void initData() {
        mAdapter.clear();
        if (DataManager.getInstance().getGroupList().size() > 0) {
            mAdapter.addAll(DataManager.getInstance().getGroupList());
            checkedList = new boolean[DataManager.getInstance().getGroupList().size()];
            if (DataManager.getInstance().getMemberList(1).size() > 0) {
                List<PersonData> list = DataManager.getInstance().getMemberList(1);
                for (int i = 0; i < list.size(); i++) {
                    Log.i("member", list.get(i).getName());
                }
            }
        }
    }

}
