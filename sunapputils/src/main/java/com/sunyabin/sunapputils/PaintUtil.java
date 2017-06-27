package com.sunyabin.sunapputils;

import android.graphics.Paint;
import android.graphics.Rect;

/**
* PaintUtil
* created at 2017-06-27 11:46 by SUN
* 测量字体
*/

public class PaintUtil {

    /**
     * 计算文本的宽度
     * @param paint
     * @param demoText
     * @return
     */
    public static int calcTextWidth(Paint paint, String demoText) {
        return (int) paint.measureText(demoText);
    }
    /**
     *计算文本的高度
     * @param paint
     * @param demoText
     * @return
     */
    public static int calcTextHeight(Paint paint, String demoText) {

        Rect r = new Rect();
        paint.getTextBounds(demoText, 0, demoText.length(), r);
        return r.height();
    }

    public static float getLineHeight(Paint paint) {
        Paint.FontMetrics metrics = paint.getFontMetrics();
        return metrics.descent - metrics.ascent;
    }

    public static float getLineSpacing(Paint paint) {
        Paint.FontMetrics metrics = paint.getFontMetrics();
        return metrics.ascent - metrics.top + metrics.bottom;
    }
}
