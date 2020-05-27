package com.inegru.siit.myapplication.week7;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.inegru.siit.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// The activity implements the interface which is used in the Date Picker Fragment
public class DatePickerActivity extends AppCompatActivity implements OnDateSelectedListener {

    private static final String DATE_FORMAT = "dd MMMM yyyy";

    // Tag used to identify the fragment later on if needed
    private static final String TAG_FRAG_DATE_PICKER = "datePicker";
    private TextView dateSelected;
    private View dateSelectedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        // Initialize the views from the layout
        dateSelected = findViewById(R.id.dateSelected);
        dateSelectedCard = findViewById(R.id.dateSelectedCard);
    }

    // Called directly from the layout - the name matters
    public void showDatePickerDialog(View view) {
        // Create the date picker dialog fragment
        DialogFragment newFragment = new DatePickerFragment();
        // Show the dialog
        newFragment.show(getSupportFragmentManager(), TAG_FRAG_DATE_PICKER);
    }

    @Override
    public void onDateSelected(@NonNull Calendar calendar) {
        // Format the date text
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        String dateFormat = format.format(calendar.getTime());
        // Use string format directly and set the text
        dateSelected.setText(getString(R.string.date_selected_n, dateFormat));

        // Show the card - initially set to gone as there is no date to show
        dateSelectedCard.setVisibility(View.VISIBLE);
    }


}
