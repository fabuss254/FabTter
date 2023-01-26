package com.example.testapirequest

import com.example.testapirequest.HTTP.Model.Tweet
import com.example.testapirequest.HTTP.Model.UserResponseData

object GlobalFenv {
    var CurrentUsername: String = "";
    var CurrentUserId: String = "";

    var userData : UserResponseData? = null;
    var tweetData : Tweet? = null;
}