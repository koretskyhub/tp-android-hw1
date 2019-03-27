package com.example.hw1;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RecyclerFragment extends Fragment {
    public static final String TAG = "RecyclerFragment";

    public interface IncNumListener{
        void incNumsInList();
    }

    private Integer numsInList;
    private ItemClickListener showListener;
    private IncNumListener incListener;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        showListener = (ItemClickListener) activity;
        incListener = (IncNumListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            numsInList = bundle.getInt("NUM_IN_LIST");
        }

        RecyclerView mRecyclerView = getView().findViewById(R.id.recycler_view);

        Integer orientation = getResources().getConfiguration().orientation;
        Integer spanNum = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? 4 : 3;

        mRecyclerView.setLayoutManager(new GridLayoutManager(getView().getContext(), spanNum));

        final NumListAdapter mAdapter = new NumListAdapter(numsInList, showListener);
        mRecyclerView.setAdapter(mAdapter);

        getView().findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incListener.incNumsInList();
                mAdapter.addNextNum();
            }
        });
    }

    static class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTextView;
        private ItemClickListener mListener;

        public NumberViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);

            mTextView = itemView.findViewById(R.id.grid_entry_text);
        }

        @Override
        public void onClick(View v) {
            mListener.showNumber((String) mTextView.getText(), mTextView.getCurrentTextColor());
        }
    }
}
