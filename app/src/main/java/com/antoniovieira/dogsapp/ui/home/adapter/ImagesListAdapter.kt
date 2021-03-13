package com.antoniovieira.dogsapp.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.data.model.Breed
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImagesListAdapter(
    private val onItemSelected: (Breed) -> Unit
) : PagingDataAdapter<Breed, ImagesListAdapter.ImagesViewHolder>(COMPARATOR) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImagesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_image, parent, false)
        )

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val breedImg: ImageView = itemView.findViewById(R.id.breedImage)
        private val breedTxt: TextView = itemView.findViewById(R.id.breedName)

        fun bind(breed: Breed) {
            // FIXME
            //  - This should have a placeholder in case the image download fails and the
            //  transition would look much better
            //  - Some images are huge and don't look good
            Glide.with(itemView.context)
                .load(breed.image.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(breedImg)

            breedTxt.text = breed.name

            itemView.setOnClickListener {
                onItemSelected(breed)
            }
        }
    }
}