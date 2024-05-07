package com.example.codingtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codingtest.data.model.Articles
import com.example.codingtest.databinding.ItemLayoutBinding

class ArticleAdapter(
    private val articles: ArrayList<Articles>
) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = articles[position]
        holder.textViewTitle.text = item.author
        holder.textViewDescription.text = item.title
        Glide.with(holder.imageView.context)
            .load(item.urlToImage)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, articles: Articles)
    }

    class ViewHolder(binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewTitle = binding.textViewTitle
        val textViewDescription = binding.textViewDescription
        val imageView = binding.imageView
    }
}