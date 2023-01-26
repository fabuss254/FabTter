package com.example.testapirequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapirequest.HTTP.Model.Tweet

class TweetAdapter(private val dataSet: ArrayList<Tweet>) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usernameView: TextView
        val tagView: TextView
        val contentView: TextView

        init {
            // Define click listener for the ViewHolder's View
            usernameView = view.findViewById(R.id.UsernameTextview)
            tagView = view.findViewById(R.id.tagTextview)
            contentView = view.findViewById(R.id.contentTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tweetitem ,parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data: Tweet = dataSet[position];

        viewHolder.usernameView.text = GlobalFenv.userData?.name ?: "NoName"
        viewHolder.tagView.text = "@" + (GlobalFenv.userData?.username ?: "NoTag")
        viewHolder.contentView.text = data.text
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}