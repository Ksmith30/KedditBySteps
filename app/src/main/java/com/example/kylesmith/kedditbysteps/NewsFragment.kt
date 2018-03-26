package com.example.kylesmith.kedditbysteps

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class NewsFragment : ViewGroup {
    private var newsList: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = container?.inflate(R.layout.news_fragment)
        newsList = view?.findViewById(R.id.news_list) as RecyclerView?

    }

}