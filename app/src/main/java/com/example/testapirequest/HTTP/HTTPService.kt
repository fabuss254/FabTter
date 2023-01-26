package com.example.testapirequest.HTTP

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class HTTPService {
    var client = OkHttpClient.Builder().addInterceptor((HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))).addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAG3qkwEAAAAAiHOrt1fiCqKMri4DwFwtjpNY1%2B4%3DifV9q9pKPtruJ75WDnHGoowBNsU0V2989W87rSxZREXojZkD2A")
            .build()
        chain.proceed(newRequest)
    }.build()

    fun getTwitterService() : TwitterService {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.twitter.com/")
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                                                                .add(KotlinJsonAdapterFactory())
                                                                .build()))
            .build();

        return retrofit.create(TwitterService::class.java)
    }
}