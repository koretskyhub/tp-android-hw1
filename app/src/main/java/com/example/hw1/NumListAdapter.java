package com.example.hw1;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


class NumListAdapter extends RecyclerView.Adapter<RecyclerFragment.NumberViewHolder> {

    private List<Integer> mData = new ArrayList<Integer>();
    private ItemClickListener mListener;

    public NumListAdapter(Integer listSize, ItemClickListener listener) {
        mListener = listener;

        for (Integer i = 1; i <= listSize; i++) {
            mData.add(i);
        }
    }

    @Override
    public RecyclerFragment.NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.grid_entry, viewGroup, false);

        return new RecyclerFragment.NumberViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(final RecyclerFragment.NumberViewHolder vh, int i) {
        vh.mTextView.setTextColor((i%2 == 0) ? (Color.RED) : (Color.BLUE));
        vh.mTextView.setText(Integer.toString(mData.get(i)));
    }

    public void addNextNum() {
        mData.add(getItemCount() + 1);
        notifyItemInserted(getItemCount());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}