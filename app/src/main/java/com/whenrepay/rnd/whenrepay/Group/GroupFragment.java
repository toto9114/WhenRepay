package com.whenrepay.rnd.whenrepay.Group;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.whenrepay.rnd.whenrepay.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {


    public GroupFragment() {
        // Required empty public constructor
    }

    FloatingActionsMenu menu;
    RecyclerView recyclerView;
    GroupAdapter mAdapter;
    LinearLayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        menu = (FloatingActionsMenu)view.findViewById(R.id.floating_menu);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        mAdapter = new GroupAdapter();
        layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton delBtn = (FloatingActionButton)view.findViewById(R.id.btn_delete);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"delete",Toast.LENGTH_SHORT).show();
                menu.collapse();
            }
        });
        FloatingActionButton addBtn = (FloatingActionButton)view.findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddGroupActivity.class));
                Toast.makeText(getContext(),"add",Toast.LENGTH_SHORT).show();
                menu.collapse();
            }
        });
        return view;
    }

}
