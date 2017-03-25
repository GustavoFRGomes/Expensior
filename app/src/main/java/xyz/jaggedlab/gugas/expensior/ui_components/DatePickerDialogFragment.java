package xyz.jaggedlab.gugas.expensior.ui_components;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Asus on 24/03/2017.
 */

public class DatePickerDialogFragment extends android.support.v4.app.DialogFragment implements DatePickerDialog.OnDateSetListener {
    public static final String MAX_DATE = "MAX_DATE";
    public static final String MIN_DATE = "MIN_DATE";
    public static final String HAS_MIN_DATE = "HAS_MIN_DATE";

    public static final String SELECTED_DATE = "SELECTED_DATE";
    public static final int RESULT_CODE = 2017;

    private Date maxDate = new Date();
    private Calendar calendar;

    public static DatePickerDialogFragment newInstance(Date maxDate,Date minDate) {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MAX_DATE, maxDate);
        bundle.putSerializable(MIN_DATE, minDate);
        bundle.putBoolean(HAS_MIN_DATE, true);

        fragment.setArguments(bundle);
        return fragment;
    }

    public static DatePickerDialogFragment newInstance(Date maxDate) {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MAX_DATE, maxDate);
        bundle.putBoolean(HAS_MIN_DATE, false);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        this.calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getActivity(), this, year, month, day);

        if (this.getArguments() != null) {
            DatePicker datePicker = datePickerDialog.getDatePicker();
            this.maxDate = ((Date) this.getArguments().getSerializable(MAX_DATE));
            datePicker.setMaxDate(maxDate.getTime());

            if (this.getArguments().getBoolean(HAS_MIN_DATE, false)) {
                Date minDate = ((Date) this.getArguments().getSerializable(MIN_DATE));
                datePicker.setMinDate(minDate.getTime());
            }
        }

        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        Date selectedDate = calendar.getTime();

        this.sendResult(RESULT_CODE, selectedDate);
    }

    private void sendResult(int resultCode, Date date) {
        if (this.getTargetFragment() == null)
        {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(SELECTED_DATE, date);

        this.getTargetFragment().onActivityResult(this.getTargetRequestCode(), resultCode, intent);
    }
}
