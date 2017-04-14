package xyz.jaggedlab.gugas.expensior.model;

import android.os.Bundle;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Asus on 23/03/2017.
 */

public class Expense extends RealmObject {
    @PrimaryKey
    private int id;
    private String title;
    private String description;

    private String category;

    private int categoryId;

    private int periodicity;

    private double amount;
    private String currency;

    @Index
    private long dateOfExpense;

    public Expense() {}

    public Expense(int id,
                   String title,
                   String description,
                   String category,
                   int categoryId,
                   int periodicity,
                   double amount,
                   String currency,
                   long dateOfExpense) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.categoryId = categoryId;
        this.periodicity = periodicity;
        this.amount = amount;
        this.currency = currency;
        this.dateOfExpense = dateOfExpense;
    }

    public String getTitle() {
        return this.title;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCategory() {
        return this.category;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public int getPeriodicity() {
        return this.periodicity;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public long getDateOfExpense() {
        return this.dateOfExpense;
    }

    // Setters
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setPeriodicity(int periodicity) {
        this.periodicity = periodicity;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency (String currency) {
        this.currency = currency;
    }

    public void setDateOfExpense(long dateOfExpense) {
        this.dateOfExpense = dateOfExpense;
    }

    public Periodicity getPeriodicityEnumValue() {
        return Periodicity.getPeriodicityBaseOnNumberOfDays(this.periodicity);
    }

}
