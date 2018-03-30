package com.example.kylesmith.kedditbysteps.features.news

import com.example.kylesmith.kedditbysteps.commons.RedditNewsItem
import java.util.*
import rx.Observable


class NewsManager {

    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create {
            subscriber ->

            val news = mutableListOf<RedditNewsItem>()
            (1..10).map {
                news.add (RedditNewsItem(
                        "author$it",
                        "Title$it",
                        it,
                        1457207701L - it * 200,
                        "http://lorempixel.com/200/200/technics/$it",
                        "url"
                ))
            }
            subscriber.onNext(news)
            subscriber.onCompleted()
        }
    }


}