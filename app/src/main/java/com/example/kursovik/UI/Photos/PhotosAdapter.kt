package com.example.kursovik.UI.Photos

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kursovik.databinding.PhotoListItemBinding

class PhotosAdapter(private val context: Context
): RecyclerView.Adapter<PhotosAdapter.PhotoItemViewHolder>() {
    private var items: MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoListItemBinding.inflate(inflater, parent, false)
        binding.root.post {
            binding.root.layoutParams.height = parent.width / 2
            binding.root.requestLayout()
        }
        return PhotoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        Log.d("News count", items.size.toString())
        return items.size
    }

    fun setList(list: List<String>) {
        items.clear()
        addList(list)
    }

    fun addList(list: List<String>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class PhotoItemViewHolder(private val binding: PhotoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {

            val url: String = item

            Glide.with(context)
                .load(url)
                .into(binding.imageView4)
        }
    }

}