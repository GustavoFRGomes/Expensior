package xyz.jaggedlab.gugas.expensior.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by User on 24/03/2017.
 */

public class Category extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private int color;

    @Ignore
    private double totalAmountOfExpenses;

    public Category() {

    }

    public Category(int categoryId, String categoryName, int categoryColor, double totalAmountOfExpenses) {
        this.id = categoryId;
        this.name = categoryName;
        this.color = categoryColor;
        this.totalAmountOfExpenses = totalAmountOfExpenses;
    }

    public int getCategoryId() {
        return this.id;
    }

    public int getCategoryColor() {
        return this.color;
    }

    public String geteCategoryName() {
        return this.name;
    }

    public double getTotalAmountOfExpenses() {
        return this.totalAmountOfExpenses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setTotalAmountOfExpenses(double totalAmountOfExpenses) {
        this.totalAmountOfExpenses = totalAmountOfExpenses;
    }
}
