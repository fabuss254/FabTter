package com.example.testapirequest.HTTP.Model

import com.squareup.moshi.Json
import java.util.Date

// Header of the response
data class Meta(
    @field:Json(name = "result_count") val result_count: Int,
    @field:Json(name = "oldest_id") val oldest_id: Long,
    @field:Json(name = "newest_id") val newest_id: Long,
    @field:Json(name = "next_token") val next_token: String,
)

data class TweetResponse(
    @field:Json(name = "data") val data: List<Tweet>,
    @field:Json(name = "meta") val meta: Meta,
)

// Body of every tweets
data class Tweet(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "text") val text: String,
    @field:Json(name = "edit_history_tweet_ids") val edit_history_tweet_ids: List<Long>,

    @field:Json(name = "created_at") val created_at: String?,
    @field:Json(name = "author_id") val author_id: Long?,
    @field:Json(name = "created_at") val in_reply_to_user_id: String?,
    @field:Json(name = "source") val source: String?,
    @field:Json(name = "conversation_id") val conversation_id: Long?,
    @field:Json(name = "reply_settings") val reply_settings: String?,
    @field:Json(name = "lang") val lang: String?,

    @field:Json(name = "public_metrics") val public_metrics: TweetPublicMetrics?,
    @field:Json(name = "entities") val entities: List<TweetUrl>?,
)

data class TweetPublicMetrics(
    @field:Json(name = "retweet_count") val retweet_count: Long,
    @field:Json(name = "reply_count") val reply_count: Long,
    @field:Json(name = "like_count") val like_count: Long,
    @field:Json(name = "quote_count") val quote_count: Long,
    @field:Json(name = "impression_count") val impression_count: Long?, // Was recently added to twitter, keeping it nullable in case it's not available on older tweets.
) {
    override fun toString(): String {
        return "TweetPublicMetrics[%s, %s, %s, %s, %s]".format(retweet_count, reply_count, like_count, quote_count, impression_count ?: "null")
    }
}

data class TweetUrl(
    @field:Json(name = "start") val start: Long,
    @field:Json(name = "end") val end: Long,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "expanded_url") val expanded_url: String,
    @field:Json(name = "display_url") val display_url: String,

)