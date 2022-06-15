package com.example.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.app.R;

public class fragment4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menutimer, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item1:
                getParentFragmentManager().beginTransaction().replace(R.id.frame, new fragment4_1()).commit();
                break;
            case R.id.menu_item2:
                getParentFragmentManager().beginTransaction().replace(R.id.frame, new fragment4_2()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
