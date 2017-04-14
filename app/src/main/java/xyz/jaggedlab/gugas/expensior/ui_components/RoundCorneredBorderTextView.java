package xyz.jaggedlab.gugas.expensior.ui_components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import xyz.jaggedlab.gugas.expensior.R;

/**
 * Created by Gustavo Gomes on 14/04/2017.
 */

public class RoundCorneredBorderTextView extends android.support.v7.widget.AppCompatTextView {

    public RoundCorneredBorderTextView(Context context) {
        super(context);
    }

    public RoundCorneredBorderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public RoundCorneredBorderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        TypedArray viewAttributes = context
                .getTheme()
                .obtainStyledAttributes(attrs, R.styleable.RoundedCorneredTextView, 0, 0);

        int borderColorInt = (viewAttributes.getColor(R.styleable.RoundedCorneredTextView_border_color,
                ContextCompat.getColor(context, R.color.transparent)));

        this.setBackgroundResource(R.drawable.round_cornered_border);
        this.getBackground().setColorFilter(borderColorInt, PorterDuff.Mode.SRC_ATOP);

        this.setTextAlignment(TEXT_ALIGNMENT_CENTER);
    }

    public void changeColor(int colorResourceId) {
        this.changeColor(colorResourceId, colorResourceId);
    }

    public void changeColor(int borderResourceColorId, int textResourceColorId) {
        int borderColorInt = ContextCompat.getColor(this.getContext(), borderResourceColorId);
        int textColorInt = ((borderResourceColorId == textResourceColorId) ?
                borderColorInt : ContextCompat.getColor(this.getContext(), textResourceColorId));

        this.getBackground().setColorFilter(borderColorInt, PorterDuff.Mode.SRC_ATOP);
        this.setTextColor(textColorInt);

    }

    public void changeBorderlineColorFromColorId(int colorResourceId) {
        int borderColorInt = ContextCompat.getColor(this.getContext(), colorResourceId);
        this.getBackground().setColorFilter(borderColorInt, PorterDuff.Mode.SRC_ATOP);
    }
}
