package com.example.kylesmith.kedditbysteps.features.news

import com.example.kylesmith.kedditbysteps.api.RedditNewsResponse
import com.example.kylesmith.kedditbysteps.api.RestApi
import com.example.kylesmith.kedditbysteps.commons.RedditNewsItem
import java.util.*
import rx.Observable


class NewsManager(private val api: RestApi = RestApi()) {

    fun getNews(limit: String = "10"): Observable<List<RedditNewsItem>> {
        return Observable.create {
            subscriber ->

            val callResponse = api.getNews("", limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body()!!.data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments, item.created,
                            item.thumbnail, item.url)
                }
                subscriber.onNext(news)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}