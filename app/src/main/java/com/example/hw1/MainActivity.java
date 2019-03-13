package com.example.hw1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    static final String NUMS_IN_LIST = "numsInList";
    private Integer numsInList = 100;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(NUMS_IN_LIST, numsInList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            numsInList = savedInstanceState.getInt(NUMS_IN_LIST);
        }

        setContentView(R.layout.activity_main);
        showList();
    }

    public void incNumsInList() {
        numsInList++;
    }

    public void showList() {
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setNumsInList(numsInList);
        this.showFragment(fragment);
    }

    public void showNumber(final RecyclerFragment.NumberViewHolder vh) {
        ShowerFragment fragment = new ShowerFragment();
        fragment.setContent(vh);
        this.showFragment(fragment);
    }

    private void showFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_slot, fragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}