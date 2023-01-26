package com.example.testapirequest

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.DispatcherState
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.Debug
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.testapirequest.HTTP.*
import com.example.testapirequest.HTTP.Model.*
import com.example.testapirequest.databinding.TweetinfoBinding
import com.example.testapirequest.databinding.TweetlistBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TweetInfo : Fragment() {

    private var _binding: TweetinfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = TweetinfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (GlobalFenv.tweetData == null) return
        if (GlobalFenv.userData == null) return

        binding.LinkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://twitter.com/%s/status/%s".format(GlobalFenv.userData!!.username, GlobalFenv.tweetData!!.id))
            startActivity(intent)
        }

        binding.tagView.text = "TAG: " + (GlobalFenv.userData!!.name)
        binding.textView.text = "TEXT: " + (GlobalFenv.tweetData!!.text)
        binding.usernameView.text = "USERNAME: " + (GlobalFenv.userData!!.username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}