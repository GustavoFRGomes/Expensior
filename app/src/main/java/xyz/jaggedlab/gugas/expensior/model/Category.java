package xyz.jaggedlab.gugas.expensior.model;

/**
 * Created by User on 24/03/2017.
 */

public class Category {

    private int id;
    private String name;
    private int color;
    private double totalAmountOfExpenses;

    public Category(int categoryId, String categoryName, int categoryColor, double totalAmountOfExpenses) {
        this.id = categoryId;
        this.name = categoryName;
        this.color = categoryColor;
        this.totalAmountOfExpenses = totalAmountOfExpenses;
    }

    private int getCategoryId() {
        return this.id;
    }

    private int getCategoryColor() {
        return this.color;
    }

    private String geteCategoryName() {
        return this.name;
    }

    private double getTotalAmountOfExpenses() {
        return this.totalAmountOfExpenses;
    }
}
