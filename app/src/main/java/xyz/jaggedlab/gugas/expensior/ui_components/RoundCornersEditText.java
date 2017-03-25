package xyz.jaggedlab.gugas.expensior.ui_components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import xyz.jaggedlab.gugas.expensior.R;

/**
 * Created by Gustavo Gomes on 24/03/2017.
 */

public class RoundCornersEditText extends AppCompatEditText {

    private float roundedRadiusSize;
    private float smoothRadiusSize;

    private int backgroundColorInt;
    private int errorColor;
    private boolean showsError;

    public RoundCornersEditText(Context context) {
        super(context);
    }

    public RoundCornersEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*this.initializeView(context, attrs);*/
    }

    private void initializeView(Context context, AttributeSet attributeSet) throws Exception {
        this.roundedRadiusSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        this.smoothRadiusSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());

        float[] cornerRadii = new float[4];

        if (attributeSet != null) {

            // Get the TypedArray for the Corners from RoundCornersView
            TypedArray roundCorneredViewTypedArray = context
                    .getTheme()
                    .obtainStyledAttributes(attributeSet, R.styleable.RoundCornersView, 0, 0);

            // Get the TypedArray for the Errors from RoundCornersEditText
            TypedArray roundedCorneredEditextTypedArray = context
                    .getTheme()
                    .obtainStyledAttributes(attributeSet, R.styleable.RoundCornersEditText,0, 0);

            // Process the shape of the corners
            if (roundCorneredViewTypedArray.getBoolean(R.styleable.RoundCornersView_round_corners, false)) {
                cornerRadii = this.initializeCornerRadiiWithValue(cornerRadii, roundedRadiusSize);
            }
            else if (roundCorneredViewTypedArray.getBoolean(R.styleable.RoundCornersView_smooth_corners, false)) {
                cornerRadii = this.initializeCornerRadiiWithValue(cornerRadii, smoothRadiusSize);
            }
            else {
                cornerRadii = this.setPersonalizedCornerStyle(smoothRadiusSize, roundedRadiusSize, roundCorneredViewTypedArray);
            }

            if (this.getBackground() instanceof ColorDrawable) {

                ColorDrawable backgroundColorDrawable = ((ColorDrawable) this.getBackground());
                this.backgroundColorInt = backgroundColorDrawable.getColor();
            }
            else {
                throw new Exception("The Background should be a Color!");
            }

            this.errorColor = roundedCorneredEditextTypedArray.getColor(R.styleable.RoundCornersEditText_error_color,
                    this.backgroundColorInt);

            // If the error color is the same as the background color it won't show an error.
            this.showsError = (!(this.errorColor == this.backgroundColorInt));
        }
    }

    private float[] setPersonalizedCornerStyle(float smoothRadiiSize, float roundedRadiiSize, TypedArray cornersPersonalization) {
        float[] cornerRadii = new float[4];

        cornerRadii[0] = this.giveCornerRadiiValue(roundedRadiiSize,
                smoothRadiiSize,
                cornersPersonalization.getBoolean(R.styleable.RoundCornersView_round_corner_top_left, false),
                cornersPersonalization.getBoolean(R.styleable.RoundCornersView_smooth_corner_top_left, false));

        cornerRadii[1] = this.giveCornerRadiiValue(roundedRadiiSize,
                smoothRadiiSize,
                cornersPersonalization.getBoolean(R.styleable.RoundCornersView_round_corner_top_right, false),
                cornersPersonalization.getBoolean(R.styleable.RoundCornersView_smooth_corner_top_right, false));

        cornerRadii[2] = this.giveCornerRadiiValue(roundedRadiiSize,
                smoothRadiiSize,
                cornersPersonalization.getBoolean(R.styleable.RoundCornersView_round_corner_bottom_right, false),
                cornersPersonalization.getBoolean(R.styleable.RoundCornersView_smooth_corner_bottom_right, false));

        cornerRadii[3] = this.giveCornerRadiiValue(roundedRadiiSize,
                smoothRadiiSize,
                cornersPersonalization.getBoolean(R.styleable.RoundCornersView_round_corner_bottom_left, false),
                cornersPersonalization.getBoolean(R.styleable.RoundCornersView_smooth_corner_bottom_left, false));

        return cornerRadii;
    }

    private float giveCornerRadiiValue(float roundedRadiusSize, float smoothRadiusSize, boolean roundedCorner, boolean smoothCorner) {
        if (roundedCorner) {
            return roundedRadiusSize;
        }

        if (smoothCorner) {
            return smoothRadiusSize;
        }

        return 0;
    }

    private float[] initializeCornerRadiiWithValue(float[] cornerRadii, float radiusSize) {
        for (int i=0; i<4; i++) {
            cornerRadii[i] = radiusSize;
        }

        return cornerRadii;
    }
}
