package com.example.testapirequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapirequest.HTTP.*
import com.example.testapirequest.HTTP.Model.*
import com.example.testapirequest.databinding.TweetlistBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TweetList : Fragment() {

    private var _binding: TweetlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = TweetlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            val l: TweetResponse? =
                HTTPService().getTwitterService().listTweets("836928295845445633").execute().body()

        }

        //binding.progressBar.visibility = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}