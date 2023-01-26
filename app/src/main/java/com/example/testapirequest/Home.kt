package com.example.testapirequest

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.testapirequest.HTTP.HTTPService
import com.example.testapirequest.HTTP.Model.TweetResponse
import com.example.testapirequest.databinding.HomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Home : Fragment() {

    private var _binding: HomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = HomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var navController : NavController = findNavController()
        binding.button.setOnClickListener {
            var UserId: Long = -1;
            try {
                UserId = binding.InputUserID.text.toString().toLong()
            } catch(_: Exception) {}
            if (UserId <= 0) {
                val AlertBuilder = AlertDialog.Builder(context)
                AlertBuilder.setTitle("Identifiant invalide !")
                AlertBuilder.setMessage("L'identifiant donnée n'est pas valide.")
                AlertBuilder.setNeutralButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->  })

                AlertBuilder.show()
                return@setOnClickListener
            };
            GlobalFenv.CurrentUserId = UserId
            navController.navigate(R.id.action_Home_To_TweetList)
        }

        /*
        GlobalScope.launch(Dispatchers.IO) {
            val l: TweetResponse? =
                HTTPService().getTwitterService().listTweets("836928295845445633").execute().body()

        }
        */

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}