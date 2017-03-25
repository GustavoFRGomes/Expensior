package xyz.jaggedlab.gugas.expensior.sections.expense_section;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.jaggedlab.gugas.expensior.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ExpenseDetailFragment extends Fragment {

    public ExpenseDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expense_detail, container, false);

        // TODO: Get all the layout linked and ready.

        return rootView;
    }
}
