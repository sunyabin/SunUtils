package com.sunyabin.sunapputils;

import android.view.MotionEvent;
import android.view.View;
/**
*CheckViewInRange
*created at 2017-06-27 11:28 by SUN
*判断MotionEvent 是否在 view 范围
*/
public class CheckViewInRange {
    /**
     * 判断MotionEvent 是否在 view 范围
     * @param view
     * @param ev
     * @return  boolean
     */
    public static boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        if(view.getVisibility() != View.VISIBLE) {
            return false;
        } else {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            return ev.getRawX() >= (float)x && ev.getRawX() <= (float)(x + view.getWidth()) && ev.getRawY() >= (float)y && ev.getRawY() <= (float)(y + view.getHeight());
        }
    }
}
