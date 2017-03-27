package xyz.jaggedlab.gugas.expensior.sections.expense_section;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.model.Category;
import xyz.jaggedlab.gugas.expensior.model.Currency;
import xyz.jaggedlab.gugas.expensior.model.Expense;

/**
 * Created by User on 24/03/2017.
 */

public class ExpenseRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;

    private ArrayList<Category> categories; // For the Header Graphic
    private ArrayList<Expense> expenses; // For the ret of the list.

    private double totalAmountOfTheExpenses = 0.0;
    private String currencyOfExpenses;

    private IOnExpenseItemClickListener onExpenseItemClickListener;

    public ExpenseRecyclerViewAdapter(Context context,
                                      ArrayList<Expense> expenseList,
                                      ArrayList<Category> categoryList,
                                      IOnExpenseItemClickListener onExpenseItemClickListener) {
        this.context = context;
        this.categories = categoryList;
        this.expenses = expenseList;
        this.onExpenseItemClickListener = onExpenseItemClickListener;
        this.totalAmountOfTheExpenses = this.calculateTotalAmountOfExpenses(expenseList);
        this.getCurrencyOfExpenses(); // Gets the symbol of the expenses.

    }

    public void updateExpenseList(ArrayList<Expense> newExpenseList) {
        this.expenses = newExpenseList;
        this.totalAmountOfTheExpenses = this.calculateTotalAmountOfExpenses(newExpenseList);
        this.getCurrencyOfExpenses();
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        final View itemView;

        if (viewType == ExpenseRecyclerViewType.CIRCULAR_GRAPHIC.getIntType()) {
            // HEADER WITH GRAPHIC
            itemView = this.getItemView(parent, R.layout.section_expense_header_item);
            return new GraphicHeaderViewHolder(itemView);
        }
        else if (viewType == ExpenseRecyclerViewType.TODAY_TITLE.getIntType()) {
            // TODAY TITLE in BLUE and Separator on Top
            itemView = this.getItemView(parent, R.layout.section_expense_today_title_item);
            return new TodayTitleViewHolder(itemView);
        }
        else { // ViewType is ITEM
            itemView = this.getItemView(parent, R.layout.section_expense_regular_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onExpenseItemClickListener.onItemClickedListener(itemView, expenses.get(getRealPosition(viewType)));
                }
            });
            return new ItemViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = this.getItemViewType(position);

        if (viewType == ExpenseRecyclerViewType.CIRCULAR_GRAPHIC.getIntType()) {
            // HEADER WITH GRAPHIC

        }
        else if (viewType == ExpenseRecyclerViewType.TODAY_TITLE.getIntType()) {
            // TODAY TITLE in BLUE and Separator on Top
            ((TodayTitleViewHolder)holder).todayTitle.setText(this.context.getString(R.string.todays_expenses));

            String totalExpenseAmount = this.context.getString(R.string.total_of_expenses,
                    Double.toString(this.totalAmountOfTheExpenses),
                    this.currencyOfExpenses);
            ((TodayTitleViewHolder) holder).totalAmountOfExpenses.setText(totalExpenseAmount);
        }
        else { // ViewType is ITEM
            Expense temporaryExpense = this.expenses.get(this.getRealPosition(position));

            ItemViewHolder itemViewHolder = ((ItemViewHolder) holder);
            itemViewHolder.expenseTitle.setText(temporaryExpense.getTitle());
            itemViewHolder.categoryTitle.setVisibility(View.VISIBLE);
            itemViewHolder.categoryTitle.setText(temporaryExpense.getCategory());

            String expenseAmount = this.context.getString(R.string.expended_amount,
                    Double.toString(temporaryExpense.getAmount()),
                    this.currencyOfExpenses);

            itemViewHolder.priceTitle.setVisibility(View.VISIBLE);
            itemViewHolder.priceTitle.setText(expenseAmount);
        }
    }

    @Override
    public int getItemCount() {
        int additionalNumberOfSections = 1; // for Graphic Header and Today Title
        if (this.categories != null && this.categories.size() > 0)
        {
            additionalNumberOfSections += 1;
        }

        int numberOfExpenseItems = 0;
        if (this.expenses != null && this.expenses.size() > 0) {
            numberOfExpenseItems = this.expenses.size();
        }

        return numberOfExpenseItems + additionalNumberOfSections;
    }

    private int getRealPosition(int position) {
        int additionalNumberOfSections = 1; // for Graphic Header and Today Title
        if (this.categories != null && this.categories.size() > 0)
        {
            additionalNumberOfSections += 1;
        }

        return position - additionalNumberOfSections;
    }

    private boolean checkGraphicHeaderPresent() {
        return this.categories != null && this.categories.size() > 0;
    }

    private boolean isItLastExpenseElement(int position) {
        return this.getRealPosition(position) == (this.expenses.size() -1);
    }

    private double calculateTotalAmountOfExpenses(ArrayList<Expense> expenses) {
        double sumOfAllExpenses = 0.0;
        for (Expense expense: expenses) {
            sumOfAllExpenses += expense.getAmount();
        }



        return sumOfAllExpenses;
    }

    private void getCurrencyOfExpenses() {
        if (this.expenses != null && this.expenses.size() > 0) {
            this.currencyOfExpenses = this.expenses.get(0).getCurrency();
        }
        else
        {
            this.currencyOfExpenses = Currency.EURO.getCurrencySymbol();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && this.checkGraphicHeaderPresent()) {
            // HEADER with Graph
            return ExpenseRecyclerViewType.CIRCULAR_GRAPHIC.getIntType();
        }
        else if (position == 1 && this.checkGraphicHeaderPresent()
                || (position == 0 && !this.checkGraphicHeaderPresent())) {
            // TITLE: "Today's Expenses"
            return ExpenseRecyclerViewType.TODAY_TITLE.getIntType();
        }
        else {
            return position;
        }
    }

    private View getItemView(ViewGroup parent, int resourceLayoutId) {
        return LayoutInflater.from(this.context).inflate(resourceLayoutId, parent, false);
    }

    private enum ExpenseRecyclerViewType {
        CIRCULAR_GRAPHIC(-2),
        TODAY_TITLE(-1),
        ITEM(20);

        private final int intType;
        ExpenseRecyclerViewType (int type) {
            this.intType = type;
        }

        private int getIntType() {
            return this.intType;
        }
    }

    public static class GraphicHeaderViewHolder extends RecyclerView.ViewHolder {
        public GraphicHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class TodayTitleViewHolder extends RecyclerView.ViewHolder {
        public TextView todayTitle;
        public TextView totalAmountOfExpenses;

        // TODO: Make another constructor and let the user customize the title color.
        public TodayTitleViewHolder(View itemView) {
            super(itemView);
            this.todayTitle = ((TextView) itemView.findViewById(R.id.todays_title));
            this.totalAmountOfExpenses = ((TextView) itemView.findViewById(R.id.total_amount_of_expenses));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView expenseTitle;
        public TextView categoryTitle;
        public TextView priceTitle;
        public View separatorLine;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.expenseTitle = (TextView) itemView.findViewById(R.id.regular_expense_title);
            this.categoryTitle = ((TextView) itemView.findViewById(R.id.regular_expense_category));
            this.priceTitle = ((TextView) itemView.findViewById(R.id.regular_expense_price));
            this.separatorLine = itemView.findViewById(R.id.item_separator_line);
        }
    }

}
