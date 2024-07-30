package com.example.kursovik.UI.Friends.FriendsList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kursovik.Core.Models.Poso.Friend
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.Core.Utils.DateUtils
import com.example.kursovik.databinding.FriendListItemBinding
import com.example.kursovik.databinding.NewsListItemBinding

interface OnFriendClickListener {
    fun onItemClick(item: Friend)
}

class FriendsAdapter(private val context: Context,
                  private val listener: OnFriendClickListener): RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {
    private var items: MutableList<Friend> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FriendListItemBinding.inflate(inflater, parent, false)
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

    fun setList(list: List<Friend>) {
        items.clear()
        addList(list)
    }

    fun addList(list: List<Friend>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class FriendViewHolder(private val binding: FriendListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Friend) {
            binding.firstName.text = item.firstName
            binding.lastName.text = item.lastName
            binding.status.text = item.status
            binding.firstName.text = item.firstName

            Glide.with(context)
                .load(item.url)
                .into(binding.imageView2)
        }
    }

}