package com.alexgomes.redbag.onboarding

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.alexgomes.redbag.R
import com.alexgomes.redbag.util.PrefUtil
import com.alexgomes.redbag.util.dp
import kotlinx.android.synthetic.main.activity_slide_screen.*

class SlideScreenActivity : AppCompatActivity() {

    private val sliderAdapter = SliderAdapter()
    private var previousSelectedSlidePosition = 0

    private val listOfDrawable = listOf<Int>(R.drawable.slide_1,
            R.drawable.slide_2,
            R.drawable.slide_3,
            R.drawable.slide_4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_screen)

        viewPager.adapter = sliderAdapter

        btnSkip.setOnClickListener {
            val askingScreen = Intent(SlideScreenActivity@ this, AskingScreen::class.java)
            startActivity(askingScreen)
            finish()
            PrefUtil.putBoolean(PrefUtil.USER_FINISHED_ONBOARDING, true)
            overridePendingTransition(R.anim.slide_in_right, R.anim.scale_out)
        }

        for(index in 1..listOfDrawable.size){
            val view = View(this)
            view.layoutParams = LinearLayout.LayoutParams(10.dp,10.dp)
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(5.dp,0,5.dp,0)

            view.background = ContextCompat.getDrawable(this, R.drawable.tab_indicator_dot)
            pager_dot.addView(view)
            if(index == 1){
                view.isSelected = true
            }
        }


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                pager_dot.getChildAt(previousSelectedSlidePosition).isSelected = false
                pager_dot.getChildAt(position).isSelected = true
                previousSelectedSlidePosition = position
            }
        })
    }

    inner class SliderAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(this@SlideScreenActivity).inflate(R.layout.view_pager_slider, container, false)
            view.findViewById<ImageView>(R.id.image).setImageResource(listOfDrawable[position])
            container.addView(view)
            return view

        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return listOfDrawable.size
        }
    }
}
