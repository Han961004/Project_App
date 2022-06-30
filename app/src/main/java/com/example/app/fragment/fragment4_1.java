package com.example.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.app.R;

import java.util.Timer;
import java.util.TimerTask;

public class fragment4_1 extends Fragment {

    LinearLayout ET, TV;
    EditText settingSecondET, settingRestET, settingSetET;
    TextView SecondTV, RestTV, SetTV;
    Button intervalBtnET, intervalBtnTV;
    int Second1, Rest, Set = 0;

    private final Timer mTimer = new Timer();
    private TimerTask mTimerTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4_1,container,false);
        setHasOptionsMenu(true);

        ET = view.findViewById(R.id.ET);
        TV = view.findViewById(R.id.TV);

        intervalBtnET = view.findViewById(R.id.intervalBtnET);      //시작
        intervalBtnTV = view.findViewById(R.id.intervalBtnTV);      //중지

        settingRestET = view.findViewById(R.id.settingRestET);
        settingSecondET = view.findViewById(R.id.settingSecondET);
        settingSetET = view.findViewById(R.id.settingSetET);

        SecondTV = view.findViewById(R.id.SecondTV);
        SetTV = view.findViewById(R.id.SetTV);
        RestTV = view.findViewById(R.id.RestTV);

        intervalBtnET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ET.setVisibility(View.GONE);
                TV.setVisibility(View.VISIBLE);

                SecondTV.setText(settingSecondET.getText().toString());
                RestTV.setText(settingRestET.getText().toString());
                SetTV.setText(settingSetET.getText().toString());

                Second1 = Integer.parseInt(settingSecondET.getText().toString());
                Rest = Integer.parseInt(settingRestET.getText().toString());
                Set = Integer.parseInt(settingSetET.getText().toString());


                mTimerTask = createTimerTask();
                mTimer.schedule(mTimerTask, 0, 1000); //Timer 실행
            }
        });

        intervalBtnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ET.setVisibility(View.VISIBLE);
                TV.setVisibility(View.GONE);

                if(mTimerTask != null)
                    mTimerTask.cancel();
            }
        });

        return view;
    }

    private TimerTask createTimerTask(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (Second1 != 0) {

                    Second1--;
                    SecondTV.setText(Second1 + "");

                } else if (Rest!=0){

                    Rest--;
                    RestTV.setText(Rest + "");

                } else if (Set != 0){

                    Set--;
                    SetTV.setText(Set + "");

                    Rest = Integer.parseInt(settingRestET.getText().toString());
                    RestTV.setText(Rest + "");

                    Second1 = Integer.parseInt(settingSecondET.getText().toString());
                    SecondTV.setText(Second1 + "");

                } else {

                    // 다 되었다고 알림벨 설정하기

                }
            }
        };
        return timerTask;
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

