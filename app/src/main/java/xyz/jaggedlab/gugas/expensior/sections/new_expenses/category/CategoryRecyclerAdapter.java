package xyz.jaggedlab.gugas.expensior.sections.new_expenses.category;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.model.Category;
import xyz.jaggedlab.gugas.expensior.ui_components.RoundCorneredBorderTextView;

/**
 * Created by gustavogomes on 15/04/2017.
 */

public class CategoryRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<Category> categoryList;
    private Context context;

    public CategoryRecyclerAdapter (Context context, ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (this.isLastPosition(viewType)) {
            itemView = LayoutInflater.from(this.context).inflate(R.layout.category_list_add_category_item, parent, false);

            return new AddCategoryItemView(itemView);
        }
        else {
            itemView = LayoutInflater.from(this.context).inflate(R.layout.category_list_item, parent, false);

            return new CategoryItemView(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    private boolean isLastPosition(int position) {
        // This tells us that it is the last position, since the index starts at 0.
        return categoryList.size() == position;
    }

    @Override
    public int getItemCount() {
        return categoryList.size() + 1; // The +1 is for the Add Category section.
    }

    public static class CategoryItemView extends RecyclerView.ViewHolder {
        public RoundCorneredBorderTextView categoryView;

        public CategoryItemView (View itemView) {
            super(itemView);
            this.categoryView = ((RoundCorneredBorderTextView) itemView.findViewById(R.id.category_name_label));
        }
    }

    public static class AddCategoryItemView extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView addCategoryLabel;

        public LinearLayout addNewCategoryLayout;
        public EditText addNewCategoryName;
        public EditText addNewCategoryColor;
        public Button addNewCategorySubmit;

        public AddCategoryItemView(View itemView) {
            super(itemView);

            this.addCategoryLabel = ((TextView) itemView.findViewById(R.id.add_category_label));
            this.addCategoryLabel.setOnClickListener(this);

            this.addNewCategoryLayout = ((LinearLayout) itemView.findViewById(R.id.new_category_layout));
            this.addNewCategoryName = ((EditText) itemView.findViewById(R.id.new_category_name));
            this.addNewCategoryColor = ((EditText) itemView.findViewById(R.id.new_category_color));
            this.addNewCategorySubmit = ((Button) itemView.findViewById(R.id.new_category_submit));

            this.addNewCategoryLayout.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == this.addCategoryLabel.getId()) {
                if (this.addNewCategoryLayout.getVisibility() == View.GONE) {
                    this.addNewCategoryLayout.setVisibility(View.VISIBLE);
                }
                else {
                    this.addNewCategoryLayout.setVisibility(View.GONE);

                }
            }
        }
    }
}
