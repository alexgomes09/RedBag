package com.alexgomes.redbag.donor

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexgomes.redbag.R
import com.alexgomes.redbag.custom.CustomTextView
import com.alexgomes.redbag.networking.generic.PostModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by agomes on 10/24/18.
 */
class RecipientListAdapter(var context: Context,
                           var listOfBloodPost: MutableList<PostModel>) : RecyclerView.Adapter<RecipientListAdapter.RecipientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecipientViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_blood_request_list, parent, false)
        return RecipientViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipientViewHolder, position: Int) {
        holder.tvName.text = listOfBloodPost[position].name
        holder.tvNumberOfBags.text = context.resources.getQuantityString(R.plurals.bloodBag, listOfBloodPost[position].numberOfBags, listOfBloodPost[position].numberOfBags)
        holder.tvPosted.text = "Posted: ${convertServerTimeToDisplayFormat(listOfBloodPost[position].createdAt)}"
        holder.tvBloodGroup.text = listOfBloodPost[position].bloodGroup
    }

    override fun getItemCount(): Int {
        return listOfBloodPost.size
    }

    private fun convertServerTimeToDisplayFormat(time: String?): String {

        if (time == null) {
            return ""
        }

        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val targetFormat = SimpleDateFormat("EEE, MMM dd yyyy hh:mm a")
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

        init {
            itemView.setOnClickListener {
                val ft = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                BloodRequestListDetailDialog.newInstance(listOfBloodPost[adapterPosition]).show(ft,null)
            }
        }
    }
}