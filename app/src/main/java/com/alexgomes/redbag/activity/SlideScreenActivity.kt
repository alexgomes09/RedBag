package com.alexgomes.redbag.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alexgomes.redbag.R
import kotlinx.android.synthetic.main.activity_slide_screen.*

class SlideScreenActivity : AppCompatActivity() {

    private val listOfDrawable = listOf<Int>(R.drawable.slide_1,
            R.drawable.slide_2,
            R.drawable.slide_3,
            R.drawable.slide_4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_screen)

        viewPager.adapter = SliderAdapter()

        btnSkip.setOnClickListener {
            val askingScreen = Intent(SlideScreenActivity@this,AskingScreen::class.java)
            startActivity(askingScreen)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }


    inner class SliderAdapter : PagerAdapter(){

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(this@SlideScreenActivity).inflate(R.layout.view_pager_slider,container,false)
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
