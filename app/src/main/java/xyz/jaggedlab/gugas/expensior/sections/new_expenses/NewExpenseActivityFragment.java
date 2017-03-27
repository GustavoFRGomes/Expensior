package xyz.jaggedlab.gugas.expensior.sections.new_expenses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.SharedPreferencesCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.model.Currency;
import xyz.jaggedlab.gugas.expensior.model.Expense;
import xyz.jaggedlab.gugas.expensior.model.Periodicity;
import xyz.jaggedlab.gugas.expensior.ui_components.BlueSpinnerAdapter;
import xyz.jaggedlab.gugas.expensior.ui_components.DatePickerDialogFragment;
import xyz.jaggedlab.gugas.expensior.utils.DateUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewExpenseActivityFragment extends Fragment implements LinearLayout.OnClickListener {

    private EditText titleField;
    private EditText amountField;
    private EditText categoryField;

    private EditText dateField;
    private InputMethodManager inputMethodManager;

    private EditText descriptionField;

    private LinearLayout addExpenseLabel;

    private Spinner periodicSpinner;
    private BlueSpinnerAdapter periodicBlueSpinnerAdapter;

    private ArrayList<Periodicity> periodicityOfExpense;
    private Date selectedDate = new Date();

    private Realm realmInstance;

    public NewExpenseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_expense, container, false);

        this.realmInstance = Realm.getDefaultInstance();

        this.periodicityOfExpense = new ArrayList<>();
        this.inputMethodManager = ((InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE));

        this.periodicSpinner = ((Spinner)rootView.findViewById(R.id.predicity_spinner));
        // Set the Adapter for the Spinner
        this.periodicSpinner.setAdapter(this.getPeriodicityAdapter(this.periodicSpinner));

        this.titleField = ((EditText)rootView.findViewById(R.id.title_of_expense_field));
        this.amountField = ((EditText)rootView.findViewById(R.id.amount_of_expense_field));
        this.categoryField = ((EditText) rootView.findViewById(R.id.category_of_expense_field));
        this.dateField = ((EditText) rootView.findViewById(R.id.date_of_expense_field));
        this.dateField.setFocusable(false);
        this.dateField.setOnClickListener(this);
        this.dateField.setKeyListener(null);
        this.descriptionField = ((EditText) rootView.findViewById(R.id.description_of_expense_field));

        this.addExpenseLabel = ((LinearLayout) rootView.findViewById(R.id.add_expense_layout_button));
        this.addExpenseLabel.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (this.realmInstance != null && !this.realmInstance.isClosed()) {
            this.realmInstance.close();
        }
    }

    private BlueSpinnerAdapter getPeriodicityAdapter(Spinner spinner) {
        this.periodicityOfExpense = Periodicity.getFullListOfPeriods();

        ArrayList<String> periodicityListOfStrings = new ArrayList<>();

        for (Periodicity period: this.periodicityOfExpense) {
            periodicityListOfStrings.add(period.getPeriodName());
        }

        this.periodicBlueSpinnerAdapter = (new BlueSpinnerAdapter.Builder(this.getContext(), spinner, periodicityListOfStrings).build());

        return this.periodicBlueSpinnerAdapter;
    }

    private Periodicity getPeriodicityFromSpinner() {
        int selectedPeriodPosition = this.periodicSpinner.getSelectedItemPosition();
        return this.periodicityOfExpense.get(selectedPeriodPosition);
    }

    private void extractExpenseFromLayout() {
        // Method that will create the new Expense from the data that was inserted in this screen

        String title = this.titleField.getText().toString();
        String description = this.descriptionField.getText().toString();

        String category = this.categoryField.getText().toString();

        Periodicity periodicity = this.getPeriodicityFromSpinner();


        String amountText = this.amountField.getText().toString();
        double amount = 0.0;

        // TODO: Get the Expense ID, for the new item.
        int id = 0;

        boolean hasErrors = false;
        if (title == null || title.isEmpty()) {
            // Error on the title, it is a required field.
            hasErrors = true;
        }
        if (amountText == null || amountText.isEmpty()) {
            // Error on the amount, it is a required field.
            hasErrors = true;
        }
        else {
            amount = Double.parseDouble(amountText);
        }

        if (!hasErrors) {


            // TODO: Save in the DB.
            // TODO: Finish the Activity and go back to the MainActivity.
            this.realmInstance.beginTransaction();

            this.realmInstance = Realm.getDefaultInstance();
            Number maximumNumber = this.realmInstance.where(Expense.class).max("id");

            int lastId = 0;
            if (maximumNumber != null) {
                lastId = maximumNumber.intValue();
            }

            Expense newExpense = new Expense(lastId + 1, title, description, category, -1, periodicity.getNumberOfDays(), amount, Currency.EURO.getCurrencySymbol(), selectedDate.getTime());

            Expense savedExpense = this.realmInstance.copyToRealm(newExpense);
            this.realmInstance.commitTransaction();

            this.getActivity().finishActivity(NewExpenseActivity.REQUEST_CODE);
        }

    }

    private void onAddExpenseClicked() {
        this.extractExpenseFromLayout();

        Toast.makeText(this.getContext(), R.string.expense_successfully_submitted, Toast.LENGTH_LONG).show();

        this.getActivity().finish();
    }

    private void onExpenseDateClicked() {
        this.inputMethodManager.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        this.dateField.setFocusable(true);

        DatePickerDialogFragment dialog = DatePickerDialogFragment.newInstance(new Date());
        dialog.setTargetFragment(NewExpenseActivityFragment.this, Activity.RESULT_OK);
        dialog.show(this.getFragmentManager(), "DATE_PICKER_DIALOG");
    }

    private void changeDateInLayout(Date selectedDate) {
        this.selectedDate = selectedDate;

        if (DateUtils.compareDates(new Date(), selectedDate)) {
            this.dateField.setText(this.getResources().getText(R.string.today), TextView.BufferType.EDITABLE);
        }
        else
        {
            this.dateField.setText(DateUtils.formatDate(selectedDate), TextView.BufferType.EDITABLE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.dateField.setFocusable(true);
        this.dateField.setInputType(InputType.TYPE_CLASS_TEXT);
        this.dateField.requestFocus();
        this.dateField.setFocusable(false);
        this.dateField.setInputType(InputType.TYPE_NULL);

        if (requestCode != Activity.RESULT_OK) {
            return;
        }

        if (resultCode == DatePickerDialogFragment.RESULT_CODE) {
            Date selectedDate = ((Date) data.getSerializableExtra(DatePickerDialogFragment.SELECTED_DATE));
            this.changeDateInLayout(selectedDate);
        }
    }

    @Override
    public void onClick(View view) {
        // Date Clicked
        if (view.getId() == this.dateField.getId()) {
            this.onExpenseDateClicked();
        }

        // Add Expense Button
        if (view.getId() == this.addExpenseLabel.getId()) {
            this.onAddExpenseClicked();
        }
    }
}
