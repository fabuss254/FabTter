package com.example.testapirequest

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapirequest.HTTP.Model.Tweet

class TweetAdapter(private val dataSet: Array<Tweet>) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //private val usernameView: TextView

        init {
            // Define click listener for the ViewHolder's View
            val usernameView = view.findViewById<TextView>(R.id.UsernameTextview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}