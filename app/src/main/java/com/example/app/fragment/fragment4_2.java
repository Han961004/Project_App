package com.example.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.app.R;

import java.util.Timer;
import java.util.TimerTask;


// 일반타이머
public class fragment4_2 extends Fragment {

    TextView hourET, minuteET, secondET, recordView;
    Button startBtn, stopBtn, recordBtn;
    int hour, minute, second = 0;
    boolean a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4_2, container, false);
        setHasOptionsMenu(true);


        hourET = view.findViewById(R.id.hourET);
        minuteET = view.findViewById(R.id.minuteET);
        secondET = view.findViewById(R.id.secondET);

        hourET.setText(hour + "");
        minuteET.setText(minute + "");
        secondET.setText(second + "");

        startBtn = view.findViewById(R.id.startBtn);
        stopBtn = view.findViewById(R.id.stopBtn);
        recordBtn = view.findViewById(R.id.recordBtn);
        recordView = view.findViewById(R.id.recordView);

        // 나중에 수정해야겠다

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                a = true;

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {

                        while (!a) {
                                            // 여기서 멈춰버린다 false하면
                        }


                        second++;
                        secondET.setText(second + "");

                        if (second == 60) {
                            minute++;
                            minuteET.setText(minute + "");
                            second = 0;
                        }

                        if (minute == 60) {
                            hour++;
                            hourET.setText(hour + "");
                            minute = 0;
                        }

                        //시, 분, 초가 10이하(한자리수) 라면
                        // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                        if (second <= 9) {
                            secondET.setText("0" + second);
                        } else {
                            secondET.setText(Integer.toString(second));
                        }

                        if (minute <= 9) {
                            minuteET.setText("0" + minute);
                        } else {
                            minuteET.setText(Integer.toString(minute));
                        }

                        if (hour <= 9) {
                            hourET.setText("0" + hour);
                        } else {
                            hourET.setText(Integer.toString(hour));
                        }
                    }
                };
                timer.schedule(timerTask, 0, 1000); //Timer 실행
            }
        });

        // 인터럽트 쓰는게 좋은데 못 하겠네

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = false;
            }
        });

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordView.setText(recordView.getText() + "\n" + hourET.getText() + " : " + minuteET.getText() + " : " + secondET.getText() + "\n");

//                recordT.setText(mRecordTextView.getText() + mTimeTextView.getText().toString() + "\n");
            }
        });


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
//            case R.id.menu_item3:
//                getParentFragmentManager().beginTransaction().replace(R.id.frame, new fragment4_3()).commit();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
