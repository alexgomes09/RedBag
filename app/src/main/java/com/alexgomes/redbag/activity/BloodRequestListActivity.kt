package com.alexgomes.redbag.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexgomes.redbag.R
import kotlinx.android.synthetic.main.activity_blood_request_list.*

/**
 * Created by agomes on 9/18/18.
 * Donor's see this screen if donor created profile or not
 */

class BloodRequestListActivity : AppCompatActivity() {

    lateinit var adapter: RecipientListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_request_list)

        adapter = RecipientListAdapter()
        rvList.layoutManager = LinearLayoutManager(this@BloodRequestListActivity)
        rvList.adapter = adapter

    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.scale_in, R.anim.slide_out_right)
    }


    inner class RecipientListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {


        constructor() : super()


        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(this@BloodRequestListActivity).inflate(R.layout.row_blood_request_list, parent, false)
            return RecipientViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


        inner class RecipientViewHolder : RecyclerView.ViewHolder {
            constructor(itemView: View?) : super(itemView)
        }

    }
}