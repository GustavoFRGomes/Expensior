package xyz.jaggedlab.gugas.expensior.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Asus on 27/03/2017.
 */

public class DimensionUtils {
    public static int convertFromDpToPixel(Context context, int dps) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round((dps * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)));
    }

    public static int covertFromPixelToDp(Context context, int pixels) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(pixels * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
