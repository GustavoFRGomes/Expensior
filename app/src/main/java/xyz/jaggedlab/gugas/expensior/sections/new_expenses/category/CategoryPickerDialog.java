package xyz.jaggedlab.gugas.expensior.sections.new_expenses.category;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;

import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.model.Category;
import xyz.jaggedlab.gugas.expensior.sections.new_expenses.IOnCategorySelectedCallback;

/**
 * Created by gustavogomes on 14/04/2017.
 */

public class CategoryPickerDialog extends DialogFragment {

    public static final String CategoryPickerTag = "CategoryPicker";

    public IOnCategorySelectedCallback onCategorySelectedCallback;

    public static CategoryPickerDialog newInstance(IOnCategorySelectedCallback onCategorySelectedCallback) {
        CategoryPickerDialog dialogFragment = new CategoryPickerDialog();
        dialogFragment.onCategorySelectedCallback = onCategorySelectedCallback;

        return dialogFragment;
    }

    private RecyclerView categoriesRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.category_picker_dialog_fragment, parent, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        this.categoriesRecycler = ((RecyclerView) rootView.findViewById(R.id.category_choosing_list));
        this.categoriesRecycler.setHasFixedSize(true);
        this.categoriesRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.categoriesRecycler.setAdapter(new CategoryRecyclerAdapter(this.getContext(), new ArrayList<Category>()));

        return rootView;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, CategoryPickerDialog.CategoryPickerTag);
    }
}
