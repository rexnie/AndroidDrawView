package com.example.android.androiddrawview;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by niedaocai on 16-11-21.
 */

public class DisplayUtil {
    /**
     * 将px值转换为dip或dp值,保证尺寸大小不变
     *
     * @param context
     * @param pxValue
     *            (DisplayMetrics类中属性density)
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值,保证尺寸大小不变
     *
     * @param context
     * @param dipValue
     *            (DisplayMetrics类中属性density)
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值,保证文字大小不变
     *
     * @param context
     * @param pxValue
     *            (DisplayMetrics类中属性scaledDensity)
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5);
    }

    /**
     * 将sp值转换为px值,保证文字大小不变
     *
     * @param context
     * @param spValue
     *            (DisplayMetrics类中属性scaledDensity)
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 使用　TypedValue　将dip或dp值转换为px值
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

    /**
     * 使用　TypedValue　将sp值转换为px值
     */
    public static int sp2px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
    }
}