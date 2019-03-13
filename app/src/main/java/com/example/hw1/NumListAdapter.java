package com.example.hw1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


class NumListAdapter extends RecyclerView.Adapter<RecyclerFragment.NumberViewHolder> {

    private List<Integer> mData = new ArrayList<Integer>();
    private Context mContext;

    public NumListAdapter(Integer listSize) {
        for (Integer i = 1; i <= listSize; i++) {
            mData.add(i);
        }
    }

    @Override
    public RecyclerFragment.NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.grid_entry, viewGroup, false);

        this.mContext = viewGroup.getContext();

        return new RecyclerFragment.NumberViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerFragment.NumberViewHolder vh, int i) {
        vh.setContent(mContext, mData.get(i));

        vh.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).showNumber(vh);
            }
        });
    }

    public void addNextNum() {
        Integer newNum = this.getItemCount() + 1;
        mData.add(newNum);
        notifyItemInserted(newNum - 1);

        Toast.makeText(mContext,"Number " + this.getItemCount() + " added",
                      Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}