package xyz.jaggedlab.gugas.expensior.sections.expense_section;

import android.view.View;

import xyz.jaggedlab.gugas.expensior.model.Expense;

/**
 * Created by User on 25/03/2017.
 */

public interface IOnExpenseItemClickListener  {
    void onItemClickedListener(View itemView, Expense expense);
}
