package xyz.jaggedlab.gugas.expensior.utils;

import android.os.Bundle;

import xyz.jaggedlab.gugas.expensior.model.Expense;

/**
 * Created by User on 25/03/2017.
 */

public class ExpenseUtils {

    private static final String EXPENSE_TITLE = "EXPENSE_TITLE";
    private static final String EXPENSE_DESCRIPTION = "EXPENSE_DESCRIPTION";
    private static final String EXPENSE_CATEGORY = "EXPENSE_CATEGORY";
    private static final String EXPENSE_CURRENCY = "EXPENSE_CURRENCY";
    private static final String EXPENSE_CATEGORY_ID = "EXPENSE_CATEGORY_ID";

    private static final String EXPENSE_AMOUNT = "EXPENSE_AMOUNT";
    private static final String EXPENSE_ID = "EXPENSE_ID";

    private static final String EXPENSE_DATE = "EXPENSE_DATE";

    private static final String EXPENSE_PERIODICITY = "EXPENSE_PERIODICITY";

    public static Bundle getBundleFromExpense(Expense expense) {
        Bundle bundle = new Bundle();

        bundle.putString(EXPENSE_TITLE, expense.getTitle());
        bundle.putString(EXPENSE_DESCRIPTION, expense.getDescription());

        bundle.putString(EXPENSE_CATEGORY, expense.getCategory());
        bundle.putString(EXPENSE_CURRENCY, expense.getCurrency());

        bundle.putInt(EXPENSE_CATEGORY_ID, expense.getCategoryId());
        bundle.putInt(EXPENSE_ID, expense.getId());
        bundle.putInt(EXPENSE_PERIODICITY, expense.getPeriodicity());

        bundle.putLong(EXPENSE_DATE, expense.getDateOfExpense());

        bundle.putDouble(EXPENSE_AMOUNT, expense.getAmount());

        return bundle;
    }

    public static Expense extractExpenseFromBundle(Bundle bundle) {
        int id = bundle.getInt(EXPENSE_ID);
        String title = bundle.getString(EXPENSE_TITLE);
        String description = bundle.getString(EXPENSE_DESCRIPTION);
        String category = bundle.getString(EXPENSE_CATEGORY);
        int categoryId = bundle.getInt(EXPENSE_CATEGORY_ID);
        int periodicity = bundle.getInt(EXPENSE_PERIODICITY);
        double amount = bundle.getDouble(EXPENSE_AMOUNT);
        String currency = bundle.getString(EXPENSE_CURRENCY);
        long dateOfExpense = bundle.getLong(EXPENSE_DATE);

        return new Expense(id, title, description, category, categoryId,
                periodicity, amount, currency, dateOfExpense);
    }

    public static boolean isBundledExpenseValid(Bundle bundle) {
        // NOTE: Minimal validation.

        String title = bundle.getString(EXPENSE_TITLE, null);
        String description = bundle.getString(EXPENSE_DESCRIPTION, null);
        String category = bundle.getString(EXPENSE_CATEGORY, null);
        String currency = bundle.getString(EXPENSE_CURRENCY, null);

        return title == null || description == null || category == null || currency == null
                || (bundle != null && (!bundle.containsKey(EXPENSE_ID) || !bundle.containsKey(EXPENSE_AMOUNT)
                                    || !bundle.containsKey(EXPENSE_CATEGORY_ID)) || !bundle.containsKey(EXPENSE_DATE));
    }
}
