package com.example.yumly.features.details.view;

import android.app.DatePickerDialog;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Calender {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.ENGLISH);
    private static Calendar calendar;

    private Calender() {}

    public static String getMinDate() {
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public static long getMinDateForDialog() {
        return System.currentTimeMillis();
    }

    public static long getMaxDate() {
        return System.currentTimeMillis() + (7 * 1000 * 60 * 60 * 24);
    }

    public static String getDay(String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            date = inputFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        return dayFormat.format(date);
    }

    public static void showDate(Context context, DateSelectedCallback callback) {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    Calendar selectedCal = Calendar.getInstance();
                    selectedCal.set(selectedYear, selectedMonth, selectedDay);
                    String selectedDate = sdf.format(selectedCal.getTime());
                    callback.onDateSelected(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.getDatePicker().setMinDate(getMinDateForDialog());
        datePickerDialog.show();
    }
}
