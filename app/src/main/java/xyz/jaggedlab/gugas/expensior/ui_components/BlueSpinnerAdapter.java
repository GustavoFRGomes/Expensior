package xyz.jaggedlab.gugas.expensior.ui_components;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.model.Expense;

/**
 * Created by Asus on 23/03/2017.
 */

public class BlueSpinnerAdapter extends BaseAdapter {

    private ArrayList<String> items;
    private int spinnerLayout;
    private int dropDownLayout;
    private Context context;
    private Spinner spinner;

    public static class Builder {
        private final ArrayList<String> items;
        private final Context context;
        private final Spinner spinner;
        private int spinnerBackgroundLayout = R.layout.spinner_collapsed_item_layout;
        private int spinnerDropDownLayout = R.layout.spinner_dropdown_item_layout;

        public Builder(Context context, Spinner spinner, ArrayList<String> items) {
            this.context = context;
            this.spinner = spinner;
            this.items = items;
        }

        public Builder spinnerBackground(int layoutResourceId) {
            this.spinnerBackgroundLayout = layoutResourceId;
            return this;
        }

        public Builder spinnetDropDownLayout(int layoutResourceId) {
            this.spinnerDropDownLayout = layoutResourceId;
            return this;
        }

        public BlueSpinnerAdapter build() {
            return new BlueSpinnerAdapter(this.context, this.spinner, this.items, this.spinnerBackgroundLayout, this.spinnerDropDownLayout);
        }
    }

    /*public BlueSpinnerAdapter(Builder builder) {
        this.context = builder.context;
        this.spinner = builder.spinner;
        this.dropDownLayout = builder.spinnerDropDownLayout;
        this.spinnerLayout = builder.spinnerBackgroundLayout;
        this.items = builder.items;
    }*/

    private BlueSpinnerAdapter(Context context, Spinner spinner, ArrayList<String> items, int spinnerLayout, int dropdownLayout) {
        this.context = context;
        this.spinner = spinner;
        this.items = items;
        this.spinnerLayout = spinnerLayout;
        this.dropDownLayout = dropdownLayout;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);

        View view = ((convertView != null) ? convertView : inflater.inflate(this.spinnerLayout, parent, false));

        TextView title = ((TextView)view.findViewById(R.id.spinner_item_text));
        title.setText(this.items.get(position));

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);

        View view = ((convertView != null) ? convertView : inflater.inflate(this.dropDownLayout, parent, false));

        TextView title = ((TextView)view.findViewById(R.id.dropdown_item_text));
        title.setText(this.items.get(position));

        if (spinner.getSelectedItemPosition() == position) {
            title.setTextColor(ContextCompat.getColor(this.context, R.color.colorAccent));
        }

        if (position == (this.getCount() - 1)) {
            View separatorLine = ((View) view.findViewById(R.id.separator_line));
            separatorLine.setVisibility(View.GONE);
        }

        return view;
    }

    private void setSelectedPosition(int position) {

    }
}
