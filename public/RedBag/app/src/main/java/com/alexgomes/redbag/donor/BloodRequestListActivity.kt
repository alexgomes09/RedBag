package com.alexgomes.redbag.donor

import android.support.v7.app.AppCompatActivity

/**
 * Created by agomes on 9/18/18.
 * Donor's see this screen if donor created profile or not
 */
class BloodRequestListActivity : AppCompatActivity() {
//
//    private lateinit var adapter: RecipientListAdapter
//    private val body = hashMapOf<String, Any>()
//    private var listOfBloodPost: MutableList<Request_Get_BloodModel.Posts> = mutableListOf()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_blood_request_list)
//
//        btn_topbar_right.visibility = View.VISIBLE
//        adapter = RecipientListAdapter()
//        rvList.layoutManager = LinearLayoutManager(this@BloodRequestListActivity)
//        rvList.adapter = adapter
//        rvList.addItemDecoration(SpacingAroundCell(16.dp))
//
//        getBloodRequest(0)
//
//        rvList.addOnScrollListener(object : EndlessRecyclerViewScrollListener(rvList.layoutManager as LinearLayoutManager) {
//            override fun onLoadMore(totalItemsCount: Int, view: RecyclerView?) {
//                getBloodRequest(listOfBloodPost.size)
//            }
//        })
//
//        swipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
//            override fun onRefresh() {
//                loading.visibility = View.VISIBLE
//                listOfBloodPost.clear()
//                adapter.notifyDataSetChanged()
//                getBloodRequest(0)
//            }
//        })
//
//        btn_topbar_right.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_right)
//                    .replace(android.R.id.content, FilterFragment.newInstance())
//                    .addToBackStack(null)
//                    .commit()
//        }
//    }
//
//    fun getBloodRequest(amountToSkip: Int) {
//        body["skip"] = amountToSkip.toString()
//        body["bloodGroup"] = mutableListOf("A+","B+")
//
//        RestAdapter.getBloodRequest(body, object : Callback<Request_Get_BloodModel> {
//            override fun onResponse(call: Call<Request_Get_BloodModel>, response: Response<Request_Get_BloodModel>) {
//                loading.visibility = View.GONE
//                swipeRefreshLayout.isRefreshing = false;
//
//                if (response.isSuccessful) {
//                    listOfBloodPost.addAll(response.body()!!.posts)
//                    adapter.notifyItemInserted(listOfBloodPost.size)
//                }
//            }
//
//            override fun onFailure(call: Call<Request_Get_BloodModel>?, t: Throwable?) {
//                loading.visibility = View.GONE
//                swipeRefreshLayout.isRefreshing = false
//                Util.showToast(this@BloodRequestListActivity, "Error retrieving data")
//            }
//        })
//    }
//
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        overridePendingTransition(R.anim.scale_in, R.anim.slide_out_right)
//    }
//
//    inner class RecipientListAdapter : RecyclerView.Adapter<RecipientListAdapter.RecipientViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecipientViewHolder {
//            val view = LayoutInflater.from(this@BloodRequestListActivity).inflate(R.layout.row_blood_request_list, parent, false)
//            return RecipientViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: RecipientViewHolder, position: Int) {
//            holder.tvName.text = listOfBloodPost[position].name
//            holder.tvNumberOfBags.text = "${listOfBloodPost[position].numberOfBags} bags"
//            holder.tvPosted.text = "Posted: ${convertServerTimeToDisplayFormat(listOfBloodPost[position].postedTime)}"
//            holder.tvBloodGroup.text = listOfBloodPost[position].bloodGroup
//        }
//
//        override fun getItemCount(): Int {
//            return listOfBloodPost.size
//        }
//
//        fun convertServerTimeToDisplayFormat(time: String?): String {
//
//            if (time == null) {
//                return ""
//            }
//
//            val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//            val targetFormat = SimpleDateFormat("EEE, MMM dd yyyy hh:mm a")
//            var originalDate: Date? = null
//            try {
//                originalDate = originalFormat.parse(time)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//
//            return targetFormat.format(originalDate)
//        }
//
//        inner class RecipientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            var tvName = itemView.findViewById<CustomTextView>(R.id.tvName)
//            var tvNumberOfBags = itemView.findViewById<CustomTextView>(R.id.tvNumberOfBags)
//            var tvPosted = itemView.findViewById<CustomTextView>(R.id.tvPosted)
//            var tvBloodGroup = itemView.findViewById<CustomTextView>(R.id.tvBloodGroup)
//        }
//    }
//
//    inner class SpacingAroundCell(private val spacing: Int) : RecyclerView.ItemDecoration() {
//
//        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//            outRect.top = spacing
//            outRect.left = spacing
//            outRect.right = spacing
//        }
//    }
}