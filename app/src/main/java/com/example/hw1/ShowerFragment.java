package com.example.hw1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ShowerFragment extends Fragment {
    private RecyclerFragment.NumberViewHolder mVh;

    public ShowerFragment() {
        // Required empty public constructor
    }

    public void setContent(RecyclerFragment.NumberViewHolder vh) {
        mVh = vh;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shower, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView tv = (TextView) getView().findViewById(R.id.showing_num);

        tv.setText(mVh.getNumberString());
        tv.setTextColor(mVh.getColor());

        Button backButton = (Button) getView().findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showList();
            }
        });
    }
}
