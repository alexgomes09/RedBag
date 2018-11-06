package com.alexgomes.redbag.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by agomes on 10/14/18.
 */
// code taken from https://gist.github.com/hzqtc/7940858
public class FlowLayout extends ViewGroup {

    private int defaultHorizontalSpacing;
    private int defaultVerticalSpacing;

    public FlowLayout(Context context) {
        super(context);
        init(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        defaultHorizontalSpacing = (int) (15 * ((float)getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        defaultVerticalSpacing = (int) (15 * ((float)getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int lineHeight = 0;
        // 100 is a dummy number, widthMeasureSpec should always be EXACTLY for FlowLayout
        int myWidth = resolveSize(100, widthMeasureSpec);
        int wantedHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            // let the child measure itself
            child.measure(
                    getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(),
                            child.getLayoutParams().width),
                    getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(),
                            child.getLayoutParams().height));
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            // lineheight is the height of current line, should be the height of the heightest view
            lineHeight = Math.max(childHeight, lineHeight);
            if (childWidth + childLeft + getPaddingRight() > myWidth) {
                // wrap this line
                childLeft = getPaddingLeft();
                childTop += defaultVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            }
            childLeft += childWidth + defaultHorizontalSpacing;
        }
        wantedHeight += childTop + lineHeight + getPaddingBottom();
        setMeasuredDimension(myWidth, resolveSize(wantedHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int lineHeight = 0;
        int myWidth = right - left;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            lineHeight = Math.max(childHeight, lineHeight);
            if (childWidth + childLeft + getPaddingRight() > myWidth) {
                childLeft = getPaddingLeft();
                childTop += defaultVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            }
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + defaultHorizontalSpacing;
        }
    }
}
