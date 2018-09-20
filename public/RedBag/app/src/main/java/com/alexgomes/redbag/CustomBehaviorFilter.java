package com.alexgomes.redbag;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by agomes on 9/16/18.
 */

class CustomBehaviorFilter extends CoordinatorLayout.Behavior<FrameLayout> {

    public CustomBehaviorFilter() {
        super();
        //Used when the layout has a behavior attached via the Annotation;
    }

    public CustomBehaviorFilter(Context context, AttributeSet attrs) {
        super(context, attrs);
        //Used when the layout has a behavior attached via xml (Within the xml file e.g.
        //<app:layout_behavior=".link.to.your.behavior">
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FrameLayout child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FrameLayout child, View dependency) {

        child.setY(dependency.getBottom());


        return false;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, FrameLayout child, View dependency) {
        super.onDependentViewRemoved(parent, child, dependency);
    }

}