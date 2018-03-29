package com.example.kylesmith.kedditbysteps.features.news

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.support.v7.widget.*
import android.view.View
import android.view.ViewGroup
import com.example.kylesmith.kedditbysteps.R
import com.example.kylesmith.kedditbysteps.commons.RedditNewsItem
import com.example.kylesmith.kedditbysteps.features.news.adapter.NewsAdapter
import com.example.kylesmith.kedditbysteps.commons.extensions.inflate
import kotlinx.android.synthetic.main.news_fragment.*


@RequiresApi(Build.VERSION_CODES.M)
class NewsFragment : Fragment() {

    private val newsManager by lazy { NewsManager() }

    private val newsList: RecyclerView by lazy {
        news_list
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         newsList.setHasFixedSize(true)
         newsList.layoutManager = LinearLayoutManager(context)

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
             (newsList.adapter as NewsAdapter).addNews(news)
             requestNews()
         }
    }

    private fun initAdapter() {
        if (newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }
    }

    private fun requestNews() {

    }

}