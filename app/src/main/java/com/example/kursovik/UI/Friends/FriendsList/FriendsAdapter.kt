package com.example.kursovik.UI.Friends.FriendsList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.Core.Utils.DateUtils
import com.example.kursovik.databinding.NewsListItemBinding

interface OnFriendClickListener {
    fun onItemClick(item: Post)
}

class FriendsAdapter(private val context: Context,
                  private val listener: OnFriendClickListener): RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {
    private var items: MutableList<Post> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsListItemBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
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

    inner class FriendViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {

        }
    }

}