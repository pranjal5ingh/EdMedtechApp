package com.example.edmedtech.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edmedtech.databinding.ItemBlogBinding

class BlogAdapter(
    private val titleList: List<String>,
    private val imageList: List<Int>
) : RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.binding.apply {
            blogTitle.text = titleList[position]
            blogImage.setImageResource(imageList[position])
        }
    }

    override fun getItemCount(): Int = titleList.size

    class BlogViewHolder(val binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root)
}
