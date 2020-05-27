package com.inegru.siit.myapplication.week7;

import androidx.annotation.NonNull;

import java.util.Calendar;

// Custom contract that will be used to pass the Date from the Date Picker Dialog to the Activity
public interface OnDateSelectedListener {

    /**
     * Date selected.
     *
     * @param calendar Calendar instance with the selected date data.
     */
    void onDateSelected(@NonNull Calendar calendar);

}
