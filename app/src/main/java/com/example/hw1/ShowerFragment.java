package com.example.hw1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ShowerFragment extends Fragment {
    public static final String TAG = "ShowerFragment";

    private String mValue;
    private Integer mColor;

    public ShowerFragment() {
        // Required empty public constructor
    }

    public void setContent(String value, Integer color) {
        mValue = value;
        mColor = color;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        if (bundle != null) {
            mValue = bundle.getString("NUM_TO_SHOW");
            mColor = bundle.getInt("CLR_TO_SHOW");
        }

        return inflater.inflate(R.layout.fragment_shower, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView tv = (TextView) getView().findViewById(R.id.showing_num);

        tv.setText(mValue);
        tv.setTextColor(mColor);

        Button backButton = (Button) getView().findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
}
