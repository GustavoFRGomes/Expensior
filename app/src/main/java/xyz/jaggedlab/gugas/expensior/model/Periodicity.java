package xyz.jaggedlab.gugas.expensior.model;

import java.util.ArrayList;

/**
 * Created by Asus on 24/03/2017.
 */

public enum Periodicity {
    ONE_TIME_OCCURENCE("One Time Only", 0),
    DAILY("Daily", 1),
    WEEKLY("Weekly", 7),
    MONTHLY("Monthly", 30), // This is a special case since not all months has 30 days.
    YEARLY("Yearly", 365); // This is also a special case since all years don't have 365 days.

    Periodicity(String stringValue, int numberOfDays) {
        this.numberOfDays = numberOfDays;
        this.periodNaming = stringValue;
    }

    private String periodNaming;
    private int numberOfDays;

    public int getNumberOfDays() {
        return this.numberOfDays;
    }

    public String getPeriodName() {
        return this.periodNaming;
    }

    public static ArrayList<Periodicity> getFullListOfPeriods() {
        ArrayList<Periodicity> periodsArrayList = new ArrayList<>();
        periodsArrayList.add(ONE_TIME_OCCURENCE);
        periodsArrayList.add(DAILY);
        periodsArrayList.add(WEEKLY);
        periodsArrayList.add(MONTHLY);
        periodsArrayList.add(YEARLY);

        return periodsArrayList;
    }

    public static Periodicity getPeriodicityBaseOnNumberOfDays(int numberOfDays) {
        switch(numberOfDays) {
            case 1:
                return DAILY;

            case 7:
                return WEEKLY;

            case 30:
                return MONTHLY;

            case 365:
                return YEARLY;

            case 0:
            default:
                return ONE_TIME_OCCURENCE;
        }
    }
}
