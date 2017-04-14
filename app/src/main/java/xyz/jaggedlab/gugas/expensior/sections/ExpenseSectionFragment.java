package xyz.jaggedlab.gugas.expensior.sections;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.model.Category;
import xyz.jaggedlab.gugas.expensior.model.Expense;
import xyz.jaggedlab.gugas.expensior.sections.expense_section.ExpenseDetail;
import xyz.jaggedlab.gugas.expensior.sections.expense_section.ExpenseRecyclerViewAdapter;
import xyz.jaggedlab.gugas.expensior.sections.expense_section.IOnExpenseItemClickListener;
import xyz.jaggedlab.gugas.expensior.sections.new_expenses.NewExpenseActivity;
import xyz.jaggedlab.gugas.expensior.utils.DateUtils;
import xyz.jaggedlab.gugas.expensior.utils.ExpenseUtils;

public class ExpenseSectionFragment extends Fragment implements IOnExpenseItemClickListener {

    private RecyclerView expenseRecyclerView;
    private ArrayList<Expense> expenses;
    private ArrayList<Category> categories;

    private Realm realmInstance;

    public ExpenseSectionFragment() {
        // Required empty public constructor
    }

    public static ExpenseSectionFragment newInstance() {
        ExpenseSectionFragment fragment = new ExpenseSectionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_expense_section, container, false);

        this.expenses = new ArrayList<>();
        this.categories = new ArrayList<>();

        this.refreshExpenseList();

        this.expenseRecyclerView = ((RecyclerView) rootView.findViewById(R.id.expense_fragment_recycler_view));
        this.expenseRecyclerView.setHasFixedSize(true);
        this.expenseRecyclerView.setAdapter(new ExpenseRecyclerViewAdapter(this.getContext(),
                this.expenses,
                this.categories,
                this));

        this.expenseRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.expenseRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.realmInstance != null && !this.realmInstance.isClosed()) {
            this.realmInstance.close();
        }
    }

    @Override
    public void onItemClickedListener(View itemView, Expense expense) {
        Intent intent = new Intent(this.getActivity(), ExpenseDetail.class);

        intent.putExtras(ExpenseUtils.getBundleFromExpense(expense));

        // TODO: startActivityForResult for possible expense editing.
        //this.getActivity().startActivityForResult(intent, E.REQUEST_CODE);
        this.getActivity().startActivity(intent);
        this.getActivity().overridePendingTransition(R.anim.animation_enter_right_to_left,
                R.anim.animation_leave_left_to_right);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != NewExpenseActivity.REQUEST_CODE) {
            return;
        }

        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshExpenseList();
                ((ExpenseRecyclerViewAdapter)ExpenseSectionFragment.this.expenseRecyclerView.getAdapter())
                        .updateExpenseList(ExpenseSectionFragment.this.expenses);
            }
        });
    }

    private void refreshExpenseList() {
        this.realmInstance = Realm.getDefaultInstance();
        RealmQuery realmQuery = this.realmInstance.where(Expense.class)
                .greaterThan("dateOfExpense", DateUtils.getBegginingOfDay(new Date()));

        RealmResults results = realmQuery.findAll();
        //Expense[] expenseArray = ((Expense[])results.toArray(new Expense[results.size()]));
        this.expenses = new ArrayList<Expense>(realmInstance.copyFromRealm(results));


        results = this.realmInstance.where(Expense.class).findAll();
        this.expenses = new ArrayList<Expense>(realmInstance.copyFromRealm(results));
    }
}
