package com.example.kylesmith.kedditbysteps.features.news.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.kylesmith.kedditbysteps.R
import com.example.kylesmith.kedditbysteps.commons.RedditNewsItem
import com.example.kylesmith.kedditbysteps.commons.adapter.ViewType
import com.example.kylesmith.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.example.kylesmith.kedditbysteps.commons.extensions.getFriendlyTime
import com.example.kylesmith.kedditbysteps.commons.extensions.inflate
import com.example.kylesmith.kedditbysteps.commons.extensions.loadImage
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter: ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {

        @SuppressLint("SetTextI18n")
        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImage(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}