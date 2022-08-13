package com.ahr.puspus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahr.puspus.data.Cat
import com.ahr.puspus.databinding.ItemRowCatBinding
import com.bumptech.glide.Glide

class CatAdapter(private val onItemClickListener: OnItemClickListener) : ListAdapter<Cat, CatAdapter.ViewHolder>(CatItemCallback) {

    private companion object CatItemCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.photo == newItem.photo
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.name == newItem.name
        }
    }

    inner class ViewHolder(private val binding: ItemRowCatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            Glide.with(itemView)
                .load(cat.photo)
                .into(binding.ivCat)

            binding.tvCatName.text = cat.name
            binding.tvCatDescription.text = cat.description
            binding.tvCatDescription.contentDescription = cat.name

            itemView.setOnClickListener { onItemClickListener.onCardClicked(cat) }
            binding.btnShare.setOnClickListener { onItemClickListener.onBtnShareClicked(cat) }
            binding.btnFavorite.setOnClickListener { onItemClickListener.onBtnFavoriteClicked(cat) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRowCatBinding = ItemRowCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemRowCatBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickListener {
        fun onCardClicked(cat: Cat)
        fun onBtnShareClicked(cat: Cat)
        fun onBtnFavoriteClicked(cat: Cat)
    }
}