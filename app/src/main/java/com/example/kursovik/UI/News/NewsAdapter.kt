package com.example.kursovik.UI.News

import android.R
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.Core.Utils.DateUtils
import com.example.kursovik.databinding.NewsListItemBinding


interface OnPostClickListener {
    fun onItemClick(item: Post)
}

class NewsAdapter(private val context: Context,
                       private val listener: OnPostClickListener): RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {
    private var items: MutableList<Post> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsListItemBinding.inflate(inflater, parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        Log.d("News count", items.size.toString())
        return items.size
    }

    fun setList(list: List<Post>) {
        items.clear()
        addList(list)
    }

    fun addList(list: List<Post>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class NewsItemViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.title.text = item.title
            binding.date.text = DateUtils.getDateString(item.date)
            binding.text.text = item.text
            val url: String = item.url

            Glide.with(context)
                .load(url)
                .into(binding.imageView2)
        }
    }

}