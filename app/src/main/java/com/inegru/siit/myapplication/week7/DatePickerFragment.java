package com.inegru.siit.myapplication.week7;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private OnDateSelectedListener callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Set the callback only if the context that this fragment is attached to implements the required interface
        if (context instanceof OnDateSelectedListener) {
            callback = (OnDateSelectedListener) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Get Year, Month and Day from the Calendar instance
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(Objects.requireNonNull(getContext()), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Sanity check on the callback as there could be no contract for this
        if (null != callback) {
            // Create a Calendar instance with the chosen date data
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, dayOfMonth);
            callback.onDateSelected(cal);
        }
    }
}
