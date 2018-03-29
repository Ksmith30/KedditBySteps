package com.example.kylesmith.kedditbysteps.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.kylesmith.kedditbysteps.R
import com.example.kylesmith.kedditbysteps.commons.adapter.ViewType
import com.example.kylesmith.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.example.kylesmith.kedditbysteps.commons.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading)) {
    }
}