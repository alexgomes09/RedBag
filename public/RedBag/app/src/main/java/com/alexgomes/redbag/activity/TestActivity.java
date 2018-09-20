package com.alexgomes.redbag.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexgomes.redbag.R;

import java.util.ArrayList;

/**
 * Created by agomes on 9/11/18.
 */

public class TestActivity extends AppCompatActivity {

    float minValue = 0.3f;
    float maxValue = 1.0f;
    MyAdapter adapter;
    RecyclerView recyclerView;
    boolean scrolledTop = false;

    ViewGroup.LayoutParams layoutParamsBtn1, layoutParamsBtn2, layoutParamsBtn3;
    boolean didChange = false;

    AppBarLayout.OnOffsetChangedListener onOffsetChangedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing);
        final CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator);

        final AppBarLayout appBarLayout = findViewById(R.id.appbar);

        final ImageView btn1 = findViewById(R.id.btn1);
        final ImageView btn2 = findViewById(R.id.btn2);
        final ImageView btn3 = findViewById(R.id.btn3);
        final Button fab = findViewById(R.id.fab);

//        ViewCompat.setZ(findViewById(R.id.filter_view), -10f);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

//        ViewCompat.setZ(recyclerView, -1f);

        layoutParamsBtn1 = btn1.getLayoutParams();
        layoutParamsBtn2 = btn2.getLayoutParams();
        layoutParamsBtn3 = btn3.getLayoutParams();


        onOffsetChangedListener =  new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float percentage = ((float) Math.abs(verticalOffset) / appBarLayout.getTotalScrollRange());
                percentage = (maxValue - percentage * (maxValue - minValue));

                Log.v("==TAG==", "TestActivity.onOffsetChanged() " +percentage);

                btn1.setPivotX(0);
                btn1.setPivotY(btn1.getHeight());

                btn2.setPivotX(-btn1.getWidth());
                btn2.setPivotY(btn2.getHeight());

                btn1.setScaleX(percentage);
                btn1.setScaleY(percentage);

                btn2.setScaleX(percentage);
                btn2.setScaleY(percentage);
            }
        };

        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TestActivity.this, "CHICKEN", Toast.LENGTH_LONG).show();
            }
        });
//        recyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                appBarLayout.setExpanded(false);
//                recyclerView.smoothScrollToPosition(250);
//            }
//        }, 2000L);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                onOffsetChangedListener.onOffsetChanged(appBarLayout,40);

                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
                AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
                if(behavior!=null) {
//                    coordinatorLayout.onNestedFling(recyclerView, 0, 296, true);
                    appBarLayout.onNestedPreFling(coordinatorLayout, 0, 296);
//                    behavior.setTopAndBottomOffset(-80);
//                    behavior.onNestedFling(coordinatorLayout,appBarLayout, recyclerView, 0, 296, true);
//                    behavior.onNestedPreScroll(coordinatorLayout,appBarLayout,recyclerView,0,80,new int[2],ViewCompat.TYPE_TOUCH);
                }

//                recyclerView.smoothScrollTo(0, 0);

//                ViewGroup.LayoutParams params = appBarLayout.getLayoutParams();
//                params.height = 1500;
//                appBarLayout.setLayoutParams(params);

//                if (scrolledTop) {
//                    scrolledTop = false;
//                    appBarLayout.setExpanded(false);
//                    recyclerView.smoothScrollToPosition(250);
//                }else{
//                    scrolledTop = true;
//                    appBarLayout.setExpanded(true);
//                    recyclerView.smoothScrollToPosition(0);
//                }
            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        ArrayList<Integer> items = new ArrayList<>();

        public MyAdapter() {
            for (int i = 0; i <= 500; i++) {
                items.add(i);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            }, 2000);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(TestActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText(items.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
