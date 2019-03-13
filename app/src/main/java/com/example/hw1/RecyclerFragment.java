package com.example.hw1;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class RecyclerFragment extends Fragment {
    private Integer numsInList = 100;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    public void setNumsInList(Integer num) {
        if (num > 0) {
            numsInList = num;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView mRecyclerView = getView().findViewById(R.id.recycler_view);

        Integer orientation = getResources().getConfiguration().orientation;
        Integer spanNum = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? 4 : 3;

        final GridLayoutManager mLayoutManager = new GridLayoutManager(getView().getContext(), spanNum);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final NumListAdapter mAdapter = new NumListAdapter(numsInList);
        mRecyclerView.setAdapter(mAdapter);

        Button addButton = (Button) getView().findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).incNumsInList();
                mAdapter.addNextNum();
            }
        });
    }

    static class NumberViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public NumberViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.grid_entry_text);
        }

        public void setContent(Context ctx, Integer number){
            this.mTextView.setText(number.toString());

            Integer color = ctx.getResources().getColor((number%2 == 0) ? (R.color.red) : (R.color.blue));
            this.mTextView.setTextColor(color);
        }

        public String getNumberString(){
            return (String) this.mTextView.getText();
        }

        public Integer getColor(){
            return this.mTextView.getCurrentTextColor();
        }

    }
}
