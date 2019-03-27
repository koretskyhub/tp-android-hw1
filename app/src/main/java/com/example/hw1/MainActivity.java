package com.example.hw1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RecyclerFragment.IncNumListener,
                                                               ItemClickListener {
    private Integer numsInList = 100;
    private RecyclerFragment recyclerFragment;
    private ShowerFragment showerFragment;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("NUMS_IN_LIST", numsInList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            numsInList = savedInstanceState.getInt("NUMS_IN_LIST");
        }

        setContentView(R.layout.activity_main);

        showerFragment = (ShowerFragment) getSupportFragmentManager().findFragmentByTag(ShowerFragment.TAG);
        recyclerFragment = (RecyclerFragment) getSupportFragmentManager().findFragmentByTag(RecyclerFragment.TAG);

        if (showerFragment == null) {
            showerFragment = new ShowerFragment();
        }

        if (recyclerFragment == null) {
            recyclerFragment = new RecyclerFragment();
        }

        if(getSupportFragmentManager().findFragmentByTag(showerFragment.TAG) == null) {
            showList();
        }
    }

    public void incNumsInList() {
        numsInList++;
    }

    public void showList() {
        Bundle bundle = new Bundle();
        bundle.putInt("NUM_IN_LIST", numsInList);
        recyclerFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.frag_slot, recyclerFragment, RecyclerFragment.TAG).
                commit();
    }

    public void showNumber(final String value, final Integer color) {
        Bundle bundle = new Bundle();
        bundle.putString("NUM_TO_SHOW", value);
        bundle.putInt("CLR_TO_SHOW", color);
        showerFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.frag_slot, showerFragment, ShowerFragment.TAG).
                addToBackStack(null).
                commit();
    }
}