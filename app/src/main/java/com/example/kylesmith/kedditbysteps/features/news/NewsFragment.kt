package com.example.kylesmith.kedditbysteps.features.news

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.support.v7.widget.*
import android.view.View
import android.view.ViewGroup
import com.example.kylesmith.kedditbysteps.R
import com.example.kylesmith.kedditbysteps.commons.RedditNewsItem
import com.example.kylesmith.kedditbysteps.commons.RxBaseFragment
import com.example.kylesmith.kedditbysteps.features.news.adapter.NewsAdapter
import com.example.kylesmith.kedditbysteps.commons.extensions.inflate
import kotlinx.android.synthetic.main.news_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


@RequiresApi(Build.VERSION_CODES.M)
class NewsFragment : RxBaseFragment() {

    private val newsManager by lazy { NewsManager() }
    private var redditNews: RedditNews? = null

    class RedditNews {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         news_list.setHasFixedSize(true)
         news_list.layoutManager = LinearLayoutManager(context)

         initAdapter()

         if (savedInstanceState == null) {
             val news = (1..10).map {
                 RedditNewsItem(
                         "author$it",
                         "Title$it",
                         it,
                         1457207701L - it * 200,
                         "http://lorempixel.com/200/200/technics/$it",
                         "url"
                 )
             }
             (news_list.adapter as NewsAdapter).addNews(news)
             requestNews()
         }
    }

    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = NewsAdapter()
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }


}