package com.dylan_wang.capturescreen;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class ScreenUtil {

    public static int statusBarHeight(Activity activity) {
        Rect rectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        return statusBarHeight;
    }

    public static int statusBarHeight2(Resources res) {
        int statusBarHeight = 0;
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    // https://stackoverflow.com/questions/3407256/height-of-status-bar-in-android
    public void test(Activity activity) {
        Rect rectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight= contentViewTop - statusBarHeight;

        Log.i("*** Elenasys :: ", "StatusBar Height= " + statusBarHeight + " , TitleBar Height = " + titleBarHeight);
    }

    //https://gist.github.com/hamakn/8939eb68a920a6d7a498
    public void BarHeight(Resources res) {
        // status bar height
        int statusBarHeight = 0;
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }

        /*
        // action bar height
        int actionBarHeight = 0;
        final TypedArray styledAttributes = getActivity().getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize }
        );
        actionBarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        */

        /*
        // navigation bar height
        int navigationBarHeight = 0;
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = res.getDimensionPixelSize(resourceId);
        }
        */
    }

    static final Point screenSize = new Point();
    public static Point getScreenSize(Context ctt) {
        if (ctt == null) {
            return screenSize;
        }
        WindowManager wm = (WindowManager) ctt.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            Display diplay = wm.getDefaultDisplay();
            if(diplay!=null)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)// Build.VERSION_CODES.JELLY_BEAN
                {
                    diplay.getRealMetrics(mDisplayMetrics);
                }
                else
                {
                    diplay.getMetrics(mDisplayMetrics);
                }
                int W = mDisplayMetrics.widthPixels;
                int H = mDisplayMetrics.heightPixels;
                if (W * H > 0 /*&& (W > screenSize.x || H > screenSize.y)*/)
                {
                    screenSize.set(W, H);
                    //Log.i(TAG, "screen size:" + screenSize.toString());
                }
            }
        }
//		if (MainActivity.DEBUG) {
//			Log.i(TAG, screenSize.toString());
//		}
        return screenSize;
    }
}
