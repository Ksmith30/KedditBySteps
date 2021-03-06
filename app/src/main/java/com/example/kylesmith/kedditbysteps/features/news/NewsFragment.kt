package com.example.kylesmith.kedditbysteps.features.news

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.support.v7.widget.*
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.kylesmith.kedditbysteps.R
import com.example.kylesmith.kedditbysteps.commons.InfiniteScrollListener
import com.example.kylesmith.kedditbysteps.commons.RedditNews
import com.example.kylesmith.kedditbysteps.commons.RedditNewsItem
import com.example.kylesmith.kedditbysteps.commons.RxBaseFragment
import com.example.kylesmith.kedditbysteps.features.news.adapter.NewsAdapter
import com.example.kylesmith.kedditbysteps.commons.extensions.inflate
import kotlinx.android.synthetic.main.news_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


@RequiresApi(Build.VERSION_CODES.M)
class NewsFragment : RxBaseFragment() {

    companion object {
        private const val KEY_REDDIT_NEWS = "redditNews"
    }

    private var redditNews: RedditNews? = null
    private val newsManager by lazy { NewsManager() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

         news_list.apply {
             setHasFixedSize(true)
             val linearLayout = LinearLayoutManager(context)
             layoutManager = linearLayout
             clearOnScrollListeners()
             addOnScrollListener(InfiniteScrollListener({ requestNews()}, linearLayout))
         }

         initAdapter()

         if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
             redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
             (news_list.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
         } else {
             requestNews()
         }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.isNotEmpty()) {
            outState!!.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = NewsAdapter()
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { retrievedNews ->
                            redditNews = retrievedNews
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }


}