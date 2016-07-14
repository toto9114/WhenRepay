package com.whenrepay.rnd.whenrepay.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;
import com.whenrepay.rnd.whenrepay.Transactions.TransactionViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-14.
 */
public class SectionAdapter  extends SectionedRecyclerViewAdapter{
    private static final int TYPE_OVERDUE_HEADER = 0;
    private static final int TYPE_OVERDUE = 1;
    private static final int TYPE_CONTRACT_HEADER =2;
    private static final int TYPE_CONTRACT = 3;

    List<TransactionData> overDue = new ArrayList<>();
    List<TransactionData> contract = new ArrayList<>();

    public void addOverDue(TransactionData data){
        overDue.add(data);
    }

    public void addContract(TransactionData data){
        contract.add(data);
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @Override
    public int getSectionCount() {
        return 2;
    }

    @Override
    public int getItemCount(int section) {
        return overDue.size()+contract.size();
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {
        ((OverDueHeaderView)holder).setOverDueView(overDue.size());
        ((ContractHeaderView)holder).setContractView(contract.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int relativePosition, int absolutePosition) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =null;
        switch (viewType){
            case TYPE_OVERDUE_HEADER:
                view =inflater.inflate(R.layout.view_header_overdue,parent,false);
                return new OverDueHeaderView(view);
            case TYPE_OVERDUE:
                view = inflater.inflate(R.layout.view_contract,parent,false);
                return new TransactionViewHolder(view);
            case TYPE_CONTRACT_HEADER:
                view = inflater.inflate(R.layout.view_header_contract,parent,false);
                return new ContractHeaderView(view);
            case TYPE_CONTRACT:
                view = inflater.inflate(R.layout.view_contract,parent,false);
                return new TransactionViewHolder(view);
        }
        return null;
    }
}
