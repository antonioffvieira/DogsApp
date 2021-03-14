package com.antoniovieira.dogsapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.antoniovieira.dogsapp.data.model.Breed
import com.antoniovieira.dogsapp.databinding.ListItemImageBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImagesListAdapter(
    private val onItemSelected: (Breed) -> Unit
) : PagingDataAdapter<Breed, ImagesListAdapter.ImageViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Breed>() {
            override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ListItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ImageViewHolder(private val binding: ListItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(breed: Breed) {
            // FIXME
            //  - This should have a placeholder in case the image download fails and the
            //  transition would look much better
            //  - Some images are huge and don't look good
            Glide.with(itemView.context)
                .load(breed.image?.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.breedImage)

            binding.breedName.text = breed.name

            binding.root.setOnClickListener {
                onItemSelected(breed)
            }
        }
    }
}