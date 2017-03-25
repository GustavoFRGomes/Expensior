package xyz.jaggedlab.gugas.expensior.sections.expense_section;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.model.Expense;
import xyz.jaggedlab.gugas.expensior.utils.ExpenseUtils;

public class ExpenseDetail extends AppCompatActivity {

    private Expense selectedExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        //this.getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        //((TextView)this.getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title)).setText(R.string.title_activity_new_expense);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpenseDetail.this.onBackPressed();
            }
        });

        Bundle bundle = this.getIntent().getExtras();
        if (ExpenseUtils.isBundledExpenseValid(bundle)) {
            this.selectedExpense = ExpenseUtils.extractExpenseFromBundle(bundle);
        }
        else
        {
            this.selectedExpense = null; // Way of signalling that the expense is invalid.
        }
    }

    public Expense getSelectedExpense() {
        return this.selectedExpense;
    }

    @Override
    public void onBackPressed() {
        this.finish();
        this.overridePendingTransition(R.anim.animation_enter_left_to_right, R.anim.animation_leave_right_to_left);
    }

}
