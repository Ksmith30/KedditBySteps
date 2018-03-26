package com.example.kylesmith.kedditbysteps

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.support.v7.widget.*
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_fragment.*


@RequiresApi(Build.VERSION_CODES.M)
class NewsFragment : Fragment() {
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
    }

}