package com.example.testapirequest.HTTP

import retrofit2.http.GET
import com.example.testapirequest.HTTP.Model.TweetResponse
import com.example.testapirequest.HTTP.Model.UserResponse
import retrofit2.Call
import retrofit2.http.Path

interface TwitterService {
    @GET("2/users/{id}/tweets?tweet.fields=public_metrics,created_at&exclude=retweets,replies")
    fun listTweets(@Path("id") id: String?): Call<TweetResponse?>

    @GET("https://api.twitter.com/2/users/:id")
    fun getUserFromId(@Path("id") id: String?): Call<UserResponse?>

    @GET("https://api.twitter.com/2/users/by/username/:username")
    fun getUserFromUsername(@Path("username") username: String?): Call<UserResponse?>
}