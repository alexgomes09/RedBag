package com.alexgomes.redbag.donor

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.alexgomes.redbag.R
import com.alexgomes.redbag.custom.EndlessRecyclerViewScrollListener
import com.alexgomes.redbag.networking.RestAdapter
import com.alexgomes.redbag.networking.generic.PostModel
import com.alexgomes.redbag.networking.reqest.BloodRequestPosts
import com.alexgomes.redbag.util.Util
import com.alexgomes.redbag.util.dp
import kotlinx.android.synthetic.main.activity_blood_request_list.*
import kotlinx.android.synthetic.main.partial_appbar.*
import kotlinx.android.synthetic.main.partial_loading_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by agomes on 9/18/18.
 * Donor's see this screen if donor created profile or not
 */
class BloodRequestListActivity : AppCompatActivity(), FilterFragment.OnFilterSet {

    private lateinit var adapter: RecipientListAdapter
    private val listOfBloodPost: MutableList<PostModel> = mutableListOf()
    private var filterBloodGroup: ArrayList<String> = arrayListOf()
    private var filterSort:String = "desc" // recent to old

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_request_list)

        appbar_title.text = "Blood Requests"

        btn_topbar_right.visibility = View.VISIBLE
        adapter = RecipientListAdapter(this@BloodRequestListActivity, listOfBloodPost)
        rvList.layoutManager = LinearLayoutManager(this@BloodRequestListActivity)
        rvList.adapter = adapter
        rvList.addItemDecoration(SpacingAroundCell(16.dp))

        getBloodRequest(0)

        rvList.addOnScrollListener(object : EndlessRecyclerViewScrollListener(rvList.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(totalItemsCount: Int, view: RecyclerView?) {
                getBloodRequest(listOfBloodPost.size)
            }
        })

        swipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                loading.visibility = View.VISIBLE
                listOfBloodPost.clear()
                adapter.notifyDataSetChanged()
                getBloodRequest(0)
            }
        })

        btn_topbar_right.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right)
                    .replace(android.R.id.content, FilterFragment.newInstance(filterBloodGroup,filterSort))
                    .addToBackStack(null)
                    .commit()
        }
    }

    fun getBloodRequest(amountToSkip: Int) {
        RestAdapter.getBloodRequestList(
                amountToSkip.toString(),
                filterBloodGroup,
                filterSort,
                object : Callback<BloodRequestPosts> {
            override fun onResponse(call: Call<BloodRequestPosts>, response: Response<BloodRequestPosts>) {
                loading.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false

                if (response.isSuccessful) {
                    if (response.body()!!.posts.isEmpty()) {
                        Util.showToast(this@BloodRequestListActivity, "Empty posts")
                        return
                    }
                    listOfBloodPost.addAll(response.body()!!.posts)
                    adapter.notifyItemInserted(listOfBloodPost.size)
                } else {
                    Util.showToast(this@BloodRequestListActivity, RestAdapter.parseError(response).message)
                }
            }

            override fun onFailure(call: Call<BloodRequestPosts>, t: Throwable) {
                loading.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                Util.showToast(this@BloodRequestListActivity, "Error retrieving data")
            }
        })
    }

    override fun filterSet(selectedSort: String, selectedFilter: ArrayList<String>) {
        this.filterSort = selectedSort
        this.filterBloodGroup = selectedFilter
        loading.visibility = View.VISIBLE
        listOfBloodPost.clear()
        adapter.notifyDataSetChanged()
        getBloodRequest(0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.scale_in, R.anim.slide_out_right)
    }


    inner class SpacingAroundCell(private val spacing: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.top = spacing
            outRect.left = spacing
            outRect.right = spacing

            val position = parent.getChildAdapterPosition(view)
            if (position == parent.adapter.itemCount - 1) {
                outRect.bottom = spacing
            }
        }
    }
}