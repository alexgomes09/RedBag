package com.alexgomes.redbag.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexgomes.redbag.R
import com.alexgomes.redbag.Util
import com.alexgomes.redbag.custom.CustomTextView
import com.alexgomes.redbag.dp
import com.alexgomes.redbag.networking.RestAdapter
import com.alexgomes.redbag.networking.reqest.Request_Get_BloodModel
import kotlinx.android.synthetic.main.activity_blood_request_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by agomes on 9/18/18.
 * Donor's see this screen if donor created profile or not
 */

class BloodRequestListActivity : AppCompatActivity() {

    private lateinit var adapter: RecipientListAdapter
    var listOfBloodPost: MutableList<Request_Get_BloodModel.Posts> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_request_list)

        adapter = RecipientListAdapter()
        rvList.layoutManager = LinearLayoutManager(this@BloodRequestListActivity)
        rvList.adapter = adapter
        rvList.addItemDecoration(SpacingAroundCell(16.dp))

        RestAdapter.getBloodRequest(object : Callback<Request_Get_BloodModel> {
            override fun onResponse(call: Call<Request_Get_BloodModel>, response: Response<Request_Get_BloodModel>) {
                if (response.isSuccessful) {
                    listOfBloodPost.addAll(response.body()!!.posts)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Request_Get_BloodModel>?, t: Throwable?) {
                Util.showToast(this@BloodRequestListActivity, "Error retrieving data")
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.scale_in, R.anim.slide_out_right)
    }

    inner class RecipientListAdapter : RecyclerView.Adapter<RecipientListAdapter.RecipientViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecipientViewHolder {
            val view = LayoutInflater.from(this@BloodRequestListActivity).inflate(R.layout.row_blood_request_list, parent, false)
            return RecipientViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecipientViewHolder, position: Int) {
            holder.tvName.text = listOfBloodPost[position].name
            holder.tvNumberOfBags.text = "${listOfBloodPost[position].numberOfBags} bags"
            holder.tvPosted.text = "Posted: ${convertServerTimeToDisplayFormat(listOfBloodPost[position].postedTime)}"
            holder.tvBloodGroup.text = listOfBloodPost[position].bloodGroup
        }

        override fun getItemCount(): Int {
            return listOfBloodPost.size
        }

        fun convertServerTimeToDisplayFormat(time: String?): String {

            if (time == null) {
                return ""
            }

            val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val targetFormat = SimpleDateFormat("EEE, MMM dd yyyy HH:mm")
            var originalDate: Date? = null
            try {
                originalDate = originalFormat.parse(time)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return targetFormat.format(originalDate)
        }

        inner class RecipientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvName = itemView.findViewById<CustomTextView>(R.id.tvName)
            var tvNumberOfBags = itemView.findViewById<CustomTextView>(R.id.tvNumberOfBags)
            var tvPosted = itemView.findViewById<CustomTextView>(R.id.tvPosted)
            var tvBloodGroup = itemView.findViewById<CustomTextView>(R.id.tvBloodGroup)
        }
    }

    inner class SpacingAroundCell(private val spacing: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.top = spacing
            outRect.left = spacing
            outRect.right = spacing
        }
    }
}