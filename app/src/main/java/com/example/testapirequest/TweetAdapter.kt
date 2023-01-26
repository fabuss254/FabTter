package com.example.testapirequest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testapirequest.HTTP.Model.Tweet
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection


class TweetAdapter(private val dataSet: ArrayList<Tweet>) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usernameView: TextView
        val tagView: TextView
        val contentView: TextView
        val profileView: ImageView

        init {
            // Define click listener for the ViewHolder's View
            usernameView = view.findViewById(R.id.UsernameTextview)
            tagView = view.findViewById(R.id.tagTextview)
            contentView = view.findViewById(R.id.contentTextView)
            profileView = view.findViewById(R.id.profileView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tweetitem ,parent, false))
    }

    private fun getImageBitmap(url: String): Bitmap? {
        var bm: Bitmap? = null
        try {
            val aURL = URL(url)
            val conn: URLConnection = aURL.openConnection()
            conn.connect()
            val `is`: InputStream = conn.getInputStream()
            val bis = BufferedInputStream(`is`)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            `is`.close()
        } catch (e: IOException) {
            Log.e("TweetAdapter", "Error getting bitmap", e)
        }
        return bm
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data: Tweet = dataSet[position];

        viewHolder.usernameView.text = GlobalFenv.userData?.name ?: "NoName"
        viewHolder.tagView.text = "@" + (GlobalFenv.userData?.username ?: "NoTag")
        viewHolder.contentView.text = data.text

        viewHolder.contentView.setOnClickListener {
            GlobalFenv.tweetData = data;
            viewHolder.contentView.findNavController().navigate(R.id.action_TweetList_to_tweetInfo)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}