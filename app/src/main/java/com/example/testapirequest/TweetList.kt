package com.example.testapirequest

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapirequest.HTTP.*
import com.example.testapirequest.HTTP.Model.*
import com.example.testapirequest.databinding.TweetlistBinding
import kotlinx.coroutines.*
import retrofit2.Call

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TweetList : Fragment() {

    private var _binding: TweetlistBinding? = null
    private var tweetSet: ArrayList<Tweet> = ArrayList<Tweet>();
    private var adapter: TweetAdapter = TweetAdapter(tweetSet);

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = TweetlistBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tweetSet.clear()
        adapter.notifyDataSetChanged()

        val progressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            // First step: Retrieve user's id from their username
            val l: Call<UserResponse?> =
                HTTPService().getTwitterService().getUserFromUsername(GlobalFenv.CurrentUsername)
            var userData: UserResponse = UserResponse(UserResponseData("-1", "Unknown", "Unknown"))
            try {
                userData = (l.execute()).body()!!
            } catch (_: Exception) {}

            if (userData.data.id == "-1"){
                val alertBuilder = AlertDialog.Builder(context)
                alertBuilder.setTitle("une erreur est survenue.")
                alertBuilder.setMessage(String.format("L'utilisateur %s n'as pas était trouvé(e).", GlobalFenv.CurrentUsername))
                alertBuilder.setNeutralButton("Ok", DialogInterface.OnClickListener { _, _ ->  })

                withContext(Dispatchers.Main) {
                    alertBuilder.show()
                    findNavController().navigate(R.id.action_TweetList_To_Home)
                }
                return@launch
            }

            val id: String = userData.data.id;

            // Second step: Retrieve user's tweet list from their UserId
            Log.v("TweetList", ("User %s has been found: %s").format(GlobalFenv.CurrentUsername, id))
            GlobalFenv.CurrentUserId = id
            GlobalFenv.userData = userData.data

            var tweetData: TweetResponse? = TweetResponse(emptyList<Tweet>(), Meta(0, 0, 0, "None"))
            try {
                tweetData = HTTPService().getTwitterService().listTweets(id).execute().body()
            }catch(e: Exception) {
                Log.v("TweetList", e.message ?: "Unknown")
            }

            if (tweetData == null) {
                val alertBuilder = AlertDialog.Builder(context)
                alertBuilder.setTitle("une erreur est survenue.")
                alertBuilder.setMessage(String.format("Erreur durant la récuperation des tweets de %s.", GlobalFenv.CurrentUsername))
                alertBuilder.setNeutralButton("Ok", DialogInterface.OnClickListener { _, _ ->  })

                withContext(Dispatchers.Main) {
                    alertBuilder.show()
                    findNavController().navigate(R.id.action_TweetList_To_Home)
                }
                return@launch
            }

            withContext(Dispatchers.Main) {
                progressBar.visibility = View.INVISIBLE

                for (tweet in tweetData.data) {
                    tweetSet.add(tweet);
                }
                adapter.notifyDataSetChanged()
                Log.v("TweetList", ("found %s tweets").format(adapter.itemCount))
            }
        }

        //binding.progressBar.visibility = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}