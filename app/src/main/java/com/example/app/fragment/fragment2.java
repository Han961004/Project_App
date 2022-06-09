package com.example.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.example.app.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class fragment2 extends Fragment {

    Calendar calendar;
    Long today;
    MaterialDatePicker materialDatePicker;
    SimpleDateFormat simpleDateFormat;
    private TextView date_picker_text;
    private Button date_picker_btn;
    private Button date_range_picker_btn;

    @Override
    public View onCreateView (LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_2,container,false);



        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        today = MaterialDatePicker.todayInUtcMilliseconds();
        date_picker_text = view.findViewById(R.id.date_picker_text);
        date_picker_btn = (Button)view.findViewById(R.id.date_picker_btn);
        date_range_picker_btn = (Button)view.findViewById(R.id.date_range_picker_btn);





        // 단일 날짜 선택시
        date_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Date Picker").setSelection(today).build();

                materialDatePicker.show(getParentFragmentManager(),"Date Picker");

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                        Date date = new Date();
                        date.setTime(selection);

                        String dateString = simpleDateFormat.format(date);

                        date_picker_text.setText(dateString);
                    }
                });

            }
        });




        
        // 기간 날짜 선택시
        date_range_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
                builder.setTitleText("Date Picker");
                builder.setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),MaterialDatePicker.todayInUtcMilliseconds()));
                MaterialDatePicker materialDatePicker = builder.build();
                materialDatePicker.show(getParentFragmentManager(),"Date Picker");


                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                        Date date1 = new Date();
                        Date date2 = new Date();

                        date1.setTime(selection.first);
                        date2.setTime(selection.second);

                        String dateString1 = simpleDateFormat.format(date1);
                        String dateString2 = simpleDateFormat.format(date2);

                        date_picker_text.setText(dateString1 + " \n " + dateString2);
                    }
                });

            }
        });














        
        return view;
    }
}
